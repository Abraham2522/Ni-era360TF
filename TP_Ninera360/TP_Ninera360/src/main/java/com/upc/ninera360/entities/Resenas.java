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
@Table(name="resenas")
public class Resenas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResena;
    private int calificacion;
    private String comentario;
    private String creadoEn;

    //@ManyToOne
    //@JoinColumn(name="id_reserva")
    //  private
}
