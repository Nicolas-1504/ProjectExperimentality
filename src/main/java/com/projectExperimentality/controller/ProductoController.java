package com.projectExperimentality.controller;

import com.projectExperimentality.exceptions.BadRequestException;
import com.projectExperimentality.model.ProductoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.projectExperimentality.persistance.entities.Imagen;
import com.projectExperimentality.service.serviceimpl.ProductoService;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/lista")
    public ResponseEntity<?> obtenerProductos(){
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProductoPorId(@PathVariable Long id){
        ProductoDto producto = productoService.buscarPorId(id);
        if (producto != null){
            return ResponseEntity.ok(producto);
        }else {
            return ResponseEntity.badRequest().body("No se encontro el producto");
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearProducto(@ModelAttribute ProductoDto producto) throws BadRequestException {
        if(producto.getPaisDeVenta().equals("Colombia") || producto.getPaisDeVenta().equals("Mexico")){
            if (producto.getPorcentajeDescuento() > 50){
                return ResponseEntity.badRequest().body("No se puede crear el producto debido que para Colombia y Mexico, el porcentaje de descuento no puede ser mayor a 50");
            }
        }else if (producto.getPaisDeVenta().equals("Chile") || producto.getPaisDeVenta().equals("Peru")){
            if (producto.getPorcentajeDescuento() > 30){
                return ResponseEntity.badRequest().body("No se puede crear el producto debido que para Chile y Peru, el porcentaje de descuento no puede ser mayor a 30");
            }
        }
        Set<Imagen> lista = new HashSet<>();
        if (producto.getImagen() == null){
            return ResponseEntity.badRequest().body("No se puede crear un producto sin imagenes");
        }else if (producto.getImagen().length > 0)
            try {
                for (MultipartFile imagen: producto.getImagen()) {
                    Imagen imagen1 = new Imagen();
                    imagen1.setImagen(imagen.getBytes());
                    lista.add(imagen1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        ProductoDto productoDto = productoService.guardar(producto, lista);
        if (productoDto != null){
            return ResponseEntity.ok(productoDto);
        }else{
            throw new BadRequestException("No se puede crear un producto null");
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarProducto(@ModelAttribute ProductoDto producto) throws BadRequestException {
        if (producto.getId() == null){
            return ResponseEntity.badRequest().body("No se puede actualizar el producto sin el id correspondiente");
        }else if(producto.getPaisDeVenta().equals("Colombia") || producto.getPaisDeVenta().equals("Mexico")){
            if (producto.getPorcentajeDescuento() > 50){
                return ResponseEntity.badRequest().body("No se puede actualizar el producto debido que para Colombia y Mexico, el porcentaje de descuento no puede ser mayor a 50");
            }
        }else if (producto.getPaisDeVenta().equals("Chile") || producto.getPaisDeVenta().equals("Peru")){
            if (producto.getPorcentajeDescuento() > 30){
                return ResponseEntity.badRequest().body("No se puede actualizar el producto debido que para Chile y Peru, el porcentaje de descuento no puede ser mayor a 30");
            }
        }
        Set<Imagen> lista = new HashSet<>();
        if (producto.getImagen() == null){
            return ResponseEntity.badRequest().body("No se puede actualizar un producto sin imagenes");
        }else if (producto.getImagen().length > 0)
            try {
                for (MultipartFile imagen: producto.getImagen()) {
                    Imagen imagen1 = new Imagen();
                    imagen1.setImagen(imagen.getBytes());
                    lista.add(imagen1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        ProductoDto productoDto = productoService.actualizar(producto, lista);
        if (productoDto != null){
            return ResponseEntity.ok(productoDto);
        }else{
            throw new BadRequestException("No se puede actualizar un producto null");
        }
    }


}
