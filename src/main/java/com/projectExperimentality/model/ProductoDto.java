package com.projectExperimentality.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import com.projectExperimentality.persistance.entities.Imagen;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
public class ProductoDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Double precioConDescuento;
    private Integer porcentajeDescuento;
    private String paisDeVenta;
    @JsonIgnoreProperties("producto")
    private Set<Imagen> imagenes;
    @JsonIgnore
    private MultipartFile imagen[];

    public ProductoDto() {
    }
}
