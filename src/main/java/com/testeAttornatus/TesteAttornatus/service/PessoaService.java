package com.testeAttornatus.TesteAttornatus.service;


import com.testeAttornatus.TesteAttornatus.model.EnderecoModel;
import com.testeAttornatus.TesteAttornatus.model.PessoaModel;
import com.testeAttornatus.TesteAttornatus.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaModel inserirPessoa(PessoaModel pessoa){
        return pessoaRepository.save(pessoa);
    }

    public PessoaModel atualizarPessoa(Long id, PessoaModel atualizarPessoa){
        PessoaModel pessoa = pessoaRepository.findById(id).orElseThrow(null);

        pessoa.setNome(atualizarPessoa.getNome());
        pessoa.setDataNascimento(atualizarPessoa.getDataNascimento());
        pessoa.setEnderecos(atualizarPessoa.getEnderecos());

        return pessoaRepository.save(pessoa);
    }

    public PessoaModel buscarPessoaById(Long id){
        return pessoaRepository.findById(id).orElseThrow(null);
    }

    public List<PessoaModel> buscarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    public EnderecoModel buscarEnderecoPrincipal(Long pessoaId) {
        PessoaModel pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(null);
        return pessoa.getEnderecos().stream()
                .filter(EnderecoModel::isPrincipal)
                .findFirst()
                .orElseThrow(null);
    }
}
