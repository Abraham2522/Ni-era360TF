package com.upc.ninera360.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientesDTO {
    private Long idUsuario;
    private Long idCliente;
    private String descripcion;
}
