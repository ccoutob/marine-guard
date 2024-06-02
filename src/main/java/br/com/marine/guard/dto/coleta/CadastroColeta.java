package br.com.marine.guard.dto.coleta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;

public record CadastroColeta(@NotBlank @Size(max = 255) String nome,
                             @NotBlank @Size(max = 9) String cep,
                             @NotNull LocalTime horarioAbertura,
                             @NotNull LocalTime horarioFechamento) {
}