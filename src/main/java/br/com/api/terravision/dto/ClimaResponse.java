package br.com.api.terravision.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClimaResponse(

        String name,

        Main main,

        Wind wind,

        Rain rain,

        Clouds clouds

) {

    public record Main(

            double temp,

            int humidity,

            double pressure

    ) {
    }

    public record Wind(

            double speed,

            int deg

    ) {
    }

    public record Rain(

            @JsonProperty("1h")
            Double oneHour

    ) {
    }

    public record Clouds(

            int all

    ) {
    }
}