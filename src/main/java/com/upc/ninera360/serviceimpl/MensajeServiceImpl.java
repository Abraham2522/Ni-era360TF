package com.upc.ninera360.serviceimpl;

import com.upc.ninera360.dtos.MensajeDTO;
import com.upc.ninera360.entities.Chat;
import com.upc.ninera360.entities.Mensaje;
import com.upc.ninera360.entities.UserProfile;
import com.upc.ninera360.repositories.ChatRepository;
import com.upc.ninera360.repositories.MensajeRepository;
import com.upc.ninera360.repositories.UserProfileRepository;
import com.upc.ninera360.services.MensajeService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public MensajeDTO insertarMensaje(MensajeDTO mensajeDTO) {
        if (mensajeDTO.getIdMensaje() != null && mensajeRepository.existsById(mensajeDTO.getIdMensaje())) {
            throw new RuntimeException("El Mensaje con ID " + mensajeDTO.getIdMensaje() + " ya existe.");
        }
        Mensaje mensaje = modelMapper.map(mensajeDTO, Mensaje.class);
        mensaje = mensajeRepository.save(mensaje);
        return modelMapper.map(mensaje, MensajeDTO.class);
    }

    @Transactional
    @Override
    public MensajeDTO editarMensaje(MensajeDTO mensajeDTO) {
        return mensajeRepository.findById(mensajeDTO.getIdMensaje())
                .map(existing -> {
                    Mensaje mensaje = modelMapper.map(mensajeDTO, Mensaje.class);
                    return modelMapper.map(mensajeRepository.save(mensaje), MensajeDTO.class);
                })
                .orElseThrow(() -> new RuntimeException(String.format("Mensaje con ID %d no encontrado", mensajeDTO.getIdMensaje())));
    }

    @Override
    @Transactional
    public void eliminarMensaje(long id) {
        if (!mensajeRepository.existsById(id)) {
            throw new RuntimeException("Mensaje no encontrado con ID: " + id);
        }
        mensajeRepository.deleteById(id);
    }

    @Override
    public List<MensajeDTO> listarMensajes() {
        return mensajeRepository.findAll()
                .stream()
                .map(mensaje -> modelMapper.map(mensaje, MensajeDTO.class))
                .toList();
    }

    @Override
    public MensajeDTO buscarPorId(long id) {
        return mensajeRepository.findById(id)
                .map(mensaje -> modelMapper.map(mensaje, MensajeDTO.class))
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado con ID: " + id));
    }
    @Override
    public long count() {
        long count = mensajeRepository.count();
        if (count == 0) {
            throw new RuntimeException(
                    "No existen mensajes registrados"
            );
        }
        return count;
    }

    @Override
    public List<MensajeDTO> findByContenido(String contenido) {
        List<Mensaje> lista =
                mensajeRepository.findByContenido(contenido);
        if (lista.isEmpty()) {
            throw new RuntimeException(
                    "No existen mensajes con ese contenido"
            );
        }
        return lista.stream()
                .map(m ->
                        modelMapper.map(m, MensajeDTO.class))
                .toList();
    }
}