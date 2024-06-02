package br.com.marine.guard.dto.residuo;

import br.com.marine.guard.model.Residuo.TipoPlastico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CadastroResiduo(@NotNull TipoPlastico tipoPlastico,
                              @NotBlank @Size(max = 20)String risco,
                              @NotNull LocalDate dataEnvio) {
}
