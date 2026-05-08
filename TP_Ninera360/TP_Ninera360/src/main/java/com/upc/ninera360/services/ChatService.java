package com.upc.ninera360.services;

import com.upc.ninera360.dtos.ChatDTO;
import java.util.List;

public interface ChatService {
    List<ChatDTO> getAll();
    ChatDTO getById(Long id);
    ChatDTO create(ChatDTO chatDTO);
    void delete(Long id);
}