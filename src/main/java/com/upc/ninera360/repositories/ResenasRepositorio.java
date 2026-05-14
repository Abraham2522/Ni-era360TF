package com.upc.ninera360.repositories;

import com.upc.ninera360.entities.Resenas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResenasRepositorio extends JpaRepository<Resenas, Long> {
    public long count();
    // Buscar reseñas por calificación
    public List<Resenas> findByCalificacion(int calificacion);
}
