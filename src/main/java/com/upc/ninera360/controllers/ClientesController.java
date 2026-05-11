package com.upc.ninera360.controllers;

import com.upc.ninera360.dtos.ClientesDTO;
import com.upc.ninera360.services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(clientesDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarCliente(@PathVariable long id) {
        clientesService.eliminarCliente(id);
    }

    @GetMapping("/buscarpor/{id}")
    public ResponseEntity<ClientesDTO> buscarPorId(@PathVariable long id) {
        ClientesDTO clientesDTO = clientesService.buscarPorId(id);
        return ResponseEntity.ok(clientesDTO);
    }
    @GetMapping("/countClientes")
    public long count() {
        return clientesService.count();
    }

    @GetMapping("/descripcion/{descripcion}")
    public List<ClientesDTO> findByDescripcion(@PathVariable String descripcion) {
        return clientesService.findByDescripcion(descripcion);
    }
}
