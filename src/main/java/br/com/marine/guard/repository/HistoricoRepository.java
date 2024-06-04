package br.com.marine.guard.repository;

import br.com.marine.guard.model.Historico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    @Query("from Historico h where h.quantidadeTrocas = :quantidadeTrocas")
    Page<Historico> buscarPelaQuantidadeDeTrocas(@Param("quantidadeTrocas") Integer quantidadeTrocas, Pageable page);
}
