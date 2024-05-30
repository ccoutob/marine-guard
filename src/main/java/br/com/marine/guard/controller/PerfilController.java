package br.com.marine.guard.controller;

import br.com.marine.guard.dto.perfil.CadastroPerfil;
import br.com.marine.guard.dto.perfil.DetalhesPerfil;
import br.com.marine.guard.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("perfil")
@Controller
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping //Listar todos os perfils
    public ResponseEntity<List<DetalhesPerfil>> listar(Pageable pageable){
        var lista = perfilRepository.findAll(pageable)
                .stream().map(DetalhesPerfil::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}") //Listar perfil específico
    public ResponseEntity<DetalhesPerfil> buscar(@PathVariable("id") Long id){
        var perfil = perfilRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesPerfil(perfil));
    }

    @PutMapping("{id}") //Atualizar um perfil
    @Transactional
    public ResponseEntity<DetalhesPerfil> atualizar(@PathVariable("id") Long id,
                                                      @RequestBody CadastroPerfil perfilPut){
        var perfil = perfilRepository.getReferenceById(id);
        perfil.atualizarDados(perfilPut);
        return ResponseEntity.ok(new DetalhesPerfil(perfil));
    }

    @DeleteMapping("{id}") //Deletar um Perfil
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        perfilRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
