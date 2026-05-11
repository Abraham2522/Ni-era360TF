package com.upc.ninera360.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservasDTO {
    private Long idReserva;
    private Long idCliente;
    private Long idCuidador;
    private OffsetDateTime horaInicio;
    private OffsetDateTime horaFin;
    private String estado;
}
