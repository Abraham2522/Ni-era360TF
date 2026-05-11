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
        if (resenasDTO.getIdResena() != null &&
                resenasRepositorio.existsById(resenasDTO.getIdResena())) {
            throw new RuntimeException(
                    "La reseña con ID " + resenasDTO.getIdResena() + " ya existe.");
        }

        Resenas resenas = modelMapper.map(resenasDTO, Resenas.class);
        resenas = resenasRepositorio.save(resenas);
        return modelMapper.map(resenas, ResenasDTO.class);
    }

    @Transactional
    @Override
    public ResenasDTO actualizarResena(ResenasDTO resenasDTO) {

        return resenasRepositorio.findById(resenasDTO.getIdResena())
                .map(existing -> {
                    Resenas resenas = modelMapper.map(resenasDTO, Resenas.class);
                    return modelMapper.map(
                            resenasRepositorio.save(resenas),
                            ResenasDTO.class);
                })
                .orElseThrow(() -> new RuntimeException(
                        String.format(
                                "Reseña con ID %d no encontrada",
                                resenasDTO.getIdResena())));
    }

    @Transactional
    @Override
    public void eliminarResena(long id) {
        if (!resenasRepositorio.existsById(id)) {
            throw new RuntimeException(
                    "Reseña no encontrada con ID " + id);
        }
        resenasRepositorio.deleteById(id);
    }

    @Override
    public List<ResenasDTO> listarResenas() {
        return resenasRepositorio.findAll()
                .stream()
                .map(resenas -> modelMapper.map(resenas, ResenasDTO.class))
                .toList();
    }

    @Override
    public ResenasDTO buscarPorId(long id) {
        return resenasRepositorio.findById(id)
                .map(resenas -> modelMapper.map(resenas, ResenasDTO.class))
                .orElseThrow(() -> new RuntimeException(
                        "Reseña no encontrada con ID: " + id));
    }
}