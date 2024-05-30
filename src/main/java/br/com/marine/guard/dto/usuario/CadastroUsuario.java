package br.com.marine.guard.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroUsuario(@NotBlank String nome,
                              @NotBlank String email,
                              @NotBlank String senha,
                              @NotNull Integer pontos) {
}
