package com.projectExperimentality.service;

import com.projectExperimentality.exceptions.BadRequestException;
import com.projectExperimentality.persistance.entities.Imagen;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface IService<E> {

    E guardar(E entidad, Set<Imagen> lista) throws BadRequestException;
    List<E> obtenerTodos();
    E buscarPorId(Long id);
    E actualizar(E entidad, Set<Imagen> lista);
    boolean borrar(Long id);
}
