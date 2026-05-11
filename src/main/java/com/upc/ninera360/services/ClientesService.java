package com.upc.ninera360.services;

import com.upc.ninera360.dtos.ClientesDTO;
import com.upc.ninera360.entities.Clientes;

import java.util.List;

public interface ClientesService {
    public ClientesDTO insertarCliente(ClientesDTO clientesDTO);
    public ClientesDTO actualizarCliente(ClientesDTO clientesDTO);
    public void eliminarCliente(long id);
    public List<ClientesDTO> listarClientes();
    public ClientesDTO buscarPorId(long id);
    public long count();
    //buscar por descripción
    public List<ClientesDTO> findByDescripcion(String descripcion);
}
