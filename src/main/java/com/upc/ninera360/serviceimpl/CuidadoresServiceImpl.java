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
        if(cuidadoresDTO.getIdCuidador()!=null && cuidadoresRepositorio.existsById(cuidadoresDTO.getIdCuidador())){
            throw new RuntimeException("El cuidador con ID" +  cuidadoresDTO.getIdCuidador() + " ya existe");
        }
        Cuidadores cuidadores = modelMapper.map(cuidadoresDTO, Cuidadores.class);
        cuidadores = cuidadoresRepositorio.save(cuidadores);
        return modelMapper.map(cuidadores, CuidadoresDTO.class);
    }

    @Transactional
    @Override
    public CuidadoresDTO actualizarCuidador(CuidadoresDTO cuidadoresDTO) {
        return cuidadoresRepositorio.findById(cuidadoresDTO.getIdCuidador())
                .map(existing ->{
                    Cuidadores cuidadores = modelMapper.map(existing, Cuidadores.class);
                    return modelMapper.map(cuidadoresRepositorio.save(cuidadores), CuidadoresDTO.class);
                })
                .orElseThrow(() ->new RuntimeException(String.format("El cuidador con ID %d no encontrado", cuidadoresDTO.getIdCuidador())));
    }

    @Transactional
    @Override
    public void eliminarCuidador(long id) {
        if (cuidadoresRepositorio.existsById(id)) {
            throw new RuntimeException("Cuidador no encontrado con ID:" + id);
        }
        cuidadoresRepositorio.deleteById(id);
    }

    @Override
    public List<CuidadoresDTO> listarCuidadores() {
        return cuidadoresRepositorio.findAll()
                .stream()
                .map(cuidadores -> modelMapper.map(cuidadores, CuidadoresDTO.class))
                .toList();
    }

    @Override
    public CuidadoresDTO buscarPorId(long id) {
        return cuidadoresRepositorio.findById(id)
                .map(cuidadores -> modelMapper.map(cuidadores, CuidadoresDTO.class))
                .orElseThrow(()->new RuntimeException("Cuidador no encontrado con ID: "+ id));
    }
}
