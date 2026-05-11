package com.upc.ninera360.services;

import com.upc.ninera360.dtos.ChatDTO;

import java.util.List;

public interface ChatService {
    public ChatDTO insertarChat(ChatDTO chatDTO);
    public ChatDTO actualizarChat(ChatDTO chatDTO);
    public void eliminarChat(long id);
    public List<ChatDTO> listarChats();
    public ChatDTO buscarPorId(long id);
    // consulta chats por cliente
    public List<ChatDTO> findByCliente_IdCliente(long idCliente);
    // count
    public long count();

}