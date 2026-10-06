```sql
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
UPDATE professores SET nome = 'Prof. Ricardo' WHERE id = 3;
    
CREATE TABLE matriculas(
id int auto_increment PRIMARY KEY,
id_aluno INT,
id_professor INT,
data_matricula DATE,

FOREIGN KEY (id_aluno) references alunos(id),
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
    
SELECT nome, curso
FROM alunos;

SELECT nome, disciplina
FROM professores;
```
