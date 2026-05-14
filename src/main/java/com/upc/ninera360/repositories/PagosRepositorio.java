package com.upc.ninera360.repositories;

import com.upc.ninera360.entities.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagosRepositorio extends JpaRepository<Pagos, Long> {
    public long count();
    public List<Pagos> findByEstadoPago(boolean estadoPago);
}
