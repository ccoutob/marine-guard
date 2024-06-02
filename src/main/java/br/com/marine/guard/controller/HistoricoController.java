package br.com.marine.guard.controller;

import br.com.marine.guard.dto.historico.CadastroHistorico;
import br.com.marine.guard.dto.historico.DetalhesHistorico;
import br.com.marine.guard.model.Historico;
import br.com.marine.guard.repository.HistoricoRepository;
import br.com.marine.guard.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("historico")
@Controller
public class HistoricoController {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<DetalhesHistorico>> listar(Pageable pageable){
        var lista = historicoRepository.findAll(pageable)
                .stream().map(DetalhesHistorico::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesHistorico> buscar(@PathVariable("id") Long id){
        var residuo = historicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesHistorico(residuo));
    }

    //Post da tabela Historico para Usuario
    @PostMapping("{id}/historicosUsuario")
    @Transactional
    public ResponseEntity<DetalhesHistorico> postHistoricoPerfil(@PathVariable("id") Long id,
                                                                            @RequestBody @Valid CadastroHistorico dto,
                                                                            UriComponentsBuilder uriBuilder){
        var usuario = usuarioRepository.getReferenceById(id);
        var historico = new Historico(dto, usuario);
        historicoRepository.save(historico);
        var uri = uriBuilder.path("historicosUsuario/{id}").buildAndExpand(historico.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesHistorico(historico));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesHistorico> atualizar(@PathVariable("id") Long id,
                                                     @RequestBody @Valid CadastroHistorico historicoPut){
        var historico = historicoRepository.getReferenceById(id);
        historico.atualizarDados(historicoPut);
        return ResponseEntity.ok(new DetalhesHistorico(historico));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        historicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
