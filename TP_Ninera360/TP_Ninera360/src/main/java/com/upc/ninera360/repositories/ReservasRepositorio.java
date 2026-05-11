package com.upc.ninera360.repositories;

import com.upc.ninera360.entities.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservasRepositorio extends JpaRepository<Reservas, Long> {
    List<Reservas> findByCliente_IdUsuario(Long idCliente);
    List<Reservas> findByCuidador_IdUsuario(Long idCuidador);
    List<Reservas> findByEstadoIgnoreCase(String estado);
}
