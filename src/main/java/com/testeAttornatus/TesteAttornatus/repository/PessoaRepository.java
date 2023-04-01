package com.testeAttornatus.TesteAttornatus.repository;

import com.testeAttornatus.TesteAttornatus.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {
}
