package br.com.marine.guard.model;

import br.com.marine.guard.dto.coleta.CadastroColeta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "TB_PONTO_COLETA")//Tabela do banco de dados
@EntityListeners(AuditingEntityListener.class)
public class PontoColeta {

    @Id
    @GeneratedValue
    @Column(name = "ID_COLETA")
    private Long codigo;

    @Column(name = "NM_COLETA", nullable = false, length = 255)
    private String nome;

    @Column(name = "CEP_COLETA", nullable = false, length = 9)
    private String cep;

    @Column(name = "HR_ABERTURA", nullable = false)
    private LocalTime horarioAbertura; //"abertura": "09:00:00"

    @Column(name = "HR_FECHAMENTO", nullable = false)
    private LocalTime horarioFechamento; //"fechamento": "18:00:00"

    @ManyToOne
    @JoinColumn(name = "pontoColeta")
    private Historico historico;

    //Dto de CadastroColetaHistorico
    public PontoColeta(CadastroColeta coleta, Historico historico){
        nome = coleta.nome();
        cep = coleta.cep();
        horarioAbertura = coleta.horarioAbertura();
        horarioFechamento = coleta.horarioFechamento();
        this.historico = historico;
    }

    public void atualizarDados(CadastroColeta atualizacao){
        if(atualizacao.nome() != null)
            nome = atualizacao.nome();
        if(atualizacao.cep() != null)
            cep = atualizacao.cep();
        if(atualizacao.horarioAbertura() != null)
            horarioAbertura = atualizacao.horarioAbertura();
        if(atualizacao.horarioFechamento() != null)
            horarioFechamento = atualizacao.horarioFechamento();
    }
}
