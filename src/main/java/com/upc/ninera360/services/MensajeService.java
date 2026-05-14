package com.upc.ninera360.services;

import com.upc.ninera360.dtos.MensajeDTO;

import java.util.List;

public interface MensajeService {
    public MensajeDTO insertarMensaje(MensajeDTO mensajeDTO);
    public MensajeDTO editarMensaje(MensajeDTO mensajeDTO);
    public void eliminarMensaje(long id);
    public List<MensajeDTO> listarMensajes();
    public MensajeDTO buscarPorId(long idChat);
    // buscar mensajes por contenido
    List<MensajeDTO> findByContenido(String contenido);
    // count
    long count();
}
