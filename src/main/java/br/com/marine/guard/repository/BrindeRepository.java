package br.com.marine.guard.repository;

import br.com.marine.guard.model.brinde.Brinde;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrindeRepository extends JpaRepository<Brinde, Long> {
}
