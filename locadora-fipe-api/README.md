# Locadora FIPE API

Transformação do projeto de consulta FIPE em uma API REST web com CRUD da locadora e PostgreSQL.

## O que existe

- CRUD de clientes: `/api/clientes`
- CRUD de veículos: `/api/veiculos`
- CRUD de atendentes: `/api/atendentes`
- CRUD de contratos: `/api/contratos`
- CRUD + histórico de consultas FIPE: `/api/consultas-fipe`
- Proxy web da FIPE:
  - `GET /api/fipe/carros/marcas`
  - `GET /api/fipe/{tipo}/marcas/{marca}/modelos`
  - `GET /api/fipe/{tipo}/marcas/{marca}/modelos/{modelo}/anos`
  - `GET /api/fipe/{tipo}/marcas/{marca}/modelos/{modelo}/anos/{ano}`
- Swagger: `/swagger-ui.html`
- Health check: `/actuator/health`
- Banco criado automaticamente pelo Flyway na primeira execução.

## Rodar com Docker

1. Copie `.env.example` para `.env`.
2. Troque a senha.
3. Execute:

```bash
docker compose up --build
```

Depois abra:

- API: `http://localhost:8080`
- Swagger: `http://localhost:8080/swagger-ui.html`

## Rodar localmente sem Docker para a API

Tenha PostgreSQL disponível e configure as variáveis:

```text
DB_URL=jdbc:postgresql://localhost:5432/locadora_fipe
DB_USERNAME=postgres
DB_PASSWORD=sua_senha
```

Depois:

```bash
mvn spring-boot:run
```

## Deploy

Não coloque credenciais reais no `application.yml` nem faça commit de `.env`.
No provedor de deploy, configure `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` e `PORT` como variáveis de ambiente.

Se o provedor entregar uma URL PostgreSQL própria, adapte-a para JDBC no formato:
`jdbc:postgresql://HOST:PORTA/BANCO`.

## Observação sobre dados bancários

O DER original possui `dados_bancarios` em Cliente. Em produção, não armazene dados sensíveis de cartão/conta em texto puro. Prefira tokenização por um provedor de pagamentos ou, no mínimo, criptografia e controle de acesso.
