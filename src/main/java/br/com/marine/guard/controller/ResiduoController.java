package br.com.marine.guard.controller;

import br.com.marine.guard.dto.residuo.CadastroResiduo;
import br.com.marine.guard.dto.residuo.DetalhesResiduo;
import br.com.marine.guard.model.Residuo.Residuo;
import br.com.marine.guard.repository.ResiduoRepository;
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

import java.time.LocalDate;
import java.util.List;

@RequestMapping("residuos")
@Controller
public class ResiduoController {

    @Autowired
    private ResiduoRepository residuoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("por-data-envio")
    public ResponseEntity<Page<Residuo>> buscarTipoBrinde(@RequestParam("dataEnvio") LocalDate dataEnvio, Pageable pageable) {
        var lista = residuoRepository.buscarPorDataEnvio(dataEnvio, pageable);
        return ResponseEntity.ok(lista);
    }

    @GetMapping
    public ResponseEntity<List<DetalhesResiduo>> listar(Pageable pageable){
        var lista = residuoRepository.findAll(pageable)
                .stream().map(DetalhesResiduo::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhesResiduo> buscar(@PathVariable("id") Long id){
        var residuo = residuoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesResiduo(residuo));
    }

    //Post da tabela Residuos para Usuario
    @PostMapping("{id}/residuosUsuario")
    @Transactional
    public ResponseEntity<DetalhesResiduo> postResiduoPerfil(@PathVariable("id") Long id,
                                                                        @RequestBody @Valid CadastroResiduo dto,
                                                                        UriComponentsBuilder uriBuilder){
        var usuario = usuarioRepository.getReferenceById(id);
        var residuo = new Residuo(dto, usuario);
        residuoRepository.save(residuo);
        var uri = uriBuilder.path("residuosUsuario/{id}").buildAndExpand(residuo.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesResiduo(residuo));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetalhesResiduo> atualizar(@PathVariable("id") Long id,
                                                         @RequestBody @Valid CadastroResiduo residuoPut){
        var residuo = residuoRepository.getReferenceById(id);
        residuo.atualizarDados(residuoPut);
        return ResponseEntity.ok(new DetalhesResiduo(residuo));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        residuoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
