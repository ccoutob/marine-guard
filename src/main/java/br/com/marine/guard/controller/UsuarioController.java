package br.com.marine.guard.controller;

import br.com.marine.guard.dto.usuario.CadastroUsuario;
import br.com.marine.guard.dto.usuario.DetalhesUsuario;
import br.com.marine.guard.model.Usuario;
import br.com.marine.guard.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("usuario")
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //endpoint para pesquisar o usuario pelo nome
    @GetMapping("por-nome")
    public ResponseEntity<Page<DetalhesUsuario>> buscarNome(@RequestParam("nome")String nome, Pageable pageable){
        var lista = usuarioRepository.buscarPorNome(nome, pageable).map(DetalhesUsuario::new);
        return ResponseEntity.ok(lista);
    }

    //endpoint para pesquisar o usuario pelo email
    @GetMapping("por-email")
    public ResponseEntity<Page<DetalhesUsuario>> buscarEmail(@RequestParam("email")String email, Pageable pageable){
        var lista = usuarioRepository.buscarPorEmail(email, pageable).map(DetalhesUsuario::new);
        return ResponseEntity.ok(lista);
    }

    @GetMapping //Listar todos os usuarios
    public ResponseEntity<List<DetalhesUsuario>> listar(Pageable pageable){
        var lista = usuarioRepository.findAll(pageable)
                .stream().map(DetalhesUsuario::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}") //Listar usuario específico
    public ResponseEntity<DetalhesUsuario> buscar(@PathVariable("id") Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesUsuario(usuario));
    }

    @PostMapping //Cadastrar um usuario
    @Transactional
    public ResponseEntity<DetalhesUsuario> cadastrar(@RequestBody @Valid CadastroUsuario usuarioPost,
                                                     UriComponentsBuilder uri){
        var usuario = new Usuario(usuarioPost);
        usuarioRepository.save(usuario);
        var url = uri.path("/usuario/{id}").buildAndExpand(usuario.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new DetalhesUsuario(usuario));
    }

    @PutMapping("{id}") //Atualizar um usuario, sem incluir o perfil
    @Transactional
    public ResponseEntity<DetalhesUsuario> atualizar(@PathVariable("id") Long id,
                                                     @RequestBody @Valid CadastroUsuario usuarioPut){
        var cliente = usuarioRepository.getReferenceById(id);
        cliente.atualizarDados(usuarioPut);
        return ResponseEntity.ok(new DetalhesUsuario(cliente));
    }

    @DeleteMapping("{id}") //Deletar um usuario e seu perfil
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
