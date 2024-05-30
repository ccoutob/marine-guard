package br.com.marine.guard.dto.residuo;

import br.com.marine.guard.model.Residuo.TipoPlastico;

import java.time.LocalDate;

public record CadastroResiduo(TipoPlastico tipoPlastico,
                              String risco,
                              LocalDate dataEnvio) {
}
