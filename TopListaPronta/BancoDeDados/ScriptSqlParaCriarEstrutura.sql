CREATE DATABASE "TopListaa"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE "TopLista"
    IS 'Este banco vai servir inicialmente como a estrutura para guardar os dados criados na utilização da solução TopLista, desenvolvida pela equipe Charizard.';

CREATE TABLE public."Cupom"
(
    "ID" "char" NOT NULL,
    "DataDeEmissao" date NOT NULL,
    PRIMARY KEY ("ID")
);

ALTER TABLE IF EXISTS public."Cupom"
    OWNER to postgres;

-- Table for Usuarios
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_cadastro TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Table for Estabelecimentos
CREATE TABLE estabelecimentos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(255),
    telefone VARCHAR(20)
);

-- Table for Produtos
CREATE TABLE produtos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    unidade_medida VARCHAR(20) -- e.g., 'kg', 'litro', 'unidade'
);

-- Table for Listas de Interesses (Shopping Lists)
CREATE TABLE listas_interesses (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL,
    nome_lista VARCHAR(100) NOT NULL,
    data_criacao DATE NOT NULL DEFAULT CURRENT_DATE,
    ativa BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Junction Table for Listas and Produtos (Many-to-Many relationship)
CREATE TABLE lista_produtos (
    lista_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL DEFAULT 1.00,
    comprado BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (lista_id, produto_id),
    FOREIGN KEY (lista_id) REFERENCES listas_interesses(id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);

-- Table for Sugestoes de Produtos (from motorSugestao)
CREATE TABLE sugestoes_produtos (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL,
    produto_id INT NOT NULL,
    estabelecimento_id INT, -- Optional: a suggestion might be tied to a specific establishment
    data_sugestao TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    relevancia DECIMAL(5,2), -- e.g., 0.00 to 100.00
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE,
    FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimentos(id) ON DELETE SET NULL
);

-- Table for Relatorios de Estabelecimentos
CREATE TABLE relatorios_estabelecimentos (
    id SERIAL PRIMARY KEY,
    estabelecimento_id INT,
    data_geracao TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tipo_relatorio VARCHAR(50) NOT NULL,
    dados_relatorio TEXT, -- JSON or plain text for report details
    FOREIGN KEY (estabelecimento_id) REFERENCES estabelecimentos(id) ON DELETE SET NULL
);

-- Table for Relatorios de Usuarios
CREATE TABLE relatorios_usuarios (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL,
    data_geracao TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tipo_relatorio VARCHAR(50) NOT NULL,
    dados_relatorio TEXT, -- JSON or plain text for report details
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);
