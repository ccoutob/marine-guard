package br.com.marine.guard.dto.brinde;

import br.com.marine.guard.dto.perfil.DetalhesPerfil;
import br.com.marine.guard.model.brinde.Brinde;
import br.com.marine.guard.model.brinde.TipoBrinde;

public record DetalhesBrinde(Long codigo, String nome, TipoBrinde tipoBrinde, DetalhesPerfil perfil) {

    public DetalhesBrinde(Brinde brinde){
        this(brinde.getCodigo(), brinde.getNome(), brinde.getTipoBrinde(),
                new DetalhesPerfil(brinde.getPerfil()));
    }

}
