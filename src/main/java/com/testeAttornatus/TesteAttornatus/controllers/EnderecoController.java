package com.testeAttornatus.TesteAttornatus.controllers;

import com.testeAttornatus.TesteAttornatus.model.EnderecoModel;
import com.testeAttornatus.TesteAttornatus.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa/{pessoaId}/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<EnderecoModel> criarEndereco(@PathVariable Long pessoaId, @RequestBody @Valid EnderecoModel endereco){
        EnderecoModel criarEndereco = enderecoService.criarEndereco(pessoaId, endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarEndereco);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoModel>> buscarEnderecoPorId(@PathVariable Long pessoaId) {
        List<EnderecoModel> enderecos = enderecoService.getEnderecoPorId(pessoaId);
        return ResponseEntity.ok(enderecos);
    }
}
