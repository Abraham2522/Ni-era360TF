package com.upc.ninera360.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pagos")
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pago")
    private Long idPago;
    private BigDecimal montoPago;
    private boolean estadoPago;
    private LocalDateTime pagadaEn;

   @ManyToOne
   @JoinColumn(name="id_reserva")
   private Reservas reservas;

}
