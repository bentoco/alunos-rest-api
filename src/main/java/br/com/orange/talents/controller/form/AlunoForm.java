package br.com.orange.talents.controller.form;

import br.com.orange.talents.model.Aluno;

import javax.validation.constraints.*;

public class AlunoForm {
    @Size(min=3, max=30)
    @NotBlank
    private String nome;
    @Email
    private String email;
    @Min(18)
    @Max(150)
    @NotNull
    private Integer idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Aluno converter(){
        return new Aluno(nome, email, idade);
    }
}
