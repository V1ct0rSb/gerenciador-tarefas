# Gerenciador de Tarefas

Este projeto é um gerenciador de tarefas desenvolvido em Java com Spring Boot. Ele oferece uma API RESTful para gerenciar tarefas, incluindo funcionalidades de autenticação e autorização com JWT e documentação da API com Swagger/OpenAPI.

## Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Security (OAuth2 Authorization Server)
* PostgreSQL (banco de dados)
* H2 Database (para testes)
* Lombok
* Swagger/OpenAPI (para documentação da API)
* Docker e Docker Compose (para ambiente de desenvolvimento e produção)

## Funcionalidades

* **Gerenciamento de Tarefas:** CRUD completo para tarefas.
* **Autenticação e Autorização:** Utiliza JWT para proteger os endpoints da API.
* **Documentação da API:** Gerada automaticamente com Swagger/OpenAPI.
* **Persistência de Dados:** Utiliza PostgreSQL como banco de dados principal e H2 para testes.
* **Containerização:** Configuração com Docker e Docker Compose para fácil implantação.

## Como Rodar o Projeto

### Pré-requisitos

* Java 17 ou superior
* Maven
* Docker e Docker Compose (opcional, para rodar com containers)

### Configuração do Banco de Dados

O projeto está configurado para usar PostgreSQL. Você pode configurar as credenciais do banco de dados nos arquivos `application-dev.properties` (para desenvolvimento) e `application-prod.properties` (para produção) localizados em `src/main/resources`.

Exemplo de configuração para `application-dev.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/gerenciador_tb
spring.datasource.username=postgres
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
