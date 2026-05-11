package com.upc.ninera360.dtos;

import lombok.*;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDTO {
    private Long idMensaje;
    private Long idChat;
    private Long idUsuario;
    private String contenido;
    private ZonedDateTime fecha;
}
