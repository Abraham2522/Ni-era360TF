package com.upc.ninera360.serviceimpl;

import com.upc.ninera360.dtos.PagosDTO;
import com.upc.ninera360.entities.Pagos;
import com.upc.ninera360.repositories.PagosRepositorio;
import com.upc.ninera360.services.PagosService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagosServiceImpl implements PagosService {

    @Autowired
    private PagosRepositorio pagosRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public PagosDTO insertarPago(PagosDTO pagosDTO) {
        if (pagosDTO.getIdPago() != null && pagosRepositorio.existsById(pagosDTO.getIdPago())) {
            throw new RuntimeException("El Pago con ID " + pagosDTO.getIdPago() + " ya existe.");
        }
        Pagos pagos = modelMapper.map(pagosDTO, Pagos.class);
        pagos = pagosRepositorio.save(pagos);
        return modelMapper.map(pagos, PagosDTO.class);
    }

    @Transactional
    @Override
    public PagosDTO actualizarPago(PagosDTO pagosDTO) {
        return pagosRepositorio.findById(pagosDTO.getIdPago())
                .map(existing -> {
                    Pagos pagos = modelMapper.map(pagosDTO, Pagos.class);
                    return modelMapper.map(pagosRepositorio.save(pagos), PagosDTO.class);
                })
                .orElseThrow(() -> new RuntimeException(String.format("Pago con ID %d no encontrado", pagosDTO.getIdPago())));
    }

    @Transactional
    @Override
    public void eliminarPago(long id) {
        if (!pagosRepositorio.existsById(id)) {
            throw new RuntimeException("Pago no encontrado con ID " + id);
        }
        pagosRepositorio.deleteById(id);
    }

    @Override
    public List<PagosDTO> listarPagos() {

        return pagosRepositorio.findAll()
                .stream()
                .map(pagos -> modelMapper.map(pagos, PagosDTO.class))
                .toList();
    }

    @Override
    public PagosDTO buscarPorId(long id) {
        return pagosRepositorio.findById(id)
                .map(pagos -> modelMapper.map(pagos, PagosDTO.class))
                .orElseThrow(() -> new RuntimeException("Pagos no encontrado con ID: " + id));
    }
}
