package com.upc.ninera360.serviceimpl;

import com.upc.ninera360.dtos.ChatDTO;
import com.upc.ninera360.entities.Chat;
import com.upc.ninera360.entities.Clientes;
import com.upc.ninera360.entities.Cuidadores;
import com.upc.ninera360.repositories.ChatRepository;
import com.upc.ninera360.repositories.ClientesRepositorio;
import com.upc.ninera360.repositories.CuidadoresRepositorio;
import com.upc.ninera360.services.ChatService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public ChatDTO insertarChat(ChatDTO chatDTO) {
        if (chatDTO.getIdChat() != null && chatRepository.existsById(chatDTO.getIdChat())) {
            throw new RuntimeException("El chat con ID " + chatDTO.getIdChat() + " ya existe.");
        }
        Chat chat = modelMapper.map(chatDTO, Chat.class);
        chat = chatRepository.save(chat);
        return modelMapper.map(chat, ChatDTO.class);
    }

    @Transactional
    @Override
    public ChatDTO actualizarChat(ChatDTO chatDTO) {
        return chatRepository.findById(chatDTO.getIdChat())
                .map(existing -> {
                    Chat chat = modelMapper.map(chatDTO, Chat.class);
                    return modelMapper.map(chatRepository.save(chat), ChatDTO.class);
                })
                .orElseThrow(() -> new RuntimeException(String.format("Chat con ID %d no encontrado", chatDTO.getIdChat())));
    }

    @Override
    @Transactional
    public void eliminarChat(long id) {
        if (!chatRepository.existsById(id)) {
            throw new RuntimeException("Chat no encontrado con ID: " + id);
        }
        chatRepository.deleteById(id);
    }

    @Override
    public List<ChatDTO> listarChats() {
        return chatRepository.findAll()
                .stream()
                .map(chat -> modelMapper.map(chat, ChatDTO.class))
                .toList();
    }

    @Override
    public ChatDTO buscarPorId(long id) {
        return chatRepository.findById(id)
                .map(chat -> modelMapper.map(chat, ChatDTO.class))
                .orElseThrow(() -> new RuntimeException("Chat no encontrado con ID: " + id));
    }

    //consultas
    public long count() {
        long count =  chatRepository.count();
        if (count == 0) {
            throw new RuntimeException("No existen chats");
        }
        return count;
    }

    //chats por cliente
    public List<ChatDTO> findByCliente_IdCliente(long idCliente) {
        List<Chat> chats = chatRepository.findByCliente_IdCliente(idCliente);
        if (chats.isEmpty()) {
            throw new RuntimeException("No existen chats para el cliente con ID: " + idCliente);
        }
        return chats.stream()
                .map(chat -> modelMapper.map(chat, ChatDTO.class))
                .toList();
    }
}