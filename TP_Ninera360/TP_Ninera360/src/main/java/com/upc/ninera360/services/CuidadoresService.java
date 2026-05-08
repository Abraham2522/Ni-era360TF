package com.upc.ninera360.services;

import com.upc.ninera360.dtos.CuidadoresDTO;

import java.util.List;

public interface CuidadoresService {
    public CuidadoresDTO insertarCuidador(CuidadoresDTO cuidadoresDTO);
    public CuidadoresDTO actualizarCuidador(CuidadoresDTO cuidadoresDTO);
    public void eliminarCuidador(long id);
    public List<CuidadoresDTO> listarCuidadores();
}
