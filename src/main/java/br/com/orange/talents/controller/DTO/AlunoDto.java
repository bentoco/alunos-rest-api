package br.com.orange.talents.controller.DTO;

import br.com.orange.talents.model.Aluno;

import java.util.List;
import java.util.stream.Collectors;

public class AlunoDto {
    public Long id;
    public String nome;
    public String email;

    public AlunoDto(Aluno aluno) {
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public static List<AlunoDto> converter(List<Aluno> alunos){
        return alunos.stream().map(AlunoDto::new).collect(Collectors.toList());
    }
}
