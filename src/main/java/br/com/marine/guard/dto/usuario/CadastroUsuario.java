package br.com.marine.guard.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastroUsuario(@NotBlank @Size(max = 60) String nome,
                              @NotBlank @Size(max = 100) String email,
                              @NotBlank @Size(max = 10) String senha) {
}
