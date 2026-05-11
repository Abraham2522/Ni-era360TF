package com.upc.ninera360.controllers;

import com.upc.ninera360.dtos.ChatDTO;
import com.upc.ninera360.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/listar") //EndPoint
    public ResponseEntity<List<ChatDTO>> listarChats() {
        return ResponseEntity.ok(chatService.listarChats());
    }

    @PostMapping("/insertar")
    public ResponseEntity<ChatDTO> insertarChat(@RequestBody ChatDTO chatDTO) {
        ChatDTO chat = chatService.insertarChat(chatDTO);
        return ResponseEntity.ok(chat);
    }

    @PutMapping("/editar")
    public ResponseEntity<ChatDTO>  actualizarChat(@RequestBody ChatDTO chatDTO) {
        ChatDTO chat = chatService.actualizarChat(chatDTO);
        return ResponseEntity.ok(chatDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarChat(@PathVariable long id) {
        chatService.eliminarChat(id);
    }

    @GetMapping("/buscarpor/{id}")
    public ResponseEntity<ChatDTO> buscarPorId(@PathVariable long id) {
        ChatDTO chatDTO = chatService.buscarPorId(id);
        return ResponseEntity.ok(chatDTO);
    }
    @GetMapping("/countChats")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(chatService.count());
    }

    @GetMapping("/chatsPorCliente/{idCliente}")
    public ResponseEntity<List<ChatDTO>> findByCliente_IdCliente(@PathVariable long idCliente) {
        return ResponseEntity.ok(chatService.findByCliente_IdCliente(idCliente));
    }

}
