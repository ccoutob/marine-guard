package br.com.marine.guard.model;

import br.com.marine.guard.dto.perfil.CadastroPerfil;
import br.com.marine.guard.dto.usuario.CadastroUsuario;
import br.com.marine.guard.model.Residuo.Residuo;
import br.com.marine.guard.model.brinde.Brinde;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "TB_PERFIL")//Tabela do banco de dados
@EntityListeners(AuditingEntityListener.class)
public class Perfil {

    @Id
    @GeneratedValue
    @Column(name = "ID_PERFIL")
    private Long codigo;

    @Column(name = "PONTOS_USUARIO")
    private Integer pontos;

    @OneToOne
    @JoinColumn(name = "ID_USUARIO", unique = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "perfil")
    private List<Residuo> residuosPerfil;

    @OneToMany(mappedBy = "perfil")
    private List<Historico> historicosPerfil;

    @OneToMany(mappedBy = "perfil")
    private List<Brinde> brindes;

    public Perfil(CadastroUsuario perfil){
        pontos = perfil.pontos();
    }

    public void atualizarDados(CadastroPerfil atualizacao) {
        if (atualizacao.pontos() != null)
            pontos = atualizacao.pontos();
    }
}
