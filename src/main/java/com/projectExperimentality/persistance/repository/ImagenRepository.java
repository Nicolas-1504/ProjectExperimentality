package com.projectExperimentality.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projectExperimentality.persistance.entities.Imagen;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {
}
