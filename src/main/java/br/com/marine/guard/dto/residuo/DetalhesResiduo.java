package br.com.marine.guard.dto.residuo;

import br.com.marine.guard.dto.usuario.DetalhesUsuario;
import br.com.marine.guard.model.Residuo.Residuo;
import br.com.marine.guard.model.Residuo.TipoPlastico;

import java.time.LocalDate;

public record DetalhesResiduo(Long id, TipoPlastico tipoPlastico, String risco, LocalDate dataEnvio) {

    public DetalhesResiduo(Residuo residuo){
        this(residuo.getCodigo(), residuo.getTipoPlastico(), residuo.getRisco(), residuo.getDataEnvio());
    }

}
