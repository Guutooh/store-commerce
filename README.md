# E-commerce API

## Descrição do Projeto

Este é um projeto de API RESTful para gerenciar um sistema de e-commerce. O sistema inclui funcionalidades como autenticação de usuários, gerenciamento de produtos, categorias, pedidos, pagamentos e controle de roles (permissões). A API é baseada em Spring Boot e segue boas práticas de desenvolvimento, incluindo padrões de projeto, testes e tratamento de erros.

---

## Regras de Negócio

1. **Gerenciamento de Produtos:**

   - Produtos são associados a uma ou mais categorias.
   - Cada produto possui nome, descrição, preço e imagem.

2. **Gerenciamento de Categorias:**

   - As categorias podem ser atribuídas a produtos.

3. **Sistema de Pedidos:**

   - Um pedido é composto por um cliente, uma lista de itens e um status.
   - Possui integração com pagamentos.

4. **Sistema de Usuários e Permissões:**

   - Usuários possuem roles que determinam seus níveis de permissões (ADMIN, OPERATOR, etc.).
   - Autenticação baseada em tokens JWT.

5. **Tratamento de Exceções:**

   - Mensagens padronizadas para erros de validação, recursos não encontrados e falhas de banco de dados.

---

## Tecnologias Utilizadas

### Backend

- **Java 17**
- **Spring Boot 3.0**:
  - Spring Security
  - Spring Data JPA
  - Spring Validation
- **Hibernate**
- **JWT (JSON Web Token)**
- **ModelMapper**

### Banco de Dados

- **H2 Database** (ambiente de teste)
- **PostgreSQL** (ambiente de produção)

### Ferramentas e Dependências

- **Maven** (gerenciamento de dependências)
- **Lombok** (para redução de boilerplate)
- **Jakarta Persistence API** (JPA)
- **Docker** (para conteinerização do ambiente)

---

## Funcionalidades Principais

1. **Autenticação e Autorizacão:**

   - Autenticação via JWT.
   - Controle de acesso por roles.

2. **Gerenciamento de Produtos:**

   - CRUD completo de produtos.
   - Busca por nome com paginação.

3. **Gerenciamento de Categorias:**

   - Listagem e associação com produtos.

4. **Sistema de Pedidos:**

   - CRUD de pedidos e itens relacionados.
   - Status dinâmico de pedidos (esperando pagamento, pago, enviado, etc.).

5. **Tratamento de Erros:**

   - Validação de campos.
   - Respostas personalizadas para exceções como `ResourceNotFoundException`, `DatabaseException` e `ForbiddenException`.

---

## Estrutura do Projeto

```
/src
  |-- main
  |     |-- java/br/com/dev/ecommerce
  |     |      |-- configuration
  |     |      |-- controllers
  |     |      |-- dto
  |     |      |-- entities
  |     |      |-- exceptions
  |     |      |-- mapper
  |     |      |-- repositories
  |     |      |-- services
  |     |      |-- EcommerceApplication.java
  |     |-- resources
  |            |-- application.properties
  |            |-- application-test.properties
  |            |-- import.sql
```

---

## Configuração do Ambiente de Desenvolvimento

1. **Clone o Repositório:**

   ```bash
   git clone <url-do-repositorio>
   ```

2. **Configure o Banco de Dados:**

   - Atualize as configurações do `application.properties` com suas credenciais.

3. **Execute o Projeto:**

   - Utilize o Maven:
     ```bash
     mvn spring-boot:run
     ```
   - Ou o comando Docker (se configurado):
     ```bash
     docker-compose up
     ```

4. **Acesse a API:**

   - Base URL: `http://localhost:8080`

---

## Como Rodar os Testes

1. Certifique-se de que o banco de dados H2 está configurado no perfil de teste.
2. Execute os testes com o Maven:
   ```bash
   mvn test
   ```

---


## Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).

