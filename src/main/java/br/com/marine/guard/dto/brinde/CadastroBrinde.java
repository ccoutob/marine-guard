package br.com.marine.guard.dto.brinde;

import br.com.marine.guard.model.brinde.TipoBrinde;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastroBrinde(@NotBlank @Size(max = 100)String nome,
                             @NotNull TipoBrinde tipoBrinde) {
}
