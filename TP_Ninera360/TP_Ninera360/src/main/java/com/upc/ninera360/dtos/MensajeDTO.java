package com.upc.ninera360.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDTO {
    private Long idMensaje;
    private Long idChat;
    private Long idUsuario;
    private String contenido;
    private ZonedDateTime fecha;
}
