package com.upc.ninera360.entities;

import jakarta.persistence.*;
import lombok.*;
import com.upc.ninera360.security.entities.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nombre;
    private Integer dni;
    private String direccion;
    private Integer telefono;
    private String correo;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}