package br.com.marine.guard.controller;

import br.com.marine.guard.dto.brinde.DetalhesBrinde;
import br.com.marine.guard.dto.coleta.CadastroColeta;
import br.com.marine.guard.dto.coleta.DetalhesColeta;
import br.com.marine.guard.dto.coleta.DetalhesColetaHistorico;
import br.com.marine.guard.model.Historico;
import br.com.marine.guard.model.PontoColeta;
import br.com.marine.guard.model.brinde.Brinde;
import br.com.marine.guard.model.brinde.TipoBrinde;
import br.com.marine.guard.repository.ColetaRepository;
import br.com.marine.guard.repository.HistoricoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("coleta")
@Controller
public class ColetaController {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    @GetMapping("por-cep")
    public ResponseEntity<Page<DetalhesColeta>> buscarPorCep(@RequestParam("cep") String cep, Pageable pageable) {
        var lista = coletaRepository.buscarPorCep(cep, pageable).map(DetalhesColeta::new);;;
        return ResponseEntity.ok(lista);
    }

    @GetMapping
    public ResponseEntity<List<DetalhesColeta>> listar(Pageable pageable){
        var lista = coletaRepository.findAll(pageable)
                .stream().map(DetalhesColeta::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesColeta> buscar(@PathVariable("id") Long id){
        var coleta = coletaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesColeta(coleta));
    }

    //Post da tabela PontoColeta para Historico
    @PostMapping("{id}/coletaHistorico")
    @Transactional
    public ResponseEntity<DetalhesColetaHistorico> postHistoricoPerfil(@PathVariable("id") Long id,
                                                                       @RequestBody @Valid CadastroColeta dto,
                                                                       UriComponentsBuilder uriBuilder){
        var historico = historicoRepository.getReferenceById(id);
        var coleta = new PontoColeta(dto, historico);
        coletaRepository.save(coleta);
        var uri = uriBuilder.path("coletaHistorico/{id}").buildAndExpand(historico.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesColetaHistorico(coleta));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesColeta> atualizar(@PathVariable("id") Long id,
                                                       @RequestBody @Valid CadastroColeta coletaPut){
        var coleta = coletaRepository.getReferenceById(id);
        coleta.atualizarDados(coletaPut);
        return ResponseEntity.ok(new DetalhesColeta(coleta));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        coletaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
