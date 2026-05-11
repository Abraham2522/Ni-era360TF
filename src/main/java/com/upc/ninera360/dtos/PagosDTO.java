package com.upc.ninera360.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PagosDTO {
    private Long idPago;
    private Long idReserva;
    private String montoPago;
    private boolean estadoPago;
    private String pagada_en;
}
