package com.projectExperimentality.service.serviceimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectExperimentality.exceptions.BadRequestException;
import com.projectExperimentality.model.ProductoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projectExperimentality.persistance.entities.Imagen;
import com.projectExperimentality.persistance.entities.Producto;
import com.projectExperimentality.persistance.repository.ImagenRepository;
import com.projectExperimentality.persistance.repository.ProductoRepository;
import com.projectExperimentality.service.IService;

import java.util.*;

@Service
public class ProductoService implements IService<ProductoDto> {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ImagenRepository imagenRepository;

    @Autowired
    ObjectMapper objectMapper;


    @Override
    public ProductoDto guardar(ProductoDto entidad, Set<Imagen> lista) throws BadRequestException {
        if(entidad == null){
            return null;
        }
        Producto prueba = objectMapper.convertValue(entidad, Producto.class);
        Producto product = productoRepository.save(prueba);
        Optional<Producto> productoPerseado = productoRepository.findById(product.getId());
        if(lista != null){
            lista.forEach(imagen -> {
                imagen.setProducto(productoPerseado.get());
                imagenRepository.save(imagen);
            });
        }
        product.setImagenes(lista);
        return objectMapper.convertValue(product, ProductoDto.class);
    }

    @Override
    public List<ProductoDto> obtenerTodos() {
        List<ProductoDto> productos = new ArrayList<>();
        for (Producto producto: productoRepository.findAll()) {
            productos.add(objectMapper.convertValue(producto, ProductoDto.class));
        }
        return productos;
    }

    @Override
    public ProductoDto buscarPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.get() != null){
            return objectMapper.convertValue(producto, ProductoDto.class);
        }else {
            return null;
        }
    }

    @Override
    public ProductoDto actualizar(ProductoDto entidad, Set<Imagen> lista) {
        Producto producto = null;
        Optional<Producto> productoParseado = productoRepository.findById(entidad.getId());
        if(entidad == null){
            return null;
        }else if (productoParseado.get() != null){
            Producto prueba = objectMapper.convertValue(entidad, Producto.class);
            producto = productoRepository.save(prueba);
            Optional<Producto> productoPerseado = productoRepository.findById(producto.getId());
            if(lista != null){
                lista.forEach(imagen -> {
                    imagen.setProducto(productoPerseado.get());
                    imagenRepository.save(imagen);
                });
            }
        }
        producto.setImagenes(lista);
        return objectMapper.convertValue(producto, ProductoDto.class);
    }

    @Override
    public boolean borrar(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.get() != null){
            productoRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
