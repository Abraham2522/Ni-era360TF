package com.upc.ninera360.controllers;

import com.upc.ninera360.dtos.UserProfileDTO;
import com.upc.ninera360.entities.UserProfile;
import com.upc.ninera360.services.UserProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/listar")
    public ResponseEntity<List<UserProfileDTO>> listar() {
        return ResponseEntity.ok(userProfileService.listar());
    }

    @PostMapping("/insertar")
    public ResponseEntity<UserProfileDTO> insertar(@RequestBody UserProfileDTO userProfileDTO) {
        UserProfileDTO userProfile = userProfileService.insertar(userProfileDTO);
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<UserProfileDTO> actualizar(@RequestBody UserProfileDTO userProfileDTO) {
        UserProfileDTO userProfile = userProfileService.actualizar(userProfileDTO);
        return ResponseEntity.ok(userProfileDTO);
    }
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable long id) {
        userProfileService.eliminar(id);
    }

    @GetMapping("/buscarpor/{id}")
    public ResponseEntity<UserProfileDTO> buscarPorId(@PathVariable long id) {
        UserProfileDTO usuarioProfileDTO = userProfileService.buscarPorId(id);
        return ResponseEntity.ok(usuarioProfileDTO);
    }
    @GetMapping("/countUsuarios")
    public long count() {
        return userProfileService.count();
    }
    @GetMapping("/nombre/{nombre}")
    public List<UserProfileDTO> findByNombre(
            @PathVariable String nombre) {
        return userProfileService.findByNombre(nombre);
    }
}
