package com.upc.ninera360.controllers;

import com.upc.ninera360.dtos.MensajeDTO;
import com.upc.ninera360.services.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mensajes")
@CrossOrigin(origins = "*")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @GetMapping
    public ResponseEntity<List<MensajeDTO>> getAll() {
        return ResponseEntity.ok(mensajeService.getAll());
    }

    @GetMapping("/chat/{idChat}")
    public ResponseEntity<List<MensajeDTO>> getByChat(@PathVariable Long idChat) {
        return ResponseEntity.ok(mensajeService.getByChat(idChat));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mensajeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MensajeDTO> create(@RequestBody MensajeDTO mensajeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mensajeService.create(mensajeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mensajeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
