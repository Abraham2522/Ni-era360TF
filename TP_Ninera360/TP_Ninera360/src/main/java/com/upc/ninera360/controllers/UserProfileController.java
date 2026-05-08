package com.upc.ninera360.controllers;

import com.upc.ninera360.dtos.UserProfileDTO;
import com.upc.ninera360.entities.UserProfile;
import com.upc.ninera360.services.UserProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UserProfileController {
    @Autowired
    private UserProfileService userService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public UserProfileDTO crear(@RequestBody UserProfileDTO dto) {
        UserProfile entidad = modelMapper.map(dto, UserProfile.class);
        return modelMapper.map(userService.guardar(entidad), UserProfileDTO.class);
    }

    @GetMapping
    public List<UserProfileDTO> listar() {
        return userService.listar().stream()
                .map(u -> modelMapper.map(u, UserProfileDTO.class))
                .collect(Collectors.toList());
    }
}
