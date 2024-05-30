package br.com.marine.guard.dto.residuo;

import br.com.marine.guard.dto.perfil.DetalhesPerfil;
import br.com.marine.guard.model.Residuo.Residuo;
import br.com.marine.guard.model.Residuo.TipoPlastico;

import java.time.LocalDate;

public record DetalhesResiduoPerfil(Long id, TipoPlastico tipoPlastico, String risco, LocalDate dataEnvio,
                                    DetalhesPerfil perfil) {

    public DetalhesResiduoPerfil(Residuo residuo){
        this(residuo.getCodigo(), residuo.getTipoPlastico(), residuo.getRisco(), residuo.getDataEnvio(),
                new DetalhesPerfil(residuo.getPerfil()));
    }

}
