package br.com.api.terravision.controller;

import br.com.api.terravision.dto.CidadeRequestDTO;
import br.com.api.terravision.dto.ClimaDTO;

import br.com.api.terravision.service.ClimaServiceImpl;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clima")
@RequiredArgsConstructor
public class ClimaController {

    private final ClimaServiceImpl service;

    @PostMapping
    public ClimaDTO buscarClima(
            @RequestBody
            @Valid
            CidadeRequestDTO request
    ) {

        return service.buscarClima(request.cidade());
    }

    @GetMapping("/historico")
    public List<ClimaDTO> listarHistorico() {

        return service.listarHistorico();
    }

    @GetMapping("/historico/{id}" )
    public ClimaDTO buscarPorId(
            @PathVariable Long id
    ) {

        return service.buscarPorId(id);
    }

    @GetMapping("/risco/{risco}")
    public List<ClimaDTO> buscarPorRisco(
            @PathVariable String risco
    ) {

        return service.buscarPorRisco(risco);
    }
}