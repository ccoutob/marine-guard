package br.com.marine.guard.dto.perfil;

import br.com.marine.guard.model.Perfil;

public record DetalhesPerfil(Long id, Integer Pontos) {

    public DetalhesPerfil(Perfil perfil){
        this(perfil.getCodigo(), perfil.getPontos());
    }

}
