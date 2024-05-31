package br.com.marine.guard.dto.coleta;

import br.com.marine.guard.dto.brinde.DetalhesBrinde;
import br.com.marine.guard.model.PontoColeta;

import java.time.LocalTime;

public record DetalhesColeta(Long codigo, String nome, String cep, LocalTime horarioAbertura, LocalTime horarioFechamento) {

    public DetalhesColeta(PontoColeta coleta){
        this(coleta.getCodigo(), coleta.getNome(), coleta.getCep(), coleta.getHorarioAbertura(), coleta.getHorarioFechamento());
    }

}
