package br.com.orange.talents.controller;

import br.com.orange.talents.controller.DTO.AlunoDto;
import br.com.orange.talents.controller.form.AlunoForm;
import br.com.orange.talents.model.Aluno;
import br.com.orange.talents.repository.AlunoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoRepository alunoRepository;
    public AlunoController(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @PostMapping
    public ResponseEntity<AlunoDto> criarAluno(@RequestBody @Valid AlunoForm form, UriComponentsBuilder uriBuilder){
        Aluno aluno = form.converter();
        alunoRepository.save(aluno);

        URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new AlunoDto(aluno));
    }

    @GetMapping
    public List<AlunoDto> listaAlunos(String nome){
        List<Aluno> alunos;
        if(nome == null){
            alunos = alunoRepository.findAll();
            return AlunoDto.converter(alunos);
        } else {
            alunos = alunoRepository.findByNome(nome);
            return AlunoDto.converter(alunos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDto> listaAlunosId(@PathVariable Long id){
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if(aluno.isPresent()){
            return ResponseEntity.ok(new AlunoDto(aluno.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
