package com.upc.ninera360.serviceimpl;

import com.upc.ninera360.dtos.ResenasDTO;
import com.upc.ninera360.entities.Resenas;
import com.upc.ninera360.repositories.ResenasRepositorio;
import com.upc.ninera360.services.ResenasService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenasServiceImpl implements ResenasService {
    @Autowired
    private ResenasRepositorio resenasRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public ResenasDTO insertarResena(ResenasDTO resenasDTO) {
        Resenas resenas = modelMapper.map(resenasDTO, Resenas.class);
        resenas = resenasRepositorio.save(resenas);
        return modelMapper.map(resenas, ResenasDTO.class);
    }

    @Transactional
    @Override
    public ResenasDTO actualizarResena(ResenasDTO resenasDTO) {
        Resenas resenas = modelMapper.map(resenasDTO, Resenas.class);
        if (resenasRepositorio.findById(resenas.getIdResena()).isPresent()) {
            resenas = resenasRepositorio.save(resenas);
            return modelMapper.map(resenas, ResenasDTO.class);}
        return null;

    }

    @Transactional
    @Override
    public void eliminarResena(long id) {
        if(resenasRepositorio.existsById(id)) {
            resenasRepositorio.deleteById(id);
        }
    }

    @Override
    public List<ResenasDTO> listarResenas() {
        return resenasRepositorio.findAll()
                .stream()
                .map(resenas -> modelMapper.map(resenas, ResenasDTO.class))
                .toList();
    }
}
