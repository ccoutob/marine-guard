package br.com.marine.guard.controller;

import br.com.marine.guard.dto.brinde.CadastroBrinde;
import br.com.marine.guard.dto.brinde.DetalhesBrinde;
import br.com.marine.guard.dto.usuario.DetalhesUsuario;
import br.com.marine.guard.model.brinde.Brinde;
import br.com.marine.guard.model.brinde.TipoBrinde;
import br.com.marine.guard.repository.BrindeRepository;
import br.com.marine.guard.repository.UsuarioRepository;
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

@RequestMapping("brinde")
@Controller
public class BrindeController {

    @Autowired
    private BrindeRepository brindeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("por-tipo-brinde")
    public ResponseEntity<Page<DetalhesBrinde>> buscarTipoBrinde(@RequestParam("tipoBrinde") TipoBrinde tipoBrinde, Pageable pageable) {
        var lista = brindeRepository.buscarPorTipoBrinde(tipoBrinde, pageable).map(DetalhesBrinde::new);;
        return ResponseEntity.ok(lista);
    }


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

    //Cadastrar Brindes a um Usuario
    @PostMapping("{id}/brindesUsuario")
    @Transactional
    public ResponseEntity<DetalhesBrinde> postHistoricoPerfil(@PathVariable("id") Long id,
                                                                       @RequestBody @Valid CadastroBrinde dto,
                                                                       UriComponentsBuilder uriBuilder){
        var usuario = usuarioRepository.getReferenceById(id);
        var brinde = new Brinde(dto, usuario);
        brindeRepository.save(brinde);
        var uri = uriBuilder.path("brindesUsuario/{id}").buildAndExpand(brinde.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesBrinde(brinde));
    }

    @PutMapping("{id}") //Atualizar um brinde
    @Transactional
    public ResponseEntity<DetalhesBrinde> atualizar(@PathVariable("id") Long id,
                                                    @RequestBody @Valid CadastroBrinde brindePut){
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
