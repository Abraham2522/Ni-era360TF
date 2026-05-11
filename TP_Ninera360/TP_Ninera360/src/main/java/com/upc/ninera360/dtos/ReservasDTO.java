package com.upc.ninera360.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservasDTO {
    private Long idReserva;
    private Long idCliente;
    private Long idCuidador;
    private ZonedDateTime horaInicio;
    private ZonedDateTime horaFin;
    private String estado;
}
