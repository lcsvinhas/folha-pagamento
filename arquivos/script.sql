CREATE TABLE funcionario (
    id_funcionario SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_nascimento DATE NOT NULL,
    salario_bruto NUMERIC(10, 2) NOT NULL
);

CREATE TABLE dependente (
    id_dependente SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    data_nascimento DATE NOT NULL,
    parentesco VARCHAR(20) NOT NULL,
    id_funcionario INT NOT NULL,
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario)
);

CREATE TABLE folha_pagamento (
    codigo SERIAL PRIMARY KEY,
    id_funcionario INT NOT NULL,
    data_pagamento DATE NOT NULL,
    desconto_inss NUMERIC(10, 2) NOT NULL,
    desconto_ir NUMERIC(10, 2) NOT NULL,
    salario_liquido NUMERIC(10, 2) NOT NULL,
    FOREIGN KEY (id_funcionario) REFERENCES funcionario(id_funcionario)
);