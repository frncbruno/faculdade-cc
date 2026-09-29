`````sql
CREATE DATABASE escola;

CREATE TABLE alunos(
id int auto_increment PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
idade int NOT NULL,
curso VARCHAR(50) NOT NULL
);

INSERT INTO alunos(nome, idade, curso)
VALUES
	('João', '20', 'Matemática'),
    ('Maria', '22', 'História'),
    ('Pedro', '21', 'Ciência da Computação'),
    ('Ana', '19', 'Biologia'),
    ('Carlos', '23', 'Economia');
    
CREATE TABLE professores(
id int auto_increment PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
idade int NOT NULL,
disciplina VARCHAR(50) NOT NULL
);

INSERT INTO professores(nome, idade, disciplina)
VALUES
	('Prof. Mario', '35', 'Matemática'),
    ('Prof. Augusto', '36', 'História'),
    ('Ricardo', '26', 'Sistema de Informação');
    
UPDATE professores SET idade = 40 WHERE id = 2;
UPDATE professores SET idade = 38 WHERE id = 3;
    
CREATE TABLE matriculas(
id int auto_increment PRIMARY KEY,
id_aluno INT,
id_professor INT,
data_matricula DATE,

FOREIGN KEY (id_aluno) references aluno(id),
FOREIGN KEY (id_professor) references professores(id)
);

DESC matriculas;

INSERT INTO matriculas(id_aluno, id_professor, data_matricula)
VALUES
	(1, 1, '2023-01-15'),
    (2, 2, '2023-02-20'),
    (3, 3, '2023-03-10'),
    (4, 1, '2023-04-05'),
    (5, 2, '2023-05-12');
    
SELECT *
FROM aluno;

SELECT nome, disciplina
FROM professores;

select * from pessoa;





-----------------------------------


CREATE DATABASE  BDAula01;
show databases;

use BDAula01;

CREATE TABLE pessoa(
id int auto_increment PRIMARY KEY,
nome varchar(50) not null,
sexo varchar(1) not null,
idioma varchar(10) not null
);

show tables;
desc pessoa;

INSERT INTO pessoa (nome, sexo, idioma)
VALUES
('Ricardo','M','Português'),
('Rafael','M','Português'),
('Antony','M','Francês'),
('Joe','M','Espanhol'),
('Mary','F','Inglês');

Select * from pessoa;
````
