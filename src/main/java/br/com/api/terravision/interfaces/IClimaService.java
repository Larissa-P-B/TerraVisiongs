package br.com.api.terravision.interfaces;

import br.com.api.terravision.dto.ClimaDTO;

import java.util.List;

public interface IClimaService {

    ClimaDTO buscarClima(String cidade);

    List<ClimaDTO> listarHistorico();

    ClimaDTO buscarPorId(Long id);

    List<ClimaDTO> buscarPorRisco(String risco);
}