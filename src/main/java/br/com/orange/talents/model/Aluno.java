package br.com.orange.talents.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=3, max=30)
    @NotBlank
    private String nome;
    @Email
    private String email;
    @Min(18)
    @Max(150)
    @NotNull
    private Integer idade;

    public Aluno(
            @Size(min = 10, max = 30) @NotBlank String nome,
            @Email String email,
            @Min(18) @Max(150) @NotNull Integer idade)
    {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public Aluno(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
