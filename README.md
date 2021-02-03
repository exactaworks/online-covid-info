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
O projeto parece não estar funcionando muito bem. As primeiras 4 primeiras questões são relacionadas a isso.

1 - O projeto não tem tratamento de exceção algum. Ao executar o endpoint pela primeira vez por exemplo, ele tras o
 objeto de response todo NULL. Que tal tratarmos esse e todos os erros que pode haver no sistema?

2 - Coloque logs na aplicação. Onde achar interessante, coloque logs do tipo DEBUG. Onde achar essencial coloque logs 
do tipo INFO. Onde pode haver erros, coloque logs do tipo ERROR.

3 - Agora que os erros do projeto estão tratados e com log, que tal corrigir o erro do projeto para que retorne o resultado
da resposta do parceiro?

4 - O projeto tem um teste unitário para o service. Mas ele está quebrado. Que tal arruma-lo antes de prosseguir com 
as atividades?

**O projeto agora deve estar com tratamentos de exceção, logs e funcionando adequadamente, inclusive o teste. 
As 4 próximas questões são features adicionais ao projeto e podem ser realizadas em qualquer ordem.**

5 - Sempre ao executar o endpoint, salvamos a requisição no banco de dados. Contudo, os dados do parceiro são atualizados
diariamente. Então é desnecessário salvar no banco de dados se a requisição daquele dia já estiver no banco de dados para
aquela região. 

6 - Crie um endpoint para buscar todas as informações do banco de dados. Elas devem vir ordenadas por data.

7 - Crie um endpoint para deletar informações do banco de dados de um determinado país, passado por parâmetro no endpoint
e independente de datas. Opcionalmente, seria ótimo se fosse um soft delete (também conhecido como false delete ou 
exclusão lógica).

8 - Altere o endpoint. Permita ao usuário, opcionalmente, colocar um nome de país. Se ele colocar um nome de país, devemos 
chamar o parceiro com um endpoint passando o nome do país, para retornar somente os dados daquele país ao invés de retornar
os dados do mundo todo.

9 - Crie um endpoint para buscar no parceiro informações de continente. O endpoint deve ser novo e deve armazenar em uma
tabela separada chamada de `continent`. Se o usuário tentar um continente que não existe, crie as informações aleatoriamente,
insira as informações no banco de dados e retorne o resultado.

10 - Desenvolva um endpoint para criar informações de covid e salvar no banco de dados. Ele deve funcionar da seguinte forma:
* Deve ser um endpoint POST, com o corpo com os dados `country`, `cases`, `death`, `recovered`, `population`. Colocar
validações para não inserir valores nulos, acima de zero para os campos numéricos e com o mínimo de 1 caractere para o
campo `country`.
* Ao acionar o endpoint, bata no endpoint do parceiro, cheque se o país inserido em `country` existe e pegue os dados
do parceiro. Some os dados retornados pelo parceiro aos dados inseridos pelo usuário, pra cada campo. Salve cada campo
somado no banco de dados.

**Observação**: Se quiser, altere, incremente, remova e faça as alterações que julgar interessante ou úteis.
É permitido até mesmo mudar um framework do projeto.