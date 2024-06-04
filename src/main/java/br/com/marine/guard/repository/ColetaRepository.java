package br.com.marine.guard.repository;

import br.com.marine.guard.model.PontoColeta;
import br.com.marine.guard.model.brinde.Brinde;
import br.com.marine.guard.model.brinde.TipoBrinde;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ColetaRepository extends JpaRepository<PontoColeta, Long> {

    @Query("from PontoColeta pc where pc.cep = :cep")
    Page<PontoColeta> buscarPorCep(@Param("cep") String cep, Pageable page);

}
