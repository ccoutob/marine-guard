package br.com.marine.guard.repository;

import br.com.marine.guard.model.Residuo.Residuo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ResiduoRepository extends JpaRepository<Residuo, Long> {

    @Query("from Residuo r where r.dataEnvio = :dataEnvio")
    Page<Residuo> buscarPorDataEnvio(@Param("dataEnvio") LocalDate dataEnvio, Pageable pageable);
}
