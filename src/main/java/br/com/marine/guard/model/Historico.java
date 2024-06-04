package br.com.marine.guard.model;

import br.com.marine.guard.dto.historico.CadastroHistorico;
import br.com.marine.guard.model.brinde.Brinde;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "TB_HISTORICO")//Tabela do banco de dados
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(name="seq_historico", sequenceName="seq_tb_rg_historico", allocationSize=1)
public class Historico {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_historico")
    @Column(name = "ID_HISTORICO")
    private Long codigo;

    @Column(name = "TROCAS_DISPONIVEIS", nullable = false)
    private Integer disponivel;

    @Column(name = "DT_TROCA", nullable = false)
    private LocalDate dataTroca;

    @Column(name = "QTD_TROCA", nullable = false)
    private Integer quantidadeTrocas; //Quantidade de pontos necessários para proxima troca

    @OneToMany(mappedBy = "historico")
    private List<PontoColeta> pontoColeta;

    @ManyToOne
    @JoinColumn(name = "historicos")
    private Usuario usuario;

    public Historico(CadastroHistorico historico, Usuario usuario){
        disponivel = historico.disponivel();
        quantidadeTrocas = historico.quantidadeTrocas();
        dataTroca = historico.dataTroca();
        this.usuario = usuario;
    }

    public void atualizarDados(CadastroHistorico atualizacao){
        if(atualizacao.disponivel() != null)
            disponivel = atualizacao.disponivel();
        if(atualizacao.quantidadeTrocas() != null)
            quantidadeTrocas = atualizacao.quantidadeTrocas();
        if(atualizacao.dataTroca() != null)
            dataTroca = atualizacao.dataTroca();
    }
}
