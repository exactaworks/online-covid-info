# Considerações

### Principais Alterações

* A classe `DiseaseRepository` foi substituida pela interface `JPARepository` 
  que expõe altomaticamente metodos CRUD e permite criar novos métodos apenas usando
  uma sintaxe padrão;

* Desse modo, eventuais regras de negócio que residiam no `DiseaseRepository` foram
  transferiadas para o `DiseaseService`, local mais adequado para tratamento de exceções,
  logs, verificações se o dado já exsite, etc;

* Isso resolveu o teste, uma vez que  objeto `jdbi` deixou de ser uma dependência do
  `DiseaseRepository`;

* Foi utlizada a classe `ModelMapper` para mapeamento entre objetos do tipo entity, DTO,
  etc; Deixei o mapper original por conveniência;

* Uso extensivo da dependência `Lombok` para gerar getters, setters, métodos `toString()`,
  etc; além de injeção de objeto de log com a anotação `@Slf4j`;

* Anotações do tipo `@Entity` e `@Table` para facilitar o ORM de modo geral, especialmente
  do `JPARepository`.

### Pontos de Melhorias 

Esses pontos de modo geral são provenientes de limitações de tempo e disponibilidade
que tive durante a semana; o foco foi terminar o projeto cumprindo as especificações.
Num cenário do mundo real, as melhorias listadas abaixo trariam mais governança para o
código:

* O tratamento de exceções foi feito de maneira bem simplificada; simplesmente voltando
  `INTERNAL SERVER ERROR` ou `BAD REQUEST` quando convém. Embora isso seja uma boa prática
  do ponto de vista de segurança ([OWASP Improper Error Handling](https://owasp.org/www-community/Improper_Error_Handling)),
  as mensagens poderiam ser um pouco mais customizadas;

* Os logs logam os dados de entrada e os erros provenientes dos métodos do `DiseaseService`;
  mais logs não fariam mal.

* Criar testes para todos os endpoints.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.2/gradle-plugin/reference/html/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#using-boot-devtools)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [JDBI Database Library](https://jdbi.org/)

### Additional Links
These additional references should also help you:

* [Open Covid API](https://corona.lmao.ninja/docs/#/)

### Project Info

#### Included on project
* Gradle
* Spring boot and related (data, security etc)
* Flyway
* JDBI
* Mockito
* Okhttp
* H2 Database

#### How to use
* Just start the project with gradle - no need database configuration
* Open your curl tool and do the requisition:
- GET - http://localhost:9090/covid/disease
* Check the result

### Activities (pt-BR)
O projeto parece não funcionar muito bem. As primeiras 4 questões são relacionadas a isso. Execute-as na ordem que estão determinadas abaixo:

1 — O projeto não possui tratamento de exceções. Ao executar o endpoint pela primeira vez, por exemplo, ele mostra o
 objeto de response com os valores todos NULL. Que tal tratarmos esse e todos os erros que pode haver no sistema?

2 - Coloque logs na aplicação. Onde achar interessante, coloque logs do tipo DEBUG. Onde achar essencial coloque logs 
do tipo INFO. Onde pode haver erros, coloque logs do tipo ERROR.

3 — Agora que os erros do projeto estão tratados e com logs, que tal corrigir o erro do projeto para que retorne o resultado
da resposta do parceiro?

4 — O projeto tem um teste unitário, mas ele está quebrado. Que tal arruma-lo antes de prosseguir com as atividades?

**O projeto agora deve estar com tratamentos de exceções, logs e funcionando adequadamente, inclusive o teste. 
As próximas questões são features adicionais ao projeto e podem ser realizadas em qualquer ordem.**

5 — Sempre ao executar o endpoint, obtemos os casos de covid no parceiro e salvamos o resultado da requisição no 
banco de dados. Contudo, os dados do parceiro são atualizados diariamente. Então é desnecessário salvar no banco
se a requisição daquele dia já estiver no banco de dados para aquela região. Faça esse tratamento. 

6 — Crie um endpoint para buscar todas as informações do banco de dados. Todas, sem exceções. Colocar paginação é
opcional, mas o resultado deve vir ordenado por data.

7 — Crie um endpoint para deletar informações do banco de dados de um determinado país, passado por parâmetro no endpoint
e independente de datas. Opcionalmente, seria ótimo se fosse um soft delete (também conhecido como false delete ou 
exclusão lógica).

8 — Altere o endpoint. Permita ao usuário, opcionalmente, colocar um nome de país. Se ele colocar um nome de país, devemos 
chamar o parceiro com um endpoint passando o nome do país, para retornar somente os dados daquele país ao invés de retornar
os dados do mundo todo.

9 — Crie um endpoint para buscar no parceiro informações de continente. O endpoint deve ser novo e deve armazenar em uma
tabela separada chamada de `continent`. Se o usuário tentar um continente que não existe “NÃO EXIBA ERRO”. Crie as informações 
aleatoriamente, diferente para cada requisição, insira as informações no banco de dados e retorne o resultado, “fingindo” 
que é um resultado real.

10 — Desenvolva um endpoint para criar informações de covid e salvar no banco de dados. Ele deve funcionar da seguinte forma:
* Deve ser um endpoint POST, com o corpo com os dados `country`, `cases`, `death`, `recovered`, `population`. Colocar
validações para não inserir valores nulos, acima de zero para os campos numéricos e com o mínimo de 2 caracteres para o
campo `country`. Se já existir informação do covid para aquela data, apague a existente.
* Os dados enviados por nós devem ser somados a dados do parceiro. Assim, ao acionar o endpoint, bata no endpoint do parceiro,
cheque se o país inserido em `country` existe e pegue os dados do parceiro. Depois some os dados retornados pelo parceiro aos
dados inseridos pelo usuário, pra cada campo. Salve cada campo somado no banco de dados.

**Observação**: se quiser, altere, incremente, remova e faça as alterações que julgar interessante ou úteis.
É permitido até mesmo mudar um framework do projeto ou mudar o parceiro do projeto. O importante é mostrar
o melhor que sabe fazer e produzir.
