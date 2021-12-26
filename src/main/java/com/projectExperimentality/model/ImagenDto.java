package com.projectExperimentality.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projectExperimentality.persistance.entities.Producto;

import java.sql.Blob;

public class ImagenDto {

    private Long id;
    private Blob imagen;
    @JsonIgnoreProperties("imagenes")
    private Producto producto;
}
