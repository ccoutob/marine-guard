package br.com.marine.guard.dto.brinde;

import br.com.marine.guard.dto.usuario.DetalhesUsuario;
import br.com.marine.guard.model.brinde.Brinde;
import br.com.marine.guard.model.brinde.TipoBrinde;

public record DetalhesBrinde(Long codigo, String nome, TipoBrinde tipoBrinde, DetalhesUsuario usuario) {

    public DetalhesBrinde(Brinde brinde){
        this(brinde.getCodigo(), brinde.getNome(), brinde.getTipoBrinde(),
                new DetalhesUsuario(brinde.getUsuario()));
    }

}
