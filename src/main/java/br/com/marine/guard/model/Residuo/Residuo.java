package br.com.marine.guard.model.Residuo;

import br.com.marine.guard.dto.residuo.CadastroResiduo;
import br.com.marine.guard.model.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "TB_RESIDUO")//Tabela do banco de dados
@EntityListeners(AuditingEntityListener.class)
public class Residuo {

    @Id
    @GeneratedValue
    @Column(name = "ID_RESIDUO")
    private Long codigo;

    @Column(name = "TIPO_PLASTICO", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPlastico tipoPlastico;

    @Column(name = "NIVEL_RISCO", nullable = false)
    private String risco;

    @Column(name = "DT_ENVIO", nullable = false)
    private LocalDate dataEnvio;

    @ManyToOne
    @JoinColumn(name = "residuosPerfil")
    private Perfil perfil;

    public Residuo(CadastroResiduo residuo){
        tipoPlastico = residuo.tipoPlastico();
        risco = residuo.risco();
        dataEnvio = residuo.dataEnvio();
    }
    //DTO de detalhesResiduoPerfil
    public Residuo(CadastroResiduo residuo, Perfil perfil){
        tipoPlastico = residuo.tipoPlastico();
        risco = residuo.risco();
        dataEnvio = residuo.dataEnvio();
        this.perfil = perfil;
    }

    public void atualizarDados(CadastroResiduo atualizacao){
        if(atualizacao.tipoPlastico() != null)
            tipoPlastico = atualizacao.tipoPlastico();
        if(atualizacao.risco() != null)
            risco = atualizacao.risco();
        if(atualizacao.dataEnvio() != null)
            dataEnvio = atualizacao.dataEnvio();
    }
}
