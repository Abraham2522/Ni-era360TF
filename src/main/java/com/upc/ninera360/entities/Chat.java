package com.upc.ninera360.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chat")
    private Long idChat;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Clientes cliente;

    @ManyToOne
    @JoinColumn(name = "id_cuidador", nullable = false)
    private Cuidadores cuidador;

    private ZonedDateTime creadoEn;
}
