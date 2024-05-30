package br.com.marine.guard.dto.historico;

import br.com.marine.guard.model.Historico;

public record DetalhesHistorico(Long codigo, Integer disponivel, Integer quantidadeTrocas) {

    public DetalhesHistorico(Historico historico){
        this(historico.getCodigo(), historico.getDisponivel(), historico.getQuantidadeTrocas());
    }

}
