package br.com.orange.talents.repository;

import br.com.orange.talents.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByNome(String nome);
}
