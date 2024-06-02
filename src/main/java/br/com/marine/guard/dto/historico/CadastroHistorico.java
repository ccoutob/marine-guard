package br.com.marine.guard.dto.historico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record CadastroHistorico(@NotNull Integer disponivel,
                                @NotNull LocalDate dataTroca,
                                @NotNull Integer quantidadeTrocas) {
}
