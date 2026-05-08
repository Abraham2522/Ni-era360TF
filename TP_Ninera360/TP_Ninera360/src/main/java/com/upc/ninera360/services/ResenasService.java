package com.upc.ninera360.services;

import com.upc.ninera360.dtos.PagosDTO;
import com.upc.ninera360.dtos.ResenasDTO;

import java.util.List;

public interface ResenasService {
    public ResenasDTO insertarResena(ResenasDTO resenasDTO);
    public ResenasDTO actualizarResena(ResenasDTO resenasDTO);
    public void eliminarResena(long id);
    public List<ResenasDTO> listarResenas();

}
