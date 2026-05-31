package br.com.api.terravision.service;

import br.com.api.terravision.client.OpenWeatherClient;
import br.com.api.terravision.dto.ClimaDTO;
import br.com.api.terravision.dto.ClimaResponse;
import br.com.api.terravision.entity.RegiaoMonitorada;
import br.com.api.terravision.interfaces.IClimaService;
import br.com.api.terravision.repository.RegiaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClimaServiceImpl implements IClimaService {

    private final OpenWeatherClient client;

    private final RegiaoRepository repository;

    @Override
    public ClimaDTO buscarClima(String cidade) {

        log.info("Consultando clima da cidade: {}", cidade);

        ClimaResponse response = client.buscarClima(cidade);

        log.info("Dados climáticos recebidos com sucesso.");

        double temperatura = response.main().temp();

        int umidade = response.main().humidity();

        double pressao = response.main().pressure();

        double velocidadeVento = response.wind().speed();

        int direcaoVento = response.wind().deg();

        int nuvens = response.clouds().all();

        Double volumeChuva = 0.0;

        if (response.rain() != null &&
                response.rain().oneHour() != null) {

            volumeChuva = response.rain().oneHour();
        }

        String risco = calcularRisco(
                temperatura,
                umidade,
                velocidadeVento,
                volumeChuva,
                pressao,
                nuvens
        );

        String severidade = calcularSeveridade(risco);

        log.warn("Risco climático identificado: {}", risco);

        RegiaoMonitorada regiao = new RegiaoMonitorada();

        regiao.setNomeCidade(cidade);

        regiao.setTemperatura(temperatura);

        regiao.setUmidade(umidade);

        regiao.setVelocidadeVento(velocidadeVento);

        regiao.setDirecaoVento(direcaoVento);

        regiao.setVolumeChuva(volumeChuva);

        regiao.setPressao(pressao);

        regiao.setNuvens(nuvens);

        regiao.setNivelRisco(risco);

        regiao.setTipoAlerta(risco);

        regiao.setSeveridade(severidade);

        repository.save(regiao);

        log.info("Registro salvo no banco com sucesso.");

        return new ClimaDTO(
                cidade,
                temperatura,
                umidade,
                velocidadeVento,
                direcaoVento,
                volumeChuva,
                pressao,
                nuvens,
                risco,
                risco,
                severidade,
                regiao.getDataLeitura()
        );
    }

    @Override
    public List<ClimaDTO> listarHistorico() {

        log.info("Listando histórico climático.");

        return repository.findAll()
                .stream()
                .map(regiao -> new ClimaDTO(
                        regiao.getNomeCidade(),
                        regiao.getTemperatura(),
                        regiao.getUmidade(),
                        regiao.getVelocidadeVento(),
                        regiao.getDirecaoVento(),
                        regiao.getVolumeChuva(),
                        regiao.getPressao(),
                        regiao.getNuvens(),
                        regiao.getNivelRisco(),
                        regiao.getTipoAlerta(),
                        regiao.getSeveridade(),
                        regiao.getDataLeitura()
                ))
                .toList();
    }

    @Override
    public ClimaDTO buscarPorId(Long id) {

        log.info("Buscando registro por ID: {}", id);

        RegiaoMonitorada regiao = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Registro não encontrado."));

        return new ClimaDTO(
                regiao.getNomeCidade(),
                regiao.getTemperatura(),
                regiao.getUmidade(),
                regiao.getVelocidadeVento(),
                regiao.getDirecaoVento(),
                regiao.getVolumeChuva(),
                regiao.getPressao(),
                regiao.getNuvens(),
                regiao.getNivelRisco(),
                regiao.getTipoAlerta(),
                regiao.getSeveridade(),
                regiao.getDataLeitura()
        );
    }

    @Override
    public List<ClimaDTO> buscarPorRisco(String risco) {

        log.info("Buscando registros por risco: {}", risco);

        return repository.findByNivelRisco(risco)
                .stream()
                .map(regiao -> new ClimaDTO(
                        regiao.getNomeCidade(),
                        regiao.getTemperatura(),
                        regiao.getUmidade(),
                        regiao.getVelocidadeVento(),
                        regiao.getDirecaoVento(),
                        regiao.getVolumeChuva(),
                        regiao.getPressao(),
                        regiao.getNuvens(),
                        regiao.getNivelRisco(),
                        regiao.getTipoAlerta(),
                        regiao.getSeveridade(),
                        regiao.getDataLeitura()
                ))
                .toList();
    }

    private String calcularRisco(
            double temperatura,
            int umidade,
            double vento,
            double chuva,
            double pressao,
            int nuvens
    ) {

        if (vento >= 120 &&
                pressao <= 980 &&
                nuvens >= 90) {

            return "RISCO DE TORNADO";
        }

        if (vento >= 100 &&
                pressao <= 970) {

            return "CICLONE";
        }

        if (chuva >= 60 &&
                vento >= 60 &&
                nuvens >= 80) {

            return "TEMPESTADE SEVERA";
        }

        if (chuva >= 100) {

            return "ALAGAMENTO EXTREMO";
        }

        if (chuva >= 50) {

            return "RISCO DE ALAGAMENTO";
        }

        if (vento >= 70) {

            return "VENDAVAL";
        }

        if (temperatura >= 42) {

            return "ONDA DE CALOR EXTREMA";
        }

        if (temperatura >= 36) {

            return "ONDA DE CALOR";
        }

        if (temperatura >= 35 &&
                umidade <= 20) {

            return "SECA EXTREMA";
        }

        if (temperatura >= 33 &&
                umidade <= 30 &&
                vento >= 30) {

            return "RISCO DE INCÊNDIO";
        }

        if (temperatura <= 5 &&
                vento >= 40) {

            return "FRENTE FRIA SEVERA";
        }

        if (pressao <= 990 &&
                nuvens >= 80) {

            return "INSTABILIDADE CLIMÁTICA";
        }

        return "NORMAL";
    }

    private String calcularSeveridade(String risco) {

        return switch (risco) {

            case "NORMAL" -> "BAIXO";

            case "INSTABILIDADE CLIMÁTICA",
                 "ONDA DE CALOR",
                 "RISCO DE ALAGAMENTO" -> "MODERADO";

            case "VENDAVAL",
                 "SECA EXTREMA",
                 "RISCO DE INCÊNDIO" -> "ALTO";

            case "TEMPESTADE SEVERA",
                 "ALAGAMENTO EXTREMO",
                 "FRENTE FRIA SEVERA" -> "CRÍTICO";

            case "RISCO DE TORNADO",
                 "CICLONE",
                 "ONDA DE CALOR EXTREMA" -> "EXTREMO";

            default -> "DESCONHECIDO";
        };
    }
}