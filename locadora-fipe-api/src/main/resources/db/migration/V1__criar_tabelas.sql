CREATE TABLE cliente (
    id_cliente BIGSERIAL PRIMARY KEY,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100),
    endereco VARCHAR(255),
    dados_bancarios VARCHAR(255),
    email VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE veiculo (
    id_veiculo BIGSERIAL PRIMARY KEY,
    placa VARCHAR(10) NOT NULL UNIQUE,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(150) NOT NULL,
    tipo VARCHAR(30) NOT NULL
);

CREATE TABLE atendente (
    id_atendente BIGSERIAL PRIMARY KEY,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100),
    endereco VARCHAR(255),
    email VARCHAR(150) NOT NULL UNIQUE
);

CREATE TABLE contrato (
    numero_contrato BIGSERIAL PRIMARY KEY,
    data DATE NOT NULL,
    tipo_pagamento VARCHAR(50) NOT NULL,
    inicio_vigencia DATE NOT NULL,
    fim_vigencia DATE NOT NULL,
    id_cliente BIGINT NOT NULL REFERENCES cliente(id_cliente),
    id_veiculo BIGINT NOT NULL REFERENCES veiculo(id_veiculo),
    id_atendente BIGINT NOT NULL REFERENCES atendente(id_atendente),
    CONSTRAINT chk_vigencia CHECK (fim_vigencia >= inicio_vigencia)
);

CREATE TABLE consulta_fipe (
    id_consulta BIGSERIAL PRIMARY KEY,
    id_veiculo BIGINT NOT NULL REFERENCES veiculo(id_veiculo) ON DELETE CASCADE,
    codigo_fipe VARCHAR(30) NOT NULL,
    valor NUMERIC(15,2) NOT NULL,
    mes_referencia VARCHAR(80),
    data_consulta DATE NOT NULL
);

CREATE INDEX idx_contrato_cliente ON contrato(id_cliente);
CREATE INDEX idx_contrato_veiculo ON contrato(id_veiculo);
CREATE INDEX idx_contrato_atendente ON contrato(id_atendente);
CREATE INDEX idx_consulta_fipe_veiculo ON consulta_fipe(id_veiculo);
