package com.projectExperimentality.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.projectExperimentality.persistance.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
