package br.com.marine.guard.model;

import br.com.marine.guard.dto.usuario.CadastroUsuario;
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
@Table(name = "TB_USUARIO")//Tabela do banco de dados
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

    @Id
    @GeneratedValue
    @Column(name = "ID_USUARIO")
    private Long codigo;

    @Column(name = "NM_USUARIO", nullable = false)
    private String nome;

    @Column(name = "EMAIL_USUARIO", nullable = false)
    private String email;

    @Column(name = "SENHA_USUARIO", nullable = false)
    private String senha;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Perfil perfil;

    public Usuario(CadastroUsuario usuario){
        nome = usuario.nome();
        email = usuario.email();
        senha = usuario.senha();
        perfil = new Perfil(usuario);
        perfil.setUsuario(this);
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
