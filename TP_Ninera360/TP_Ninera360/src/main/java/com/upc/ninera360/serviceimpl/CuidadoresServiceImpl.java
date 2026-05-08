package com.upc.ninera360.serviceimpl;

import com.upc.ninera360.dtos.CuidadoresDTO;
import com.upc.ninera360.entities.Cuidadores;
import com.upc.ninera360.repositories.CuidadoresRepositorio;
import com.upc.ninera360.services.CuidadoresService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CuidadoresServiceImpl implements CuidadoresService {
    @Autowired
    private CuidadoresRepositorio cuidadoresRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public CuidadoresDTO insertarCuidador(CuidadoresDTO cuidadoresDTO) {
        Cuidadores cuidadores = modelMapper.map(cuidadoresDTO, Cuidadores.class);
        cuidadores = cuidadoresRepositorio.save(cuidadores);
        return modelMapper.map(cuidadores, CuidadoresDTO.class);
    }

    @Transactional
    @Override
    public CuidadoresDTO actualizarCuidador(CuidadoresDTO cuidadoresDTO) {
        Cuidadores cuidadores = modelMapper.map(cuidadoresDTO, Cuidadores.class);
        if (cuidadoresRepositorio.findById(cuidadores.getIdUsuario()).isPresent()) {
            cuidadores = cuidadoresRepositorio.save(cuidadores);
            return modelMapper.map(cuidadores, CuidadoresDTO.class);
        }
        return null;
    }

    @Transactional
    @Override
    public void eliminarCuidador(long id) {
        if (cuidadoresRepositorio.existsById(id)) {
            cuidadoresRepositorio.deleteById(id);
        }
    }

    @Override
    public List<CuidadoresDTO> listarCuidadores() {
        return cuidadoresRepositorio.findAll()
                .stream()
                .map(cuidadores -> modelMapper.map(cuidadores, CuidadoresDTO.class))
                .toList();
    }
}
