package com.upc.ninera360.controllers;

import com.upc.ninera360.dtos.ReservasDTO;
import com.upc.ninera360.services.ReservasService;
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
@RequestMapping("/reservas")
public class ReservasController {

    @Autowired
    private ReservasService reservasService;

    @GetMapping("/listar")
    public ResponseEntity<List<ReservasDTO>> listarReservas() {
        return ResponseEntity.ok(reservasService.listarReservas());
    }

    @PostMapping("/agregar")
    public ResponseEntity<ReservasDTO> insertarReserva(@RequestBody ReservasDTO reservasDTO) {
        ReservasDTO reservas = reservasService.insertarReserva(reservasDTO);
        return ResponseEntity.ok(reservas);
    }

    @PutMapping("/editar")
    public ResponseEntity<ReservasDTO> actualizarReserva(@RequestBody ReservasDTO reservasDTO) {
        ReservasDTO reservas = reservasService.actualizarReserva(reservasDTO);
        return ResponseEntity.ok(reservas);
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminarReserva(@PathVariable long id) {
        reservasService.eliminarReserva(id);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ReservasDTO> obtenerReservaPorId(@PathVariable long id) {
        ReservasDTO reservasDTO = reservasService.obtenerReservaPorId(id);
        return ResponseEntity.ok(reservasDTO);
    }

}
