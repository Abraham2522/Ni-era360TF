package com.upc.ninera360.controllers;

import com.upc.ninera360.dtos.PagosDTO;
import com.upc.ninera360.services.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagosController {
    @Autowired
    private PagosService pagosService;

    @GetMapping("/listar")
    public ResponseEntity<List<PagosDTO>> listarPagos(){
        return ResponseEntity.ok(pagosService.listarPagos());
    }

    @PostMapping("/agregar")
    public ResponseEntity<PagosDTO> insertarPago(@RequestBody PagosDTO pagoDTO){
        PagosDTO pagos = pagosService.insertarPago(pagoDTO);
        return ResponseEntity.ok(pagos);
    }

    @PutMapping("/editar")
    public ResponseEntity<PagosDTO> actualizarPago(@RequestBody PagosDTO pagoDTO){
        PagosDTO pagos = pagosService.actualizarPago(pagoDTO);
        return ResponseEntity.ok(pagoDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarPago(@PathVariable int id){pagosService.eliminarPago(id);}

}