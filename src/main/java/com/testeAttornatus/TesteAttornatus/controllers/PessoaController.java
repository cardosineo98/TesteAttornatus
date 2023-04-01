package com.testeAttornatus.TesteAttornatus.controllers;

import com.testeAttornatus.TesteAttornatus.model.EnderecoModel;
import com.testeAttornatus.TesteAttornatus.model.PessoaModel;
import com.testeAttornatus.TesteAttornatus.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaModel> inserirPessoa(@RequestBody @Valid PessoaModel pessoa) {
        PessoaModel criarPessoa = pessoaService.inserirPessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarPessoa);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PessoaModel> atualizarPessoa(@PathVariable Long id, @RequestBody @Valid PessoaModel atualizarPessoa){
        PessoaModel atualizar = pessoaService.atualizarPessoa(id, atualizarPessoa);
        return ResponseEntity.ok(atualizar);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PessoaModel> buscarPessoaPorId(@PathVariable Long id){
        PessoaModel pessoa = pessoaService.buscarPessoaById(id);
        return ResponseEntity.ok(pessoa);
    }
    @GetMapping
    public ResponseEntity<List<PessoaModel>> buscarTodasPessoas(){
        List<PessoaModel> pessoa = pessoaService.buscarTodasPessoas();
        return ResponseEntity.ok(pessoa);
    }
    @GetMapping("/{pessoaId}/endereco-principal")
    public ResponseEntity<EnderecoModel> buscarEnderecoPrincipal(@PathVariable Long pessoaId) {
        EnderecoModel enderecoPrincipal = pessoaService.buscarEnderecoPrincipal(pessoaId);
        return ResponseEntity.ok(enderecoPrincipal);
    }
}
