package com.upc.ninera360.serviceimpl;

import com.upc.ninera360.dtos.ChatDTO;
import com.upc.ninera360.entities.*;
import com.upc.ninera360.repositories.ChatRepository;
import com.upc.ninera360.repositories.ClientesRepositorio;
import com.upc.ninera360.repositories.CuidadoresRepositorio;
import com.upc.ninera360.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ClientesRepositorio clienteRepository;

    @Autowired
    private CuidadoresRepositorio cuidadorRepository;

    private ChatDTO toDTO(Chat chat) {
        ChatDTO dto = new ChatDTO();
        dto.setIdChat(chat.getIdChat());
        dto.setIdCliente(chat.getCliente().getIdUsuario());
        dto.setIdCuidador(chat.getCuidador().getIdUsuario());
        return dto;
    }

    private Chat toEntity(ChatDTO dto) {
        Chat chat = new Chat();
        Clientes cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Cuidadores cuidador = cuidadorRepository.findById(dto.getIdCuidador())
                .orElseThrow(() -> new RuntimeException("Cuidador no encontrado"));
        chat.setCliente(cliente);
        chat.setCuidador(cuidador);
        return chat;
    }

    @Override
    public List<ChatDTO> getAll() {
        return chatRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public ChatDTO getById(Long id) {
        Chat chat = chatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat no encontrado"));
        return toDTO(chat);
    }

    @Override
    public ChatDTO create(ChatDTO chatDTO) {
        Chat chat = toEntity(chatDTO);
        return toDTO(chatRepository.save(chat));
    }

    @Override
    public void delete(Long id) {
        chatRepository.deleteById(id);
    }
}