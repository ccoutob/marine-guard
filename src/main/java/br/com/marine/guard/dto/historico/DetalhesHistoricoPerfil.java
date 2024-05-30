package br.com.marine.guard.dto.historico;

import br.com.marine.guard.dto.perfil.DetalhesPerfil;
import br.com.marine.guard.model.Historico;

public record DetalhesHistoricoPerfil(Long codigo, Integer disponivel, Integer quantidadeTrocas,
                                      DetalhesPerfil perfil) {

    public DetalhesHistoricoPerfil(Historico historico){
        this(historico.getCodigo(), historico.getDisponivel(), historico.getQuantidadeTrocas(),
                new DetalhesPerfil(historico.getPerfil()));
    }
}
