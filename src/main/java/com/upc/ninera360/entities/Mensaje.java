package com.upc.ninera360.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Long idMensaje;

    @ManyToOne
    @JoinColumn(name = "id_chat", nullable = false)
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserProfile usuario;

    private String contenido;
    private ZonedDateTime fecha;
}