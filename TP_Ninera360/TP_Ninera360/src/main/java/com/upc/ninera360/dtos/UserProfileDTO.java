package com.upc.ninera360.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDTO {
    private Long idUsuario;
    private String nombre;
    private Integer dni;
    private String direccion;
    private Integer telefono;
    private String correo;
}
