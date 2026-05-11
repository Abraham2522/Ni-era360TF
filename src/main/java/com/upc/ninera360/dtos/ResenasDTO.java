package com.upc.ninera360.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResenasDTO {
    private Long idResena;
    private int calificacion;
    private String comentario;
    private LocalDateTime creadoEn;
    private Long idReserva;
}
