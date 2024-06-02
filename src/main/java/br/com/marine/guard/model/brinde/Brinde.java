package br.com.marine.guard.model.brinde;

import br.com.marine.guard.dto.brinde.CadastroBrinde;
import br.com.marine.guard.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "TB_BRINDE")//Tabela do banco de dados
@EntityListeners(AuditingEntityListener.class)
public class Brinde {

    @Id
    @GeneratedValue
    @Column(name = "ID_BRINDE")
    private Long codigo;

    @Column(name = "NM_BRINDE", nullable = false, length = 100)
    private String nome;

    @Column(name = "TP_BRINDE", nullable = false, length = 25)
    @Enumerated(EnumType.STRING)
    private TipoBrinde tipoBrinde;

    @ManyToOne
    @JoinColumn(name = "brindes")
    private Usuario usuario;

    public Brinde(CadastroBrinde cadastro, Usuario usuario){
        nome = cadastro.nome();
        tipoBrinde = cadastro.tipoBrinde();
        this.usuario = usuario;
    }

    public void atualizarDados(CadastroBrinde atualizacao) {
        if (atualizacao.nome() != null)
            nome = atualizacao.nome();
        if (atualizacao.tipoBrinde() != null)
            tipoBrinde = atualizacao.tipoBrinde();
    }
}
