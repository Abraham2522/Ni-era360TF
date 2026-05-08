package com.upc.ninera360.serviceimpl;

import com.upc.ninera360.dtos.MensajeDTO;
import com.upc.ninera360.entities.Chat;
import com.upc.ninera360.entities.Mensaje;
import com.upc.ninera360.entities.UserProfile;
import com.upc.ninera360.repositories.ChatRepository;
import com.upc.ninera360.repositories.MensajeRepository;
import com.upc.ninera360.repositories.UserProfileRepository;
import com.upc.ninera360.services.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserProfileRepository usuarioRepository;

    private MensajeDTO toDTO(Mensaje mensaje) {
        MensajeDTO dto = new MensajeDTO();
        dto.setIdMensaje(mensaje.getIdMensaje());
        dto.setIdChat(mensaje.getChat().getIdChat());
        dto.setIdUsuario(mensaje.getUsuario().getIdUsuario());
        dto.setContenido(mensaje.getContenido());
        dto.setFecha(mensaje.getFecha());
        return dto;
    }

    private Mensaje toEntity(MensajeDTO dto) {
        Mensaje mensaje = new Mensaje();
        Chat chat = chatRepository.findById(dto.getIdChat())
                .orElseThrow(() -> new RuntimeException("Chat no encontrado"));
        UserProfile usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        mensaje.setChat(chat);
        mensaje.setUsuario(usuario);
        mensaje.setContenido(dto.getContenido());
        mensaje.setFecha(dto.getFecha());
        return mensaje;
    }

    @Override
    public List<MensajeDTO> getAll() {
        return mensajeRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<MensajeDTO> getByChat(Long idChat) {
        return mensajeRepository.findByChat_IdChat(idChat).stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public MensajeDTO getById(Long id) {
        Mensaje mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mensaje no encontrado"));
        return toDTO(mensaje);
    }

    @Override
    public MensajeDTO create(MensajeDTO mensajeDTO) {
        Mensaje mensaje = toEntity(mensajeDTO);
        return toDTO(mensajeRepository.save(mensaje));
    }

    @Override
    public void delete(Long id) {
        mensajeRepository.deleteById(id);
    }
}