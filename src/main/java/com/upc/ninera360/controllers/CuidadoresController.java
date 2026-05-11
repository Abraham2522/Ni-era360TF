package com.upc.ninera360.controllers;

import com.upc.ninera360.dtos.CuidadoresDTO;
import com.upc.ninera360.services.CuidadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(cuidadoresDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarCuidador(@PathVariable long id) {
        cuidadoresService.eliminarCuidador(id);
    }

    @GetMapping("/buscarpor/{id}")
    public ResponseEntity<CuidadoresDTO> buscarPorId(@PathVariable long id) {
        CuidadoresDTO cuidadoresDTO = cuidadoresService.buscarPorId(id);
        return ResponseEntity.ok(cuidadoresDTO);
    }
}
