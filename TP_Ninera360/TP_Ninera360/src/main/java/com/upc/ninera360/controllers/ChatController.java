package com.upc.ninera360.controllers;

import com.upc.ninera360.dtos.ChatDTO;
import com.upc.ninera360.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public ResponseEntity<List<ChatDTO>> getAll() {
        return ResponseEntity.ok(chatService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(chatService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ChatDTO> create(@RequestBody ChatDTO chatDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chatService.create(chatDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        chatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
