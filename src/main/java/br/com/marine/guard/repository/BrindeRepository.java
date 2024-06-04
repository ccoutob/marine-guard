package br.com.marine.guard.repository;

import br.com.marine.guard.model.Usuario;
import br.com.marine.guard.model.brinde.Brinde;
import br.com.marine.guard.model.brinde.TipoBrinde;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BrindeRepository extends JpaRepository<Brinde, Long> {

    @Query("from Brinde c where c.tipoBrinde = :tipoBrinde")
    Page<Brinde> buscarPorTipoBrinde(@Param("tipoBrinde") TipoBrinde tipoBrinde, Pageable page);
}
