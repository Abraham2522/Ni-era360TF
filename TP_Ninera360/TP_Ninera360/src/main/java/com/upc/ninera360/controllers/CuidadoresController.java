package com.upc.ninera360.controllers;

import com.upc.ninera360.dtos.CuidadoresDTO;
import com.upc.ninera360.services.CuidadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cuidadores")
public class CuidadoresController {
    @Autowired
    private CuidadoresService cuidadoresService;

    @GetMapping("/listar")
    public ResponseEntity<List<CuidadoresDTO>> listarCuidadores() {
        return ResponseEntity.ok(cuidadoresService.listarCuidadores());
    }

    @PostMapping("/insertar")
    public ResponseEntity<CuidadoresDTO> insertarCuidador(@RequestBody CuidadoresDTO cuidadoresDTO) {
        CuidadoresDTO cuidadores = cuidadoresService.insertarCuidador(cuidadoresDTO);
        return ResponseEntity.ok(cuidadores);
    }

    @PutMapping("/editar")
    public ResponseEntity<CuidadoresDTO> actualizarCuidador(@RequestBody CuidadoresDTO cuidadoresDTO) {
        CuidadoresDTO cuidadores = cuidadoresService.actualizarCuidador(cuidadoresDTO);
        return ResponseEntity.ok(cuidadores);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarCuidador(@PathVariable int id) {
        cuidadoresService.eliminarCuidador(id);
    }
}
