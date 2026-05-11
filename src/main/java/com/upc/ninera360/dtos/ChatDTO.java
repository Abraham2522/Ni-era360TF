package com.upc.ninera360.dtos;

import lombok.*;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    private Long idChat;
    private Long idCliente;
    private Long idCuidador;
    private ZonedDateTime creadoEn;
}