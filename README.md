# Contrato Alert API

API backend para gestão de contratos com alertas automáticos de vencimento.
O sistema foi projetado como **SaaS multiempresa**, permitindo que várias empresas utilizem a mesma aplicação com isolamento total de dados.

## 🚀 Tecnologias

* Java 21
* Spring Boot
* Spring Security
* JWT Authentication
* Refresh Token
* MySQL
* JPA / Hibernate
* Maven

## 🔐 Autenticação

A API utiliza **JWT com Refresh Token**.

Fluxo de autenticação:

1. Usuário faz login
2. API retorna:

   * `accessToken` (curta duração)
   * `refreshToken` (longa duração)
3. Quando o access token expira:

   * o frontend chama `/auth/refresh`
4. Um novo access token é gerado

Logout revoga o **refresh token**, impedindo novas renovações.

## 🏢 Arquitetura Multiempresa

O sistema foi projetado para suportar múltiplas empresas.

Regras principais:

* Cada **usuário pertence a uma empresa**
* Uma **empresa pode ter vários usuários**
* Todos os dados são isolados por `empresaId`
* O `empresaId` é extraído do **JWT**

Estrutura:

```
Empresa
 └── Usuários
       ├── ADMIN
       ├── OPERADOR
       └── LEITURA
```

## 👤 Roles

* `ADMIN` – gerencia usuários e contratos
* `USER` – operações padrão no sistema

## 🔑 Endpoints de Autenticação

### Login

```
POST /auth/login
```

Retorna:

```
{
  "accessToken": "...",
  "refreshToken": "..."
}
```

---

### Refresh Token

```
POST /auth/refresh
```

Gera um novo access token.

---

### Logout

```
POST /auth/logout
```

Revoga o refresh token.

---

## 📄 Endpoints de Contrato

```
GET /contrato
GET /contrato/me
POST /contrato
PUT /contrato/{id}
DELETE /contrato/{id}
```

Todos protegidos por autenticação JWT.

## 🛡️ Segurança

* Autenticação Stateless
* JWT Filter customizado
* Access token de curta duração
* Refresh token armazenado em banco
* Revogação de refresh token no logout
* Tratamento elegante de erros de autenticação

## 📦 Estrutura do Projeto

```
config/
  SecurityConfig
  JwtAuthenticationFilter

controller/
  AuthController
  ContratoController

service/
  JwtService
  RefreshTokenService

domain/
  Usuario
  Empresa
  RefreshToken
  Contrato

repository/
  UsuarioRepository
  EmpresaRepository
  RefreshTokenRepository
  ContratoRepository
```

## 📌 Status do Projeto

Em desenvolvimento.

Funcionalidades já implementadas:

* [x] Autenticação JWT
* [x] Refresh Token
* [x] Logout com revogação
* [x] Segurança com Spring Security
* [x] Multiempresa
* [x] CRUD inicial de contratos

## 📖 Objetivo

Este projeto foi criado para estudo e prática de:

* Arquitetura backend moderna
* Segurança com Spring Security
* JWT + Refresh Token
* Arquitetura SaaS multiempresa
* Boas práticas em APIs REST
