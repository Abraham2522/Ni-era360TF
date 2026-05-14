package com.upc.ninera360.repositories;

import com.upc.ninera360.entities.Cuidadores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuidadoresRepositorio extends JpaRepository<Cuidadores, Long> {
    public long count();
    public List<Cuidadores> findByDescripcion(String descripcion);
}
