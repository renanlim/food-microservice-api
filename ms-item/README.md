# Microserviço de Itens

Este é um microserviço de restaurantes desenvolvido em **Java**, utilizando **Spring Boot** e **PostgreSQL**. O projeto foi desenvolvido utilizando o **IntelliJ IDEA** como IDE.

## Descrição

Este microserviço é responsável pela gestão de itens em restaurantes, permitindo operações como criação, atualização, exclusão e listagem de itens.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para construção de aplicativos Java baseados em Spring.
- **PostgreSQL**: Banco de dados relacional para persistência de dados.
- **IntelliJ IDEA**: IDE utilizada para o desenvolvimento do projeto.
- **Maven**: Ferramenta de gerenciamento de dependências e build.

## Configuração do Projeto

### Requisitos

Certifique-se de ter os seguintes requisitos instalados:

- **JDK 11** ou superior
- **PostgreSQL**
- **Maven**

### Configuração do Banco de Dados

1. **Instalar PostgreSQL**: Certifique-se de que o PostgreSQL está instalado e em execução.
2. **Criar o Banco de Dados**: Crie um banco de dados chamado `postgres` (ou o nome que você preferir).

### Configuração do Ambiente

1. **Clonar o Repositório**:

    ```bash
    git clone <URL_DO_REPOSITORIO>
    cd <NOME_DO_DIRETORIO>
    ```

2. **Configurar as Variáveis de Ambiente**: Ajuste as configurações no arquivo `application.properties` ou diretamente no IntelliJ IDEA.

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.datasource.username=name
    spring.datasource.password=password
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Executar o Projeto**: Compile e execute o projeto usando Maven.

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Endpoints da API

- **POST /item**: Cria um novo item.
- **PUT /item/{idItem}**: Atualiza os detalhes de um item existente.
- **DELETE /restaurante/{idItem}**: Remove um item.
- **GET /item**: Lista todos os itens.
- **GET /item/{idItem}**: Obtém detalhes de um item específico.
- **GET /item/name/{nome}**: Obtém detalhes de um item pelo nome.

## Contribuição

Para contribuir com o projeto:

1. **Faça um Fork do Repositório**.
2. **Crie uma Branch para suas Mudanças**:

    ```bash
    git checkout -b minha-branch
    ```

3. **Faça o Commit das Suas Mudanças**:

    ```bash
    git add .
    git commit -m "Descrição das mudanças"
    ```

4. **Envie suas Mudanças para o Repositório Remoto**:

    ```bash
    git push origin minha-branch
    ```

5. **Abra um Pull Request** no GitHub para revisão.


