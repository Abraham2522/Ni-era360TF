package com.upc.ninera360.services;

import com.upc.ninera360.entities.UserProfile;
import java.util.List;

public interface UserProfileService {
    public UserProfile guardar(UserProfile perfil);
    public List<UserProfile> listar();
}
