package br.com.marine.guard.dto.usuario;

import br.com.marine.guard.dto.historico.DetalhesHistorico;
import br.com.marine.guard.model.Usuario;

public record DetalhesUsuario(Long id, String nome, String email, String senha) {

    public DetalhesUsuario(Usuario usuario){
        this(usuario.getCodigo(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }

}
