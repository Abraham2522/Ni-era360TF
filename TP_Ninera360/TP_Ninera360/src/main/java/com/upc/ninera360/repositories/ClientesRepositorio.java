package com.upc.ninera360.repositories;

import com.upc.ninera360.entities.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepositorio extends JpaRepository<Clientes, Long> {
}
