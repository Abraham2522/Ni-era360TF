package com.upc.ninera360.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    private Long idChat;
    private Long idCliente;
    private Long idCuidador;
}