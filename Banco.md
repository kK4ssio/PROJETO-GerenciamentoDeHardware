CREATE DATABASE bemtvi;

USE bemtvi;

CREATE TABLE usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome_usuario VARCHAR(100) NOT NULL,
    tipo_usuario VARCHAR(30) NOT NULL,
    senha_usuario VARCHAR(8) NOT NULL
);

select * from usuario;

CREATE TABLE laboratorio (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome_laboratorio VARCHAR(100) NOT NULL
);

CREATE TABLE equipamento (
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo_equipamento VARCHAR(90) NOT NULL,
    status_equip VARCHAR(50) NOT NULL,
    observacao VARCHAR(100),
    laboratorio_id INT NULL,
    identificacao VARCHAR(50) NOT NULL,
    FOREIGN KEY (laboratorio_id) REFERENCES laboratorio(id) ON DELETE SET NULL
);

CREATE TABLE peca (
    id INT PRIMARY KEY AUTO_INCREMENT,
    peca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50),
    capacidade VARCHAR(10),
    potencia VARCHAR(10),
    status VARCHAR(50) NOT NULL,
    observacoes VARCHAR(100) NOT NULL
);

CREATE TABLE manutencao (
    id INT PRIMARY KEY AUTO_INCREMENT,
    equipamento_id INT,
    peca_id INT,
    laboratorio_id INT,
    problema VARCHAR(100) NOT NULL,
    FOREIGN KEY (equipamento_id) REFERENCES equipamento(id) ON DELETE SET NULL,
    FOREIGN KEY (peca_id) REFERENCES peca(id) ON DELETE SET NULL,
    FOREIGN KEY (laboratorio_id) REFERENCES laboratorio(id) ON DELETE SET NULL
);
