

CREATE DATABASE bemtivi;


USE bemtivi;


CREATE TABLE users (

id INT PRIMARY KEY AUTO_INCREMENT, 

nome_usuario VARCHAR(100) NOT NULL, 

tipo_usuario VARCHAR(30) NOT NULL,

senha_usuario VARCHAR(8) NOT NULL 


);


select * from users;


CREATE TABLE labs ( 

id INT PRIMARY KEY AUTO_INCREMENT,

nome_laboratorio VARCHAR(100) NOT NULL UNIQUE 


);



select * from labs;


CREATE TABLE equipas ( 

id INT PRIMARY KEY AUTO_INCREMENT,

tipo_equipamento VARCHAR(90) NOT NULL, 

status_equip VARCHAR(50), 

identificacao VARCHAR(50) NOT NULL UNIQUE, -- nome da maquina

lab_pertencete VARCHAR(50),

observacao VARCHAR(100),
solucao VARCHAR(100)

);

select * from equipas;


