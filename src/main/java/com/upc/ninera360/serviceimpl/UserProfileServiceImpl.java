package com.upc.ninera360.serviceimpl;

import com.upc.ninera360.dtos.UserProfileDTO;
import com.upc.ninera360.entities.UserProfile;
import com.upc.ninera360.repositories.UserProfileRepository;
import com.upc.ninera360.services.UserProfileService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public UserProfileDTO insertar(UserProfileDTO userProfileDTO) {
        if(userProfileDTO.getIdUsuario()!= null && userProfileRepository.existsById(userProfileDTO.getIdUsuario())){
            throw new RuntimeException("El usuario con ID" + userProfileDTO.getIdUsuario() + " ya existe");
        }
        UserProfile userProfile = modelMapper.map(userProfileDTO, UserProfile.class);
        userProfile= userProfileRepository.save(userProfile);
        return modelMapper.map(userProfile, UserProfileDTO.class);
    }

    @Transactional
    @Override
    public UserProfileDTO actualizar(UserProfileDTO userProfileDTO) {
        return userProfileRepository.findById(userProfileDTO.getIdUsuario())
                .map(existing->{
                    UserProfile userProfile = modelMapper.map(existing, UserProfile.class);
                    return modelMapper.map(userProfileRepository.save(userProfile), UserProfileDTO.class);
                })
                .orElseThrow(() -> new RuntimeException(String.format("Usuario con ID %d no encontrado", userProfileDTO.getIdUsuario())));
    }

    @Transactional
    @Override
    public void eliminar(long id) {
        if(!userProfileRepository.existsById(id)){
            throw new RuntimeException("Usuario no encontrado con ID" + id);
        }
        userProfileRepository.deleteById(id);
    }

    @Override
    public List<UserProfileDTO> listar() {
        return userProfileRepository.findAll()
                .stream()
                .map(userProfile -> modelMapper.map(userProfile, UserProfileDTO.class))
                .toList();
    }

    @Transactional
    @Override
    public UserProfileDTO buscarPorId(long id) {
        return userProfileRepository.findById(id)
                .map(userProfile -> modelMapper.map(userProfile, UserProfileDTO.class))
                .orElseThrow(()->new RuntimeException("Usuario no encontrado con ID:" + id));
    }
    @Override
    public long count() {
        long count = userProfileRepository.count();
        if (count == 0) {
            throw new RuntimeException(
                    "No existen usuarios registrados");
        }
        return count;
    }

    @Override
    public List<UserProfileDTO> findByNombre(String nombre) {
        List<UserProfile> lista =
                userProfileRepository.findByNombre(nombre);
        if (lista.isEmpty()) {
            throw new RuntimeException(
                    "No existen usuarios con ese nombre");
        }
        return lista.stream()
                .map(usuario ->
                        modelMapper.map(usuario,
                                UserProfileDTO.class))
                .toList();
    }

}



