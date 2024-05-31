package br.com.marine.guard.repository;

import br.com.marine.guard.model.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColetaRepository extends JpaRepository<PontoColeta, Long> {
}
