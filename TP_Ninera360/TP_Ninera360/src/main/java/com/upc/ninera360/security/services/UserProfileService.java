package com.upc.ninera360.security.services;

import com.upc.ninera360.security.entities.UserProfile;
import com.upc.ninera360.security.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("securityUserProfileService")
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    // Método para guardar/registrar
    public UserProfile guardar(UserProfile perfil) {
        return userProfileRepository.save(perfil);
    }

    // Método para listar a todos (útil para pruebas)
    public List<UserProfile> listarTodo() {
        return userProfileRepository.findAll();
    }

    // Método para buscar por DNI
    public UserProfile buscarPorDni(Integer dni) {
        return userProfileRepository.findByDni(dni).orElse(null);
    }
}
