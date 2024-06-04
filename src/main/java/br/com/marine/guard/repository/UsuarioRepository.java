package br.com.marine.guard.repository;

import br.com.marine.guard.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("from Usuario c where c.nome like %:nome%")
    Page<Usuario> buscarPorNome(@Param("nome") String nome, Pageable page);

    @Query("from Usuario c where c.email like %:email%")
    Page<Usuario> buscarPorEmail(@Param("email") String email, Pageable page);
}
