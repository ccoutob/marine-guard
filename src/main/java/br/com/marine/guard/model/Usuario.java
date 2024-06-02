package br.com.marine.guard.model;

import br.com.marine.guard.dto.coleta.CadastroColeta;
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
@Table(name = "TB_USUARIO")//Tabela do banco de dados
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

    @Id
    @GeneratedValue
    @Column(name = "ID_USUARIO")
    private Long codigo;

    @Column(name = "NM_USUARIO", nullable = false, length = 60)
    private String nome;

    @Column(name = "EMAIL_USUARIO", nullable = false, length = 100)
    private String email;

    @Column(name = "SENHA_USUARIO", nullable = false, length = 10)
    private String senha;

    @OneToMany(mappedBy = "usuario")
    private List<Historico> historicos;

    @OneToMany(mappedBy = "usuario")
    private List<Brinde> brindes;

    @OneToMany(mappedBy = "usuario")
    private List<Residuo> residuos;

    public Usuario(CadastroUsuario usuarioPost) {
        nome = usuarioPost.nome();
        email = usuarioPost.email();
        senha = usuarioPost.senha();
    }

    public void atualizarDados(CadastroUsuario atualizacao) {
        if (atualizacao.nome() != null)
            nome = atualizacao.nome();
        if (atualizacao.email() != null)
            email = atualizacao.email();
        if (atualizacao.senha() != null)
            senha = atualizacao.senha();
    }
}
