CREATE DATABASE bemtvi;

USE bemtvi;

CREATE TABLE usuario ( 

id INT PRIMARY KEY AUTO_INCREMENT,

nome_usuario VARCHAR(100) NOT NULL, 

tipo_usuario VARCHAR(30) NOT NULL, 

senha_usuario VARCHAR(8) NOT NULL 

);

select * from usuario;

CREATE TABLE lab ( 

id INT PRIMARY KEY AUTO_INCREMENT, 

nome_laboratorio VARCHAR(100) NOT NULL UNIQUE 

);

CREATE TABLE equipamentos ( 
id INT PRIMARY KEY AUTO_INCREMENT, 

tipo_equipamento VARCHAR(90) NOT NULL,

status_equip VARCHAR(50) NOT NULL, 

observacao VARCHAR(100), 

laboratorio_id INT, 

identificacao VARCHAR(50) NOT NULL, 

FOREIGN KEY (laboratorio_id) REFERENCES laboratorio(id) ON DELETE SET NULL 


);

CREATE TABLE manutencoes ( 
id INT PRIMARY KEY AUTO_INCREMENT, 

equipamento_id INT,  

laboratorio_id INT, 

problema VARCHAR(100),

solucao VARCHAR(100), 

FOREIGN KEY (equipamento_id) REFERENCES equipamento(id) ON DELETE SET NULL, 

FOREIGN KEY (laboratorio_id) REFERENCES laboratorio(id) ON DELETE SET NULL 

);