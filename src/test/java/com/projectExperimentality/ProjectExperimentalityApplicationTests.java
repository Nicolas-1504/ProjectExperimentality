package com.projectExperimentality;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectExperimentality.controller.ProductoController;
import com.projectExperimentality.exceptions.BadRequestException;
import com.projectExperimentality.model.ProductoDto;
import com.projectExperimentality.persistance.entities.Imagen;
import com.projectExperimentality.persistance.entities.Producto;
import com.projectExperimentality.persistance.repository.ImagenRepository;
import com.projectExperimentality.persistance.repository.ProductoRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class ProjectExperimentalityApplicationTests {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductoController productoController;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ImagenRepository imagenRepository;

    @Test
    public void testActualizarProducto(){
        Producto producto;
        producto = new Producto();
        producto.setId(Long.parseLong("1"));
        producto.setNombre("Blusa");
        producto.setDescripcion("Color negro");
        producto.setPrecio(20.05);
        producto.setPorcentajeDescuento(15);
        producto.setPrecioConDescuento(15.00);
        producto.setPaisDeVenta("Colombia");
        try{
            Set<Imagen> imagenes = new HashSet<>();
            FileInputStream fis = new FileInputStream("src//main//resources//static//images/Imagen.jpeg");
            FileInputStream fis1 = new FileInputStream("src//main//resources//static//images/Imagen1.jpeg");
            Imagen imagen = new Imagen();
            imagen.setId(Long.parseLong("1"));
            imagen.setImagen(fis.readAllBytes());
            imagen.setProducto(producto);
            Imagen imagen1 = new Imagen();
            imagen1.setId(Long.parseLong("2"));
            imagen1.setImagen(fis1.readAllBytes());
            imagen1.setProducto(producto);
            imagenes.add(imagen);
            imagenes.add(imagen1);
            imagenRepository.save(imagen);
            imagenRepository.save(imagen1);
            producto.setImagenes(imagenes);
        } catch (FileNotFoundException e) {
            Assert.fail("No se puede guardar la imagen");
        } catch (IOException e) {
            e.printStackTrace();
        }
        productoRepository.save(producto);
    }

    @Test
    public void testPorcentajeDescuentoMayorQueElPermitidoColombiaYMexico() throws BadRequestException {
        Producto producto;
        producto = new Producto();
        producto.setId(Long.parseLong("2"));
        producto.setNombre("Camisa");
        producto.setDescripcion("Color blanco");
        producto.setPrecio(40.05);
        producto.setPorcentajeDescuento(60);
        producto.setPrecioConDescuento(20.00);
        producto.setPaisDeVenta("Colombia");
        ResponseEntity<?> respuesta =  productoController.crearProducto(objectMapper.convertValue(producto, ProductoDto.class));
        boolean respuesta1 = "No se puede crear el producto debido que para Colombia y Mexico, el porcentaje de descuento no puede ser mayor a 50".equals(respuesta.getBody());
        Assert.assertTrue(respuesta1);
    }

    @Test
    public void testPorcentajeDescuentoMayorQueElPermitidoChileYPeru() throws BadRequestException {
        Producto producto;
        producto = new Producto();
        producto.setId(Long.parseLong("3"));
        producto.setNombre("Camisa");
        producto.setDescripcion("Color azul");
        producto.setPrecio(30.55);
        producto.setPorcentajeDescuento(40);
        producto.setPrecioConDescuento(10.00);
        producto.setPaisDeVenta("Chile");
        ResponseEntity<?> respuesta =  productoController.crearProducto(objectMapper.convertValue(producto, ProductoDto.class));
        Assert.assertEquals("No se puede crear el producto debido que para Chile y Peru, el porcentaje de descuento no puede ser mayor a 30", respuesta.getBody());
    }

    @Test
    public void testProductoSinImagenes() throws BadRequestException {
        Producto producto;
        producto = new Producto();
        producto.setId(Long.parseLong("4"));
        producto.setNombre("Pantalon");
        producto.setDescripcion("Color negro");
        producto.setPrecio(60.05);
        producto.setPorcentajeDescuento(30);
        producto.setPrecioConDescuento(25.00);
        producto.setPaisDeVenta("Colombia");
        ResponseEntity<?> respuesta =  productoController.crearProducto(objectMapper.convertValue(producto, ProductoDto.class));
        Assert.assertEquals("No se puede crear un producto sin imagenes", respuesta.getBody());
    }

    @Test
    public void testObtenerListaProductos(){
        List<Producto> lista = productoRepository.findAll();
        Assert.assertTrue(lista != null);
    }
}
