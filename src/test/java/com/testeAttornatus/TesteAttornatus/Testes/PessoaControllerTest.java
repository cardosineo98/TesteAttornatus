package com.testeAttornatus.TesteAttornatus.Testes;

import com.testeAttornatus.TesteAttornatus.model.PessoaModel;
import com.testeAttornatus.TesteAttornatus.repository.EnderecoRepository;
import com.testeAttornatus.TesteAttornatus.repository.PessoaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PessoaControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Test
    public void testCadastrarPessoa() {
        PessoaModel pessoa = new PessoaModel();
        pessoa.setNome("Fulano");
        pessoa.setDataNascimento(LocalDate.of(1990, 1, 1));

        ResponseEntity<PessoaModel> response = restTemplate.postForEntity("/pessoa", pessoa, PessoaModel.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        PessoaModel pessoaSalva = pessoaRepository.findById(response.getBody().getId()).orElse(null);
        assertNotNull(pessoaSalva);
        assertEquals(pessoa.getNome(), pessoaSalva.getNome());
        assertEquals(pessoa.getDataNascimento(), pessoaSalva.getDataNascimento());
    }
    @Test
    public void testBuscarPessoa() {
        PessoaModel pessoa = new PessoaModel();
        pessoa.setNome("Cicrano");
        pessoa.setDataNascimento(LocalDate.of(1998, 6, 1));
        pessoaRepository.save(pessoa);

        ResponseEntity<PessoaModel> response = restTemplate.getForEntity("/pessoa/" + pessoa.getId(), PessoaModel.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(pessoa.getNome(), response.getBody().getNome());
        assertEquals(pessoa.getDataNascimento(), response.getBody().getDataNascimento());
    }


}
