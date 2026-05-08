package com.upc.ninera360.security.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "SecurityUserProfile")
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
