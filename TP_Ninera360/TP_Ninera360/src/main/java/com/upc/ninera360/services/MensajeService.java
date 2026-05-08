package com.upc.ninera360.services;

import com.upc.ninera360.dtos.MensajeDTO;
import java.util.List;

public interface MensajeService {
    List<MensajeDTO> getAll();
    List<MensajeDTO> getByChat(Long idChat);
    MensajeDTO getById(Long id);
    MensajeDTO create(MensajeDTO mensajeDTO);
    void delete(Long id);
}
