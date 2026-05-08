package com.upc.ninera360.security.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDTO {
    private Long idUsuario; // Opcional para el registro, necesario para consultas
    private String nombre;
    private Integer dni;
    private String direccion;
    private Integer telefono;
    private String correo;
}
