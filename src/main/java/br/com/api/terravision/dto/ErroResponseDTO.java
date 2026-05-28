package br.com.api.terravision.dto;

import java.time.LocalDateTime;

public record ErroResponseDTO(

        LocalDateTime timestamp,

        Integer status,

        String erro,

        String mensagem,

        String caminho

) {
}