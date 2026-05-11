package com.upc.ninera360.serviceimpl;

import com.upc.ninera360.dtos.ReservasDTO;
import com.upc.ninera360.entities.Clientes;
import com.upc.ninera360.entities.Cuidadores;
import com.upc.ninera360.entities.Reservas;
import com.upc.ninera360.repositories.ClientesRepositorio;
import com.upc.ninera360.repositories.CuidadoresRepositorio;
import com.upc.ninera360.repositories.ReservasRepositorio;
import com.upc.ninera360.services.ReservasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservasServiceImpl implements ReservasService {

    @Autowired
    private ReservasRepositorio reservasRepositorio;

    @Autowired
    private ClientesRepositorio clientesRepositorio;

    @Autowired
    private CuidadoresRepositorio cuidadoresRepositorio;

    @Transactional
    @Override
    public ReservasDTO insertarReserva(ReservasDTO reservasDTO) {
        Reservas reserva = convertirAEntidad(reservasDTO);
        reserva = reservasRepositorio.save(reserva);
        return convertirADTO(reserva);
    }

    @Transactional
    @Override
    public ReservasDTO actualizarReserva(ReservasDTO reservasDTO) {
        if (reservasDTO.getIdReserva() == null || !reservasRepositorio.existsById(reservasDTO.getIdReserva())) {
            return null;
        }
        Reservas reserva = convertirAEntidad(reservasDTO);
        reserva = reservasRepositorio.save(reserva);
        return convertirADTO(reserva);
    }

    @Transactional
    @Override
    public void eliminarReserva(long id) {
        if (reservasRepositorio.existsById(id)) {
            reservasRepositorio.deleteById(id);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ReservasDTO obtenerReservaPorId(long id) {
        return reservasRepositorio.findById(id)
                .map(this::convertirADTO)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReservasDTO> listarReservas() {
        return reservasRepositorio.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReservasDTO> listarReservasPorCliente(long idCliente) {
        return reservasRepositorio.findByCliente_IdUsuario(idCliente)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReservasDTO> listarReservasPorCuidador(long idCuidador) {
        return reservasRepositorio.findByCuidador_IdUsuario(idCuidador)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReservasDTO> listarReservasPorEstado(String estado) {
        return reservasRepositorio.findByEstadoIgnoreCase(estado)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    private Reservas convertirAEntidad(ReservasDTO dto) {
        Clientes cliente = clientesRepositorio.findById(dto.getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("No existe cliente con id " + dto.getIdCliente()));

        Cuidadores cuidador = cuidadoresRepositorio.findById(dto.getIdCuidador())
                .orElseThrow(() -> new IllegalArgumentException("No existe cuidador con id " + dto.getIdCuidador()));

        Reservas reserva = new Reservas();
        reserva.setIdReserva(dto.getIdReserva());
        reserva.setCliente(cliente);
        reserva.setCuidador(cuidador);
        reserva.setHoraInicio(dto.getHoraInicio());
        reserva.setHoraFin(dto.getHoraFin());
        reserva.setEstado(dto.getEstado());
        return reserva;
    }

    private ReservasDTO convertirADTO(Reservas reserva) {
        ReservasDTO dto = new ReservasDTO();
        dto.setIdReserva(reserva.getIdReserva());
        dto.setIdCliente(reserva.getCliente().getIdUsuario());
        dto.setIdCuidador(reserva.getCuidador().getIdUsuario());
        dto.setHoraInicio(reserva.getHoraInicio());
        dto.setHoraFin(reserva.getHoraFin());
        dto.setEstado(reserva.getEstado());
        return dto;
    }
}
