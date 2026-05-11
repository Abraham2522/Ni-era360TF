package com.upc.ninera360.controllers;

import com.upc.ninera360.dtos.ResenasDTO;
import com.upc.ninera360.services.ResenasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resenas")
public class ResenasController {
    @Autowired
    private ResenasService resenasService;

    @GetMapping("/listar")
    public ResponseEntity<List<ResenasDTO>> listarResenas(){
        return ResponseEntity.ok(resenasService.listarResenas());
    }

    @PostMapping("/insertar")
    public ResponseEntity<ResenasDTO> insertarResenas(@RequestBody ResenasDTO resenasDTO){
        ResenasDTO resenas = resenasService.insertarResena(resenasDTO);
        return ResponseEntity.ok(resenas);
    }

    @PutMapping("/editar")
    public ResponseEntity<ResenasDTO> actualizarResenas(@RequestBody ResenasDTO resenasDTO){
        ResenasDTO resenas = resenasService.actualizarResena(resenasDTO);
        return ResponseEntity.ok(resenas);
    }

    @DeleteMapping("eliminar/{id}")
    public void eliminarResena(@PathVariable long id){resenasService.eliminarResena(id);}

    @GetMapping("/buscarpor/{id}")
    public ResponseEntity<ResenasDTO> buscarPorId(@PathVariable long id) {
        ResenasDTO resenasDTO = resenasService.buscarPorId(id);
        return ResponseEntity.ok(resenasDTO);
    }

}
