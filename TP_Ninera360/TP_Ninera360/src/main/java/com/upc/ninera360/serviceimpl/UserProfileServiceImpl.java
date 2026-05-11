package com.upc.ninera360.serviceimpl;

import com.upc.ninera360.entities.UserProfile;
import com.upc.ninera360.repositories.UserProfileRepository;
import com.upc.ninera360.services.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Transactional
    @Override
    public UserProfile guardar(UserProfile perfil) {
        return userProfileRepository.save(perfil);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserProfile> listar() {
        return userProfileRepository.findAll();
    }
}
