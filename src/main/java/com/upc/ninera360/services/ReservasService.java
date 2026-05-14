package com.upc.ninera360.services;

import com.upc.ninera360.dtos.ReservasDTO;

import java.util.List;

public interface ReservasService {
    public ReservasDTO insertarReserva(ReservasDTO reservasDTO);
    public ReservasDTO actualizarReserva(ReservasDTO reservasDTO);
    public void eliminarReserva(long id);
    public List<ReservasDTO> listarReservas();
    public ReservasDTO obtenerReservaPorId(long id);
    public long count();
    // Buscar por estado
    public List<ReservasDTO> findByEstado(String estado);
}
