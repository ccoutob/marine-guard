package br.com.marine.guard.model;

import br.com.marine.guard.dto.historico.CadastroHistorico;
import br.com.marine.guard.dto.residuo.CadastroResiduo;
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
@Table(name = "TB_HISTORICO")//Tabela do banco de dados
@EntityListeners(AuditingEntityListener.class)
public class Historico {

    @Id
    @GeneratedValue
    @Column(name = "ID_HISTORICO")
    private Long codigo;

    @Column(name = "TROCAS_DISPONIVEIS")
    private Integer disponivel;

    @Column(name = "QTD_TROCA")
    private Integer quantidadeTrocas; //Quantidade de pontos necessários para proxima troca

    @ManyToOne
    @JoinColumn(name = "historicosPerfil")
    private Perfil perfil;

    public Historico(CadastroHistorico historico){
        disponivel = historico.disponivel();
        quantidadeTrocas = historico.quantidadeTrocas();
    }
    //DTO de detalhesResiduoPerfil
    public Historico(CadastroHistorico historico, Perfil perfil){
        disponivel = historico.disponivel();
        quantidadeTrocas = historico.quantidadeTrocas();
        this.perfil = perfil;
    }

    public void atualizarDados(CadastroHistorico atualizacao){
        if(atualizacao.disponivel() != null)
            disponivel = atualizacao.disponivel();
        if(atualizacao.quantidadeTrocas() != null)
            quantidadeTrocas = atualizacao.quantidadeTrocas();
    }
}
