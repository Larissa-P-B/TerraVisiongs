package br.com.api.terravision.dto;

import java.time.LocalDateTime;

public record ClimaDTO(

        String cidade,

        double temperatura,

        int umidade,

        double velocidadeVento,

        int direcaoVento,

        Double volumeChuva,

        double pressao,

        int nuvens,

        String nivelRisco,

        String tipoAlerta,

        String severidade,

        LocalDateTime dataLeitura

) {
}