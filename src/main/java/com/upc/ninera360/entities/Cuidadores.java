package com.upc.ninera360.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuidadores")
public class Cuidadores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuidador")
    private Long idCuidador;

    private String descripcion;
    private BigDecimal tarifa;
    private String experiencia;
    private String antecedentes;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private UserProfile usuario;
}
