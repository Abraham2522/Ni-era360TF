package com.upc.ninera360.serviceimpl;

import com.upc.ninera360.dtos.ClientesDTO;
import com.upc.ninera360.entities.Clientes;
import com.upc.ninera360.repositories.ClientesRepositorio;
import com.upc.ninera360.services.ClientesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientesServiceImpl implements ClientesService {
    @Autowired
    private ClientesRepositorio clientesRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public ClientesDTO insertarCliente(ClientesDTO clientesDTO) {
        Clientes clientes = modelMapper.map(clientesDTO, Clientes.class);
        clientes = clientesRepositorio.save(clientes);
        return modelMapper.map(clientes, ClientesDTO.class);
    }

    @Transactional
    @Override
    public ClientesDTO actualizarCliente(ClientesDTO clientesDTO) {
        Clientes clientes = modelMapper.map(clientesDTO, Clientes.class);
        if (clientesRepositorio.findById(clientes.getIdUsuario()).isPresent()) {
            clientes = clientesRepositorio.save(clientes);
            return modelMapper.map(clientes, ClientesDTO.class);
        }
        return null;
    }

    @Transactional
    @Override
    public void eliminarCliente(long id) {
        if (clientesRepositorio.existsById(id)) {
            clientesRepositorio.deleteById(id);
        }
    }

    @Override
    public List<ClientesDTO> listarClientes() {
        return clientesRepositorio.findAll()
                .stream()
                .map(clientes -> modelMapper.map(clientes, ClientesDTO.class))
                .toList();
    }
}
