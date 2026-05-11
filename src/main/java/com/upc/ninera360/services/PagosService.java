package com.upc.ninera360.services;

import com.upc.ninera360.dtos.PagosDTO;
import com.upc.ninera360.entities.Pagos;

import java.util.List;

public interface PagosService  {
    public PagosDTO insertarPago(PagosDTO pagosDTO);
    public PagosDTO actualizarPago(PagosDTO pagosDTO);
    public void eliminarPago(long id);
    public List<PagosDTO> listarPagos();
    public PagosDTO buscarPorId(long id);
}
