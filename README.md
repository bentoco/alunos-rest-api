# alunos-rest-api
Exercício do curso de Spring Boot Rest Api




<b>Objetivo de aprendizado:</b> Criar uma aplicação web Java seguindo os padrões REST que persiste e retorna os dados de um aluno em banco de dados relacional.

<b>Motivo da escolha:</b> Utilizarem o Spring Boot porque ele facilita o processo de configuração de uma aplicação, através dos componentes do Spring. Com ele contamos com ajuda do Spring Initializr, que nos permite montar o pack inicial do projeto com as dependências necessárias de uma forma muito prática e rápida. Nesse projeto será utilizado Spring Web, Spring Data JPA, Bean Validation, MySQL Driver e H2 Database.

- Inicio a aplicação criando a classe Aluno que por sua vez será uma entidade. Essa classe terá quatro atributos: id, nome, email e idade.
    - Anoto essa classe com @Entity para informar ao Hibernate que essa classe é uma tabela dentro do banco de dados.
    - id: devemos informar através das anotações @Id e @GeneratedValue que ele será a chave primária da minha entidade e que esse idade será auto incremento.
    - nome: através da anotação @Size limitamos no mínimo 3 e máximo 30 caracteres e anotação @NotBlank para não permitir que não seja informado.
    - email: utilizo a anotação específica para validação de emails @Email.
    - idade: para limitar menores de 18 anos adicionei a anotação @Min(18) e @Max(150), assim só irá aceitar valores inteiros maiores que 18 e menores que 150.
    - Feito isso, crio os getters and setters e os construtores, um vazio e outro com os campos os atributos. Um detalhe importante é que no construtor com os atributos eu anoto os atributos para fazer um double validation e certificar que nenhuma validação seja quebrada.

- Nessa etapa criarei nossa classe DTO(Data Transfer Object) que retorna um objeto de visualização para não acoplarmos as entidade da aplicação no controller, com isso limitamos os dados que serão retornados para Client.
    - Nessa classe implementamos os atributos id, nome e email da classe Aluno, sem as anotações, pois não é necessário.
    - Implementamos um construtor que recebe a entidade Aluno com parâmetro.
    - Um método estático que retorna uma lista do tipo AlunoDto chamado converter, onde utilizei o Stream API para instancia uma nova lista do tipo AlunoDto, esse método recebe uma lista do tipo Aluno.

- Crio também um outro DTO que iremos utilizar mais a frente chamado ErroFormularioDto.
    - Essa classe terá dois atributos campo e erro
    - Crio getters and setter e construtor.

- Implemento a classe AlunoForm que converte o formulário de requisição enviado pelo cliente em dados do validados para entidade. Devemos aqui, anotar assim como na entidade os Beans Validations para que não seja enviado nenhum dado invalido para entidade.

- Para realizar as operações com o banco de dados o Spring Data nos disponibiliza a interface JpaRepository, nela podemos usufruir de operações padrões que são bastante utilizadas com banco de dados.
    - Com isso, crio um dentro de um diretório repository a interface AlunoRepository e estendo a interface JpaRepository passando minha entidade Aluno e tipo do identificador dela.

- Implementando minha classe controller, que darei o nome de AlunoController dentro de um diretório controller:
    - Anoto essa classe com @RestController que informa para o Spring que essa classe é controller
    - Anoto também com @RequestMapping(“/alunos”), essa anotação configura o end-point de acesso a coleção de métodos HTTP dessa classe.

- O primeiro método será o que irá cadastrar o aluno na aplicação:
    - Anoto ele com @PostMapping que informa que esse método é do tipo POST.
    - Esse método retorna uma entidade de resposta do tipo AlunoDto.
    - Como parâmetro esse método recebe um AlunoForm, e anotamos com @RequestBody que informa que esse método recebe dados via body da requisição. E @Valid anotação necessária para validar as beans validation anotadas na entidade.
    - Esse método recebe o formulário, converte formulário em entidade e persiste no banco de dados através do método save() do JpaRepository.
    - Feito isso, com auxílio do uriComponentBuilder configuro a URI de retorno que o client deve ser redirecionado.     E retorno através do ResponseEntity uma nova instância de AlunoDto com respectivo código HTTP 201.

- O segundo método que busca Aluno pelo id:
    - Anoto esse método com GET que recebe como parâmetro o valor da variável dinâmica id.
    - Esse método também retorna um ResponseEntity do tipo AlunoDto. ResponseEntity é a entidade que trata as respostas para o cliente.
    - Passo como parâmetro para esse método id, e anoto com @PathVariable que diz que o end-point /alunos recebe id como parâmetro na requisição.
    - Para buscar o aluno na base de dados utilizo o método findById, que retorna um Optional de Aluno. Optional porque o aluno pode ou não existir.
    - Com o retorno da consulta, verificamos através de um condição if se o aluno existe. Caso exista, retornamos através do ResponseEntity o código HTTP 200 e uma nova instância de AlunoDto do aluno requerido. Se não existir, retorna um NotFound 404.

Por fim, devo configurar uma classe ValidationErrorHandler, essa classe intercepta as exceptions do nosso controlador e retorna Bad Request(400) por default e ErroFormularioDto com o campo onde o erro ocorreu e mensagem de erro. Com isso, limitamos o que é retornado para usuário, removendo toda informação desnecessário. Lembrando que essa classe não sobrepõe o retorno do ResponseEntity caso ocorra um NotFound exception.
