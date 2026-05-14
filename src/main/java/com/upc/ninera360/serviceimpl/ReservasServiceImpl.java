package com.upc.ninera360.serviceimpl;

import com.upc.ninera360.dtos.ReservasDTO;
import com.upc.ninera360.entities.Reservas;
import com.upc.ninera360.repositories.ReservasRepositorio;
import com.upc.ninera360.services.ReservasService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservasServiceImpl implements ReservasService {

    @Autowired
    private ReservasRepositorio reservasRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public ReservasDTO insertarReserva(ReservasDTO reservasDTO) {
        if (reservasDTO.getIdReserva() != null && reservasRepositorio.existsById(reservasDTO.getIdReserva())) {
            throw new RuntimeException("La Reserva con ID " + reservasDTO.getIdReserva() + " ya existe.");
        }
        Reservas reservas = modelMapper.map(reservasDTO, Reservas.class);
        reservas = reservasRepositorio.save(reservas);
        return modelMapper.map(reservas, ReservasDTO.class);
    }

    @Transactional
    @Override
    public ReservasDTO actualizarReserva(ReservasDTO reservasDTO) {
        return reservasRepositorio.findById(reservasDTO.getIdReserva())
                .map(existing -> {
                    Reservas reservas = modelMapper.map(reservasDTO, Reservas.class);
                    return modelMapper.map(reservasRepositorio.save(reservas), ReservasDTO.class);
                })
                .orElseThrow(() -> new RuntimeException(String.format("Reservas con ID %d no encontrado", reservasDTO.getIdReserva())));
    }

    @Override
    @Transactional
    public void eliminarReserva(long id) {
        if (!reservasRepositorio.existsById(id)) {
            throw new RuntimeException("Reserva no encontrado con ID: " + id);
        }
        reservasRepositorio.deleteById(id);
    }

    @Override
    public List<ReservasDTO> listarReservas() {
        return reservasRepositorio.findAll()
                .stream()
                .map(reservas -> modelMapper.map(reservas, ReservasDTO.class))
                .toList();
    }

    @Override
    public ReservasDTO obtenerReservaPorId(long id) {
        return reservasRepositorio.findById(id)
                .map(reservas -> modelMapper.map(reservas, ReservasDTO.class))
                .orElseThrow(() -> new RuntimeException("Reserva no encontrado con ID: " + id));
    }
    @Override
    public long count() {
        long count = reservasRepositorio.count();
        if (count == 0) {
            throw new RuntimeException(
                    "No existen reservas registradas");
        }
        return count;
    }

    @Override
    public List<ReservasDTO> findByEstado(String estado) {
        List<Reservas> lista =
                reservasRepositorio.findByEstado(estado);
        if (lista.isEmpty()) {
            throw new RuntimeException(
                    "No existen reservas con ese estado");
        }
        return lista.stream()
                .map(r -> modelMapper.map(r, ReservasDTO.class))
                .toList();
    }
}
