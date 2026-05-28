package br.com.api.terravision.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "regioes_monitoradas")

@Getter
@Setter
@NoArgsConstructor

public class RegiaoMonitorada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCidade;

    private Double temperatura;

    private Integer umidade;

    private Double velocidadeVento;

    private Integer direcaoVento;

    private Double volumeChuva;

    private Double pressao;

    private Integer nuvens;

    private String nivelRisco;

    private String tipoAlerta;

    private LocalDateTime dataLeitura;


    private String severidade;

    @PrePersist
    public void prePersist() {
        dataLeitura = LocalDateTime.now();
    }


}