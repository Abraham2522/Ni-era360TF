package com.upc.ninera360.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservas")
public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Clientes cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuidador", nullable = false)
    private Cuidadores cuidador;

    @Column(name = "hora_inicio", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private ZonedDateTime horaInicio;

    @Column(name = "hora_fin", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private ZonedDateTime horaFin;

    @Column(name = "estado", nullable = false, columnDefinition = "TEXT")
    private String estado;
}
