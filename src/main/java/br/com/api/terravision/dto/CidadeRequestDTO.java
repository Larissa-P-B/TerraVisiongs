package br.com.api.terravision.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CidadeRequestDTO(

        @NotBlank(message = "Cidade é obrigatória")

        @Size(
                min = 2,
                max = 100,
                message = "Cidade deve ter entre 2 e 100 caracteres"
        )

        String cidade
) {
}