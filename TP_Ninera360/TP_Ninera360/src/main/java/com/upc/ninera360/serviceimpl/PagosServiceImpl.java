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
    public PagosDTO insertarPago(PagosDTO pagosDTO){
        Pagos pagos = modelMapper.map(pagosDTO, Pagos.class);
        pagos = pagosRepositorio.save(pagos);
        return modelMapper.map(pagos, PagosDTO.class);
    }

    @Transactional
    @Override
    public PagosDTO actualizarPago(PagosDTO pagosDTO) {
        Pagos pagos = modelMapper.map(pagosDTO, Pagos.class);
        if (pagosRepositorio.findById(pagos.getIdPago()).isPresent()) {
            pagos = pagosRepositorio.save(pagos);
            return modelMapper.map(pagos, PagosDTO.class);}
            return null;

    }

    @Transactional
    @Override
    public void eliminarPago(long id) {
        if(pagosRepositorio.existsById(id)) {
            pagosRepositorio.deleteById(id);
        }
    }
    @Override
        public List<PagosDTO> listarPagos() {

            return pagosRepositorio.findAll()
                    .stream()
                    .map(p -> modelMapper.map(p, PagosDTO.class))
                    .toList();
        }
}
