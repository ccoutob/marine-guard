package br.com.marine.guard.dto.coleta;

import br.com.marine.guard.dto.historico.DetalhesHistorico;
import br.com.marine.guard.dto.perfil.DetalhesPerfil;
import br.com.marine.guard.model.PontoColeta;

import java.time.LocalTime;

public record DetalhesColetaHistorico(Long codigo, String nome, String cep, LocalTime horarioAbertura, LocalTime horarioFechamento, DetalhesHistorico historico) {

    public DetalhesColetaHistorico(PontoColeta coleta){
        this(coleta.getCodigo(), coleta.getNome(), coleta.getCep(), coleta.getHorarioAbertura(), coleta.getHorarioFechamento(),
                new DetalhesHistorico(coleta.getHistorico()));
    }

}
