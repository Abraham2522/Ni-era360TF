package com.upc.ninera360.controllers;

import com.upc.ninera360.dtos.ClientesDTO;
import com.upc.ninera360.services.ClientesService;
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
@RequestMapping("/clientes")
public class ClientesController {
    @Autowired
    private ClientesService clientesService;

    @GetMapping("/listar")
    public ResponseEntity<List<ClientesDTO>> listarClientes() {
        return ResponseEntity.ok(clientesService.listarClientes());
    }

    @PostMapping("/insertar")
    public ResponseEntity<ClientesDTO> insertarCliente(@RequestBody ClientesDTO clientesDTO) {
        ClientesDTO clientes = clientesService.insertarCliente(clientesDTO);
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/editar")
    public ResponseEntity<ClientesDTO> actualizarCliente(@RequestBody ClientesDTO clientesDTO) {
        ClientesDTO clientes = clientesService.actualizarCliente(clientesDTO);
        return ResponseEntity.ok(clientes);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarCliente(@PathVariable int id) {
        clientesService.eliminarCliente(id);
    }
}
