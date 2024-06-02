package br.com.marine.guard.model.Residuo;

import br.com.marine.guard.dto.residuo.CadastroResiduo;
import br.com.marine.guard.model.Usuario;
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

    @Column(name = "TIPO_PLASTICO", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TipoPlastico tipoPlastico;

    @Column(name = "NIVEL_RISCO", nullable = false, length = 20)
    private String risco;

    @Column(name = "DT_ENVIO", nullable = false)
    private LocalDate dataEnvio;

    @ManyToOne
    @JoinColumn(name = "residuos")
    private Usuario usuario;

    public Residuo(CadastroResiduo residuo, Usuario usuario){
        tipoPlastico = residuo.tipoPlastico();
        risco = residuo.risco();
        dataEnvio = residuo.dataEnvio();
        this.usuario = usuario;
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
