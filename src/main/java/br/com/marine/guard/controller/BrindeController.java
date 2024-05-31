package br.com.marine.guard.controller;

import br.com.marine.guard.dto.brinde.CadastroBrinde;
import br.com.marine.guard.dto.brinde.DetalhesBrinde;
import br.com.marine.guard.dto.historico.CadastroHistorico;
import br.com.marine.guard.dto.historico.DetalhesHistoricoPerfil;
import br.com.marine.guard.model.Historico;
import br.com.marine.guard.model.brinde.Brinde;
import br.com.marine.guard.repository.BrindeRepository;
import br.com.marine.guard.repository.PerfilRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("brinde")
@Controller
public class BrindeController {

    @Autowired
    private BrindeRepository brindeRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping //Listar todos os brindes
    public ResponseEntity<List<DetalhesBrinde>> listar(Pageable pageable){
        var lista = brindeRepository.findAll(pageable)
                .stream().map(DetalhesBrinde::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}") //Listar brinde específico
    public ResponseEntity<DetalhesBrinde> buscar(@PathVariable("id") Long id){
        var brinde = brindeRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesBrinde(brinde));
    }

    //Cadastrar Brindes a um perfil
    @PostMapping("{id}/brindesPerfil")
    @Transactional
    public ResponseEntity<DetalhesBrinde> postHistoricoPerfil(@PathVariable("id") Long id,
                                                                       @RequestBody @Valid CadastroBrinde dto,
                                                                       UriComponentsBuilder uriBuilder){
        var perfil = perfilRepository.getReferenceById(id);
        var brinde = new Brinde(dto, perfil);
        brindeRepository.save(brinde);
        var uri = uriBuilder.path("brindesPerfil/{id}").buildAndExpand(brinde.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesBrinde(brinde));
    }

    @PutMapping("{id}") //Atualizar um brinde
    @Transactional
    public ResponseEntity<DetalhesBrinde> atualizar(@PathVariable("id") Long id,
                                                    @RequestBody CadastroBrinde brindePut){
        var brinde = brindeRepository.getReferenceById(id);
        brinde.atualizarDados(brindePut);
        return ResponseEntity.ok(new DetalhesBrinde(brinde));
    }

    @DeleteMapping("{id}") //Deletar um brinde
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        brindeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
