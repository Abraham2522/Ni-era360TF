package com.upc.ninera360.controllers;

import com.upc.ninera360.dtos.CuidadoresDTO;
import com.upc.ninera360.dtos.MensajeDTO;
import com.upc.ninera360.services.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @GetMapping("/listar") //EndPoint
    public ResponseEntity<List<MensajeDTO>> listarMensajes() {
        return ResponseEntity.ok(mensajeService.listarMensajes());
    }

    @PostMapping("/insertar")
    public ResponseEntity<MensajeDTO> insertarMensaje(@RequestBody MensajeDTO mensajeDTO) {
        MensajeDTO mensaje = mensajeService.insertarMensaje(mensajeDTO);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/editar")
    public ResponseEntity<MensajeDTO>  editarMensaje(@RequestBody MensajeDTO mensajeDTO) {
        MensajeDTO mensaje = mensajeService.editarMensaje(mensajeDTO);
        return ResponseEntity.ok(mensajeDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarMensaje(@PathVariable long id) {
        mensajeService.eliminarMensaje(id);
    }

    @GetMapping("/buscarpor/{id}")
    public ResponseEntity<MensajeDTO> buscarPorId(@PathVariable long id) {
        MensajeDTO proveedorDTO = mensajeService.buscarPorId(id);
        return ResponseEntity.ok(proveedorDTO);
    }

    @GetMapping("/countMensajes")
    public long count() {
        return mensajeService.count();
    }

    @GetMapping("/contenido/{contenido}")
    public List<MensajeDTO> findByContenido(@PathVariable String contenido) {
        return mensajeService.findByContenido(contenido);
    }
}
