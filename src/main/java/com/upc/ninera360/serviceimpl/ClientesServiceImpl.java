package com.upc.ninera360.serviceimpl;

import com.upc.ninera360.dtos.ClientesDTO;
import com.upc.ninera360.dtos.UserProfileDTO;
import com.upc.ninera360.entities.Clientes;
import com.upc.ninera360.entities.UserProfile;
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
        if (clientesDTO.getIdCliente() != null && clientesRepositorio.existsById(clientesDTO.getIdCliente())) {
            throw new RuntimeException("El cliente con ID" + clientesDTO.getIdCliente() + " ya existe");
        }
        Clientes clientes = modelMapper.map(clientesDTO, Clientes.class);
        clientes = clientesRepositorio.save(clientes);
        return modelMapper.map(clientes, ClientesDTO.class);
    }

    @Transactional
    @Override
    public ClientesDTO actualizarCliente(ClientesDTO clientesDTO) {
        return clientesRepositorio.findById(clientesDTO.getIdCliente())
                .map(existing -> {
                    Clientes clientes = modelMapper.map(existing, Clientes.class);
                    return modelMapper.map(clientesRepositorio.save(clientes), ClientesDTO.class);
                })
                .orElseThrow(() -> new RuntimeException(String.format("Cliente con ID %d no encontrado", clientesDTO.getIdCliente())));
    }


    @Transactional
    @Override
    public void eliminarCliente(long id) {
        if(!clientesRepositorio.existsById(id)){
            throw new RuntimeException("Cliente no encontrado con ID" + id);
        }
        clientesRepositorio.deleteById(id);
    }

    @Override
    public List<ClientesDTO> listarClientes() {
        return clientesRepositorio.findAll()
                .stream()
                .map(clientes -> modelMapper.map(clientes, ClientesDTO.class))
                .toList();
    }

    @Transactional
    @Override
    public ClientesDTO buscarPorId(long id) {
        return clientesRepositorio.findById(id)
                .map(clientes -> modelMapper.map(clientes, ClientesDTO.class))
                .orElseThrow(()->new RuntimeException("Cliente no encontrado con ID:" + id));
    }

    @Override
    public long count() {
        long count = clientesRepositorio.count();

        if (count == 0) {
            throw new RuntimeException("No existen clientes registrados");
        }

        return count;
    }

    @Override
    public List<ClientesDTO> findByDescripcion(String descripcion) {
        List<Clientes> lista = clientesRepositorio.findByDescripcion(descripcion);

        if (lista.isEmpty()) {
            throw new RuntimeException("No existen clientes con esa descripción");
        }

        return lista.stream()
                .map(c -> modelMapper.map(c, ClientesDTO.class))
                .toList();
    }
}
