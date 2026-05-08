package com.upc.ninera360.security.controllers;

import com.upc.ninera360.security.entities.UserProfile;
import com.upc.ninera360.security.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("securityUserProfileController")
@RequestMapping("/api/profiles") // Esta será la dirección web
public class UserProfileController {

    @Autowired
    private UserProfileRepository repository;

    @PostMapping("/register")
    public ResponseEntity<UserProfile> registrarPerfil(@RequestBody UserProfile perfil) {
        UserProfile nuevoPerfil = repository.save(perfil);
        return ResponseEntity.ok(nuevoPerfil);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> obtenerPerfil(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
