package com.upc.ninera360.services;

import com.upc.ninera360.dtos.ResenasDTO;
import com.upc.ninera360.dtos.UserProfileDTO;
import com.upc.ninera360.entities.UserProfile;

import java.util.List;

public interface UserProfileService {
    public UserProfileDTO insertar(UserProfileDTO userProfileDTO);
    public UserProfileDTO actualizar(UserProfileDTO userProfileDTO);
    public void eliminar(long id);
    public List<UserProfileDTO> listar();
    public UserProfileDTO buscarPorId(long id);
}

