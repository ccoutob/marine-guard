package br.com.marine.guard.dto.coleta;

import java.time.LocalTime;

public record CadastroColeta(String nome, String cep, LocalTime horarioAbertura, LocalTime horarioFechamento) {
}