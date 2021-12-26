package com.projectExperimentality.persistance.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity(name = "imagenes")
@Getter @Setter
public class Imagen implements Serializable{

    @Id
    @SequenceGenerator(name = "imagen_sequence", sequenceName = "imagen_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "imagen_sequence")
    private Long id;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "mediumblob")
    private byte[] imagen;
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonIgnoreProperties("imagenes")
    private Producto producto;

    public Imagen() {
    }
}
