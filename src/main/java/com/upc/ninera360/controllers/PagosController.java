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
        return ResponseEntity.ok(pagos);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarPago(@PathVariable long id){pagosService.eliminarPago(id);}

    @GetMapping("/buscarpor/{id}")
    public ResponseEntity<PagosDTO> buscarPagoPorId(@PathVariable long id){
        PagosDTO pagosDTO= pagosService.buscarPorId(id);
        return ResponseEntity.ok(pagosDTO);
    }
    @GetMapping("/countPagos")
    public long count() {
        return pagosService.count();
    }

    @GetMapping("/estado/{estado}")
    public List<PagosDTO> findByEstadoPago(@PathVariable boolean estado) {
        return pagosService.findByEstadoPago(estado);
    }

}