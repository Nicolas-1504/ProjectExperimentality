package com.projectExperimentality.persistance.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table
@Entity(name = "productos")
@Getter @Setter
public class Producto implements Serializable{

    @Id
    @SequenceGenerator(name = "producto_sequence", sequenceName = "producto_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "producto_sequence")
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Double precioConDescuento;
    private Integer porcentajeDescuento;
    private String paisDeVenta;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "producto")
    @JsonIgnoreProperties("producto")
    private Set<Imagen> imagenes;

    public Producto() {
    }
}
