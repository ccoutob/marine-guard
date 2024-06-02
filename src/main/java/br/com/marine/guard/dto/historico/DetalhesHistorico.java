package br.com.marine.guard.dto.historico;

import br.com.marine.guard.dto.usuario.DetalhesUsuario;
import br.com.marine.guard.model.Historico;
import br.com.marine.guard.model.brinde.TipoBrinde;

import java.time.LocalDate;
import java.time.LocalTime;

public record DetalhesHistorico(Long codigo, Integer disponivel, LocalDate dataTroca, Integer quantidadeTrocas, DetalhesUsuario usuario) {

    public DetalhesHistorico(Historico historico){
        this(historico.getCodigo(), historico.getDisponivel(), historico.getDataTroca(), historico.getQuantidadeTrocas(), new DetalhesUsuario(historico.getUsuario()));
    }

}
