package com.testeAttornatus.TesteAttornatus.service;

import com.testeAttornatus.TesteAttornatus.model.EnderecoModel;
import com.testeAttornatus.TesteAttornatus.model.PessoaModel;
import com.testeAttornatus.TesteAttornatus.repository.EnderecoRepository;
import com.testeAttornatus.TesteAttornatus.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public EnderecoModel criarEndereco(Long pessoaId, EnderecoModel endereco){
        PessoaModel pessoa = pessoaRepository.findById(pessoaId).orElseThrow(null);

        endereco.setPessoa(pessoa);
        endereco.setPrincipal(pessoa.getEnderecos().isEmpty());
        pessoa.getEnderecos().add(endereco);

        pessoaRepository.save(pessoa);

        return endereco;
    }

    public List<EnderecoModel> getEnderecoPorId(Long pessoaId){
        PessoaModel pessoa = pessoaRepository.findById(pessoaId).orElseThrow(null);

        return pessoa.getEnderecos();
    }

}

