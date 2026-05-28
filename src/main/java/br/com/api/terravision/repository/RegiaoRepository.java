package br.com.api.terravision.repository;

import br.com.api.terravision.entity.RegiaoMonitorada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegiaoRepository extends JpaRepository<RegiaoMonitorada, Long> {

    List<RegiaoMonitorada> findByNivelRisco(String nivelRisco);


}