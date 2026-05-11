package com.upc.ninera360.controllers;

import com.upc.ninera360.dtos.ReservasDTO;
import com.upc.ninera360.services.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/obtener/{id}")
    public ResponseEntity<ReservasDTO> obtenerReservaPorId(@PathVariable long id) {
        ReservasDTO reserva = reservasService.obtenerReservaPorId(id);
        if (reserva == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<ReservasDTO>> listarPorCliente(@PathVariable long idCliente) {
        return ResponseEntity.ok(reservasService.listarReservasPorCliente(idCliente));
    }

    @GetMapping("/cuidador/{idCuidador}")
    public ResponseEntity<List<ReservasDTO>> listarPorCuidador(@PathVariable long idCuidador) {
        return ResponseEntity.ok(reservasService.listarReservasPorCuidador(idCuidador));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ReservasDTO>> listarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(reservasService.listarReservasPorEstado(estado));
    }

    @PostMapping("/insertar")
    public ResponseEntity<ReservasDTO> insertarReserva(@RequestBody ReservasDTO reservasDTO) {
        return ResponseEntity.ok(reservasService.insertarReserva(reservasDTO));
    }

    @PutMapping("/editar")
    public ResponseEntity<ReservasDTO> actualizarReserva(@RequestBody ReservasDTO reservasDTO) {
        ReservasDTO reservaActualizada = reservasService.actualizarReserva(reservasDTO);
        if (reservaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reservaActualizada);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable long id) {
        reservasService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
