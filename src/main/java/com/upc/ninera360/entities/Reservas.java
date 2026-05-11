package com.upc.ninera360.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservas")
public class Reservas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Clientes cliente;

    @ManyToOne
    @JoinColumn(name = "id_cuidador")
    private Cuidadores cuidador;

    private OffsetDateTime horaInicio;
    private OffsetDateTime horaFin;
    private String estado;
}
