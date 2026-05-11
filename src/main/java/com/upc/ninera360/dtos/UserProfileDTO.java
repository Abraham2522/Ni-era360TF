package com.upc.ninera360.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private Long idUsuario;
    private String nombre;
    private Integer dni;
    private String direccion;
    private Integer telefono;
    private String correo;
}
