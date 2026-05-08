package com.upc.ninera360.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String montoPago;
    private boolean estadoPago;
    private String pagadaEn;

    //@ManyToOne
    //@JoinColumn(name="id_reserva")
  //  private

}
