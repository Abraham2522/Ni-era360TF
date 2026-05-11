package com.upc.ninera360.services;

import com.upc.ninera360.dtos.ReservasDTO;

import java.util.List;

public interface ReservasService {
    ReservasDTO insertarReserva(ReservasDTO reservasDTO);
    ReservasDTO actualizarReserva(ReservasDTO reservasDTO);
    void eliminarReserva(long id);
    ReservasDTO obtenerReservaPorId(long id);
    List<ReservasDTO> listarReservas();
    List<ReservasDTO> listarReservasPorCliente(long idCliente);
    List<ReservasDTO> listarReservasPorCuidador(long idCuidador);
    List<ReservasDTO> listarReservasPorEstado(String estado);
}
