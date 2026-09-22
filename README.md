# ATIVIDADE-Fase2-Inova-oe-Prototipa-oo-Uchoa-Paiva-Moreira-Ketes

## Integrantes

* Juan Moreira Morais
* Guilherme Ketes Maia
* Henrick Sousa Uchoa de Carvalho
* Allan Paiva Lopes Filho

## Objetivo Geral

Desenvolver um banco de dados relacional para uma empresa de aluguel de veículos, com foco em motoristas de aplicativos. A proposta é organizar as principais informações relacionadas aos clientes, atendentes, veículos e contratos de aluguel, permitindo um melhor controle das operações realizadas pela empresa. O banco deverá possibilitar o cadastro dos clientes e atendentes, incluindo a possibilidade de um atendente também ser cliente, além do registro dos veículos disponíveis para aluguel, com informações como placa, marca, modelo e tipo de veículo. Também serão armazenadas as informações dos contratos, relacionando o cliente ao veículo alugado e registrando dados como prazo de expiração, data do contrato e forma de pagamento.

## Público-alvo

O banco de dados será desenvolvido para empresas de aluguel de veículos que atendem principalmente motoristas de aplicativos. O foco está em pessoas que utilizam veículos alugados para realizar suas atividades profissionais em plataformas como Uber e 99, facilitando o gerenciamento dos clientes, veículos e contratos de aluguel




-- =========================================================
-- DER Locadora de Veículos — script de criação (PostgreSQL)
-- Inclui a tabela CONSULTA_FIPE (integração com a API da FIPE)
-- =========================================================

CREATE TABLE cliente (
    id_cliente       SERIAL PRIMARY KEY,
    cpf              VARCHAR(14)  NOT NULL UNIQUE,
    nome             VARCHAR(100) NOT NULL,
    sobrenome        VARCHAR(100) NOT NULL,
    endereco         VARCHAR(255),
    dados_bancarios  VARCHAR(255),
    email            VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE veiculo (
    id_veiculo   SERIAL PRIMARY KEY,
    placa        VARCHAR(8)  NOT NULL UNIQUE,
    marca        VARCHAR(60) NOT NULL,
    modelo       VARCHAR(60) NOT NULL,
    tipo         VARCHAR(40)
);

CREATE TABLE atendente (
    id_atendente SERIAL PRIMARY KEY,
    cpf          VARCHAR(14)  NOT NULL UNIQUE,
    nome         VARCHAR(100) NOT NULL,
    sobrenome    VARCHAR(100) NOT NULL,
    endereco     VARCHAR(255),
    email        VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE contrato (
    numero_contrato  SERIAL PRIMARY KEY,
    data             DATE NOT NULL DEFAULT CURRENT_DATE,
    tipo_pagamento   VARCHAR(30) NOT NULL,
    inicio_vigencia  DATE NOT NULL,
    fim_vigencia     DATE NOT NULL,
    id_cliente       INT NOT NULL REFERENCES cliente(id_cliente),
    id_veiculo       INT NOT NULL REFERENCES veiculo(id_veiculo),
    id_atendente     INT NOT NULL REFERENCES atendente(id_atendente),

    CONSTRAINT chk_vigencia CHECK (fim_vigencia >= inicio_vigencia)
);

-- Tabela nova: histórico de consultas à API da Tabela FIPE
-- 1 veículo pode ter N consultas ao longo do tempo (relação 1:N)
CREATE TABLE consulta_fipe (
    id_consulta      SERIAL PRIMARY KEY,
    id_veiculo       INT NOT NULL REFERENCES veiculo(id_veiculo),
    codigo_fipe      VARCHAR(20) NOT NULL,
    valor            NUMERIC(12,2) NOT NULL,
    mes_referencia   VARCHAR(30) NOT NULL,
    data_consulta    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Índices para acelerar as buscas mais comuns
CREATE INDEX idx_contrato_cliente   ON contrato(id_cliente);
CREATE INDEX idx_contrato_veiculo   ON contrato(id_veiculo);
CREATE INDEX idx_contrato_atendente ON contrato(id_atendente);
CREATE INDEX idx_consulta_veiculo   ON consulta_fipe(id_veiculo);
