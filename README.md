# ATIVIDADE-Fase2-Inova-oe-Prototipa-oo-Uchoa-Paiva-Moreira-Ketes

# Tema: Aluguel de Carros

## Integrantes

* Juan Moreira Morais
* Guilherme Ketes Maia
* Henrick Sousa Uchoa de Carvalho
* Allan Paiva Lopes Filho

## Objetivo Geral

Desenvolver um banco de dados relacional para uma empresa de aluguel de veículos, com foco em motoristas de aplicativos. A proposta é organizar as principais informações relacionadas aos clientes, atendentes, veículos e contratos de aluguel, permitindo um melhor controle das operações realizadas pela empresa. O banco deverá possibilitar o cadastro dos clientes e atendentes, incluindo a possibilidade de um atendente também ser cliente, além do registro dos veículos disponíveis para aluguel, com informações como placa, marca, modelo e tipo de veículo. Também serão armazenadas as informações dos contratos, relacionando o cliente ao veículo alugado e registrando dados como prazo de expiração, data do contrato e forma de pagamento.

## Público-alvo

O banco de dados será desenvolvido para empresas de aluguel de veículos que atendem principalmente motoristas de aplicativos. O foco está em pessoas que utilizam veículos alugados para realizar suas atividades profissionais em plataformas como Uber e 99, facilitando o gerenciamento dos clientes, veículos e contratos de aluguel



```sql
CREATE TABLE cliente (
    id_cliente SERIAL PRIMARY KEY,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    nome VARCHAR(20) NOT NULL,
    sobrenome VARCHAR(50) NOT NULL,
    endereco TEXT NOT NULL,
    dados_bancarios TEXT UNIQUE NOT NULL,
    email TEXT UNIQUE NOT NULL
	);

-- Tabela de Veículos
CREATE TABLE veiculo (
    id_veiculo SERIAL PRIMARY KEY,
    placa VARCHAR(7) UNIQUE NOT NULL,
    marca TEXT NOT NULL,
    modelo TEXT NOT NULL,
    tipo TEXT NOT NULL
	);

-- Tabela de Atendentes
CREATE TABLE atendente (
    id_atendente SERIAL PRIMARY KEY,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    nome VARCHAR(20) NOT NULL,
    sobrenome VARCHAR(50) NOT NULL,
    endereco TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
	Ativo BOOLEAN DEFAULT TRUE
	);

-- Contrato
CREATE TABLE contrato (
    numero_contrato SERIAL PRIMARY KEY,
    data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo_pagamento TEXT NOT NULL,
    inicio_vigencia DATE NOT NULL,
    fim_vigencia DATE NOT NULL,
    id_cliente INT NOT NULL,
    id_veiculo INT NOT NULL,
	id_atendente INT NOT NULL,
	CONSTRAINT fk_contrato_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    CONSTRAINT fk_contrato_veiculo FOREIGN KEY (id_veiculo) REFERENCES veiculo(id_veiculo),
	CONSTRAINT fk_contrato_atendente FOREIGN KEY (id_atendente) REFERENCES atendente(id_atendente)
	);

-- Consulta FIPE
CREATE TABLE consulta_fipe (
    id_consulta SERIAL PRIMARY KEY,
    codigo_fipe VARCHAR(20) NOT NULL,
    valor NUMERIC(12,2) NOT NULL,
    mes_referencia TEXT NOT NULL,
    data_consulta TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_veiculo INT NOT NULL,
	CONSTRAINT fk_consulta_veiculo FOREIGN KEY (id_veiculo) REFERENCES veiculo(id_veiculo)
	);
```
