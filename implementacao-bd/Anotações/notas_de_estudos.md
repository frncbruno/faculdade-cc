# Aula 05 - 28/08/2026

```sql
use EMPRESA;

-- UNION
SELECT P.Projlocal AS 'Local'
FROM PROJETO AS P

UNION

SELECT L.Dlocal AS 'Local'
FROM LOCALIZACAO_DEP AS L;

-- EXCEPT
SELECT F.Cpf, F.Pnome
FROM FUNCIONARIO AS F

EXCEPT

SELECT D.Cpf_gerente, F.Pnome
FROM DEPARTAMENTO AS D

JOIN FUNCIONARIO AS F
ON D.Cpf_gerente = F.Cpf

-- INTERSECT
SELECT Cpf
FROM FUNCIONARIO

INTERSECT

SELECT Cpf_supervisor
FROM FUNCIONARIO

-- GROUP BY
SELECT COUNT(F.Cpf) AS 'Qtd_Cpf', F.Sexo
FROM FUNCIONARIO AS F
GROUP BY F.Sexo;

-- ///
SELECT COUNT(F.Cpf) AS 'Qtd_Cpf', D.Dnome
FROM FUNCIONARIO AS F

JOIN DEPARTAMENTO AS D
ON F.Dnr = D.Dnumero

GROUP BY D.Dnome;

-- ///
SELECT SUM(F.Salario) AS 'Soma Salario', D.Dnome
FROM FUNCIONARIO AS F

JOIN DEPARTAMENTO AS D
ON F.Dnr = D.Dnumero

GROUP BY D.Dnome;

-- ///
SELECT AVG(T.Horas) AS 'M Hrs', P.Projnome
FROM TRABALHA_EM AS T

JOIN PROJETO AS P
ON T.Pnr = P.Projnumero

GROUP BY P.Projnome

-- ///
SELECT MAX(F.Salario) AS 'Salario', D.Dnome
FROM FUNCIONARIO AS F

JOIN DEPARTAMENTO AS D
ON F.Dnr = D.Dnumero

GROUP BY D.Dnome

-- HAVING
SELECT COUNT(F.Cpf) AS 'Func', D.Dnome
FROM FUNCIONARIO AS F

JOIN DEPARTAMENTO AS D
ON F.Dnr = D.Dnumero

GROUP BY D.Dnome
HAVING COUNT(F.Cpf) > 3;

-- ///
SELECT SUM(T.Horas) AS 'Min Hrs', P.Projnome
FROM TRABALHA_EM AS T

JOIN PROJETO AS P
ON T.Pnr = P.Projnumero

GROUP BY P.Projnome
HAVING SUM(T.Horas) >= 50;

-- EXISTS
SELECT * 
FROM DEPARTAMENTO AS D
WHERE EXISTS(
	SELECT 1
	FROM PROJETO, DEPARTAMENTO
	WHERE PROJETO.Dnum = DEPARTAMENTO.Dnumero
)

-- ANY
SELECT *
FROM FUNCIONARIO
WHERE Salario > ANY(
SELECT F.Salario
FROM FUNCIONARIO AS F

JOIN DEPARTAMENTO AS D
ON F.Dnr = D.Dnumero

WHERE D.Dnome = 'Administração')
ORDER BY Salario DESC;

-- ALL
SELECT *
FROM FUNCIONARIO
WHERE Salario > ALL(
SELECT F.Salario
FROM FUNCIONARIO AS F

JOIN DEPARTAMENTO AS D
ON F.Dnr = D.Dnumero

WHERE D.Dnome = 'Administração')
ORDER BY Salario DESC;

-- DECLARE
DECLARE @nome VARCHAR(100),
		@idade INT,
		@salario DECIMAL(10,2),
		@data DATE;

SET @nome = 'Herysson R. Figueiredo';
SET @idade = 38;
SET @salario = 2400;
SET @data = GETDATE();

-- PRINT 'Olá, SQL! Meu nome é: ' + @nome; 
PRINT 'Nome : ' + @nome + 
	  ', Idade: ' + CAST(@idade AS VARCHAR(10));

SELECT 
	@nome AS 'Nome',
	@idade AS 'Idade',
	@salario AS 'Salario',
	@data AS 'Data de hoje'; 

-- Armazenando variáveis com select
DECLARE @nomeDpt VARCHAR(50);

SELECT @nomeDpt = Dnome 
FROM DEPARTAMENTO AS D
WHERE D.Dnumero = 4;

PRINT 'Departamento: ' + @nomeDpt

-- Exemplo com cálculo
DECLARE @sJennifer MONEY;

SELECT @sJennifer = Salario
FROM FUNCIONARIO
WHERE Pnome = 'Jennifer';

PRINT 'Salário antigo: ' + CAST(@sJennifer AS VARCHAR(20))
PRINT 'Salário com ajustes de +10%: ' + CAST(@sJennifer * 1.1 AS VARCHAR(20));

-- Exemplo com datas
DECLARE @dataJennifer DATE,
		@idadeJennifer INT;

SELECT @dataJennifer = Datanasc
FROM FUNCIONARIO
WHERE Pnome = 'Jennifer';

SET @idadeJennifer = (DATEDIFF(YEAR, @dataJennifer, GETDATE()));

PRINT @idadeJennifer;
```

# Aula 04 - 21/08/2026

```sql
-- IN

SELECT *
FROM FUNCIONARIO AS F

LEFT JOIN TRABALHA_EM AS T
ON T.Fcpf = F.Cpf

LEFT JOIN PROJETO AS P
ON P.Projnumero = T.Pnr;

SELECT *
FROM FUNCIONARIO
WHERE Salario IN (25000, 30000);

SELECT CONCAT(F.Pnome, ' ' ,F.Unome) AS Nome, T.Pnr AS Numero_Projeto
FROM TRABALHA_EM AS T, FUNCIONARIO AS F
WHERE 
	F.Cpf = T.Fcpf
	AND Pnr IN (
		SELECT Pnr 
		FROM TRABALHA_EM 
		WHERE Fcpf = (
			SELECT Cpf 
			FROM FUNCIONARIO
			WHERE Pnome = 'Fernando')
			)
	AND F.Pnome <> 'Fernando'; -- diferente de

-- BETWEEN

SELECT *
FROM FUNCIONARIO
WHERE Salario BETWEEN 30000 AND 40000
AND Dnr = 5;

-- INNER JOIN

SELECT CONCAT(F.Pnome, ' ' ,F.Unome) AS Nome_completo, F.Endereco, D.Dnome AS Departamento
FROM FUNCIONARIO AS F

INNER JOIN DEPARTAMENTO AS D
ON F.Dnr = D.Dnumero

WHERE F.Dnr = 5;

/////

SELECT CONCAT(F.Pnome, ' ' ,F.Unome) AS Nome_completo, P.Projnome
FROM FUNCIONARIO AS F

INNER JOIN TRABALHA_EM AS T
ON T.Fcpf = F.Cpf

INNER JOIN PROJETO AS P
ON P.Projnumero = T.Pnr

WHERE P.Projnome = 'ProdutoX';

////

SELECT 
	P.Projnumero, 
	D.Dnumero, 
	D.Dnome, 
	D.Cpf_gerente, 
	F.Unome, 
	P.Projlocal, 
	F.Datanasc,
	F.Endereco
FROM FUNCIONARIO AS F

INNER JOIN TRABALHA_EM AS T
ON T.Fcpf = F.Cpf

INNER JOIN PROJETO AS P
ON P.Projnumero = T.Pnr

INNER JOIN DEPARTAMENTO AS D
ON P.Dnum = D.Dnumero

WHERE P.Projlocal = 'Mauá'

-- LEFT JOIN

SELECT *
FROM DEPARTAMENTO AS D

LEFT JOIN FUNCIONARIO AS F
ON D.Dnumero = F.Dnr

WHERE F.Cpf IS NULL; 

-- RIGHT JOIN

SELECT *
FROM DEPARTAMENTO AS D

RIGHT JOIN FUNCIONARIO AS F
ON D.Dnumero = F.Dnr

-- CROSS/FULL JOIN

SELECT *
FROM FUNCIONARIO AS F

FULL JOIN DEPARTAMENTO AS D
ON F.Dnr = D.Dnumero

WHERE
	D.Dnumero IS NULL
	OR F.Cpf IS NULL;

-- SELF JOIN (quando a tabela se relaciona a ela mesma)
-- comparar linhas e posições hierarquicas

SELECT t1.Pnome, t2.Cpf_supervisor
FROM FUNCIONARIO AS t1

JOIN FUNCIONARIO AS t2
ON t1.Cpf = t2.Cpf

WHERE 
	t2.Cpf_supervisor IS NOT NULL;


SELECT 
	F.Pnome AS 'Funcionario',
	S.Unome AS 'Supervisor'
FROM FUNCIONARIO AS F

JOIN FUNCIONARIO AS S
ON F.Cpf_supervisor = S.Cpf_supervisor

ORDER BY S.Unome;

-- UNION/INTERSECT/EXCEPT

SELECT 
	F.Pnome AS 'Nome', 
	F.Sexo AS 'Sexo', 
	F.Datanasc AS 'Data'
FROM FUNCIONARIO AS F

UNION

SELECT 
	D.Nome_dependente AS 'Nome',
	D.Sexo AS 'Sexo',
	D.Datanasc AS 'Data'
FROM DEPENDENTE AS D;
```

# Aula 03 - 14/08/2026
```sql
-- Distinct
SELECT DISTINCT F.Salario
FROM FUNCIONARIO AS F;

SELECT DISTINCT F.Sexo
FROM FUNCIONARIO AS F;

-- WHERE
SELECT *
FROM FUNCIONARIO AS F
WHERE F.Pnome = 'Carlos';

-- AND
SELECT *
FROM FUNCIONARIO AS F
WHERE 
	F.Salario >= 30000
	AND F.Sexo = 'M';

-- OR
SELECT *
FROM FUNCIONARIO AS F
WHERE 
	F.Endereco LIKE '%São Paulo%' 
	OR F.Endereco LIKE '%Curitiba%';

-- NOT 
SELECT * 
FROM FUNCIONARIO AS F
WHERE 
	F.Endereco NOT LIKE '%SP%';

-- ORDER BY
SELECT 
    F.Pnome AS 'Nome',
	F.Unome AS 'Sobrenome',
	F.Salario AS 'Salario',
    (F.Salario + COALESCE(F.Bonus, 0)) * 12 AS Custo_Anual
FROM FUNCIONARIO AS F
ORDER BY (F.Salario + COALESCE(F.Bonus, 0)) * 12 DESC;

-- NULL
SELECT *
FROM FUNCIONARIO AS F
WHERE F.Cpf_supervisor IS NULL;

-- SELECT TOP/LIMIT
SELECT TOP 3
    F.Pnome AS 'Nome',
	F.Unome AS 'Sobrenome',
	F.Salario AS 'Salario',
    (F.Salario + COALESCE(F.Bonus, 0)) * 12 AS Custo_Anual
FROM FUNCIONARIO AS F
ORDER BY F.Salario DESC;

-- MIN() MAX()
SELECT
	MIN(F.Salario) AS 'Menor salario',
	MAX(F.Salario) AS 'Maior salario'
FROM FUNCIONARIO AS F

-- SELECT alinhado
SELECT *
FROM FUNCIONARIO AS F
WHERE 
	F.Salario = (SELECT MIN(Salario) FROM FUNCIONARIO);

-- Criação de variáveis
DECLARE @salario_min DECIMAL(10, 2);
SET @salario_min = (SELECT MIN(Salario) FROM FUNCIONARIO);
PRINT @salario_min;

SELECT *
FROM FUNCIONARIO AS F
WHERE 
	F.Salario = @salario_min;

-- COUNT()
SELECT COUNT(F.Cpf)
FROM FUNCIONARIO AS F;

SELECT COUNT(D.Nome_dependente)
FROM DEPENDENTE AS D;

SELECT
	(SELECT COUNT(F.Cpf)
FROM FUNCIONARIO AS F) +
	(SELECT COUNT(D.Nome_dependente)
FROM DEPENDENTE AS D)
	AS 'Qtd Pessoas';

-- AVG()
SELECT AVG(F.Salario)
FROM FUNCIONARIO AS F

-- Pessoas que ganham abaixo da media salarial
SELECT * 
FROM FUNCIONARIO AS F 
WHERE F.Salario < (SELECT AVG(Salario) FROM FUNCIONARIO)
ORDER BY F.Salario ASC;

-- SUM()
SELECT SUM(F.Salario) * 12 AS Custo_Anual
FROM FUNCIONARIO AS F;

-- LIKE
SELECT *
FROM FUNCIONARIO AS F
WHERE F.Datanasc LIKE '__72%';

```

# Aula 02 - 07/08/2026
```sql
-- Criando meu banco
CREATE DATABASE biblioteca;
DROP SCHEMA biblioteca;

-- Colocar o banco criado em uso
use biblioteca;

-- Criar o banco
CREATE TABLE Autor ( 
	id INT PRIMARY KEY,
    nome VARCHAR(151) NOT NULL,
    nacionalidade VARCHAR(74)
);

CREATE TABLE Editora(
	id_Editora INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    cidade VARCHAR(50),
    site VARCHAR(100),
    ano_fundacao YEAR
);

CREATE TABLE Livro (
    ISBN CHAR(13) PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    ano_publicacao YEAR,
    fk_id_autor INT,
    fk_id_editora INT,

    FOREIGN KEY (fk_id_autor) REFERENCES Autor(id),
    FOREIGN KEY (fk_id_editora) REFERENCES Editora(id_Editora)
);

-- Remover a tabela livro
DROP TABLE Livro;

-- Adicionando FK via alteração
ALTER TABLE Livro 
ADD CONSTRAINT fk_Autor -- nome da restrição
FOREIGN KEY (fk_id_Autor) REFERENCES Autor (id);

-- Adicionando uma nova coluna na tabela Livro
ALTER TABLE Livro
ADD Genero TEXT; 

ALTER TABLE Autor
ADD COLUMN anoNascimento YEAR;

-- Removendo uma coluna 
ALTER TABLE Livro
DROP COLUMN Genero;

-- Modificar tipo de uma coluna
ALTER TABLE Autor
MODIFY COLUMN nacionalidade CHAR(2);

-- Alterando nome de uma coluna
ALTER TABLE Livro
CHANGE id ISBN VARCHAR(20);

-- Inserir
INSERT INTO Autor (id, nome, nacionalidade, anoNascimento) 
VALUES (1, "Machado de Assis", "Brasileiro", 1939);

INSERT INTO Autor
VALUES (2, "George Orwell", "Britânico", 1903); 

INSERT INTO editora(nome, cidade, site, ano_fundacao)
VALUES ("Companhia das Letras", "São Paulo", "www.cdi.br", 1986), 
	   ("Penguin", "Londres", "www.pg.ldn", 1935);
       
INSERT INTO Livro (titulo, ISBN, ano_publicacao, fk_id_autor, fk_id_editora)
VALUES ("Dom Casmurro", "9874689", 1910, 1, 1), ("1984", "7799654", 1949, 2, 2);

-- Update
UPDATE Autor
SET Autor.nacionalidade = "Brasileiro"
WHERE Autor.id = 2;

SELECT * FROM Livro;
SELECT * FROM Autor;

-- Query
SELECT l.titulo, l.ano_publicacao
FROM Livro as l
WHERE l.titulo LIKE "%Dom";

-- Query
SELECT l.titulo AS "Título", 
	   l.ano_publicacao AS "Ano de publicação", 
	   A.nome AS "Autor", 
	   A.nacionalidade AS "Nacionalidade", 
       CONCAT(A.nome, "/", A.nacionalidade) AS "Autor/Nacionalidade",
       e.nome AS "Editora"
FROM Livro AS l
JOIN Autor AS a ON l.fk_id_autor = A.id
JOIN Editora AS e ON l.fk_id_editora = e.id_editora; 
```

# Aula 01 - 31/07/2026

Abordagem inicial da matéria, discutindo o Plano de Ensino.

## Conceitos
O *forward engineering* é o processo tradicional de desenvolvimento, que parte de um conceito abstrato até chegar ao produto final.

- **Fluxo:** Ideia ➔ Requisitos ➔ Arquitetura/Design ➔ Implementação/Fabricação ➔ Produto Final.
- **Objetivo:** Criar algo novo do zero, seguindo especificações planejadas.
- **Exemplo:** Desenvolver um aplicativo móvel do zero, escrevendo as linhas de código com base nas necessidades do cliente.

---

O *reverse engineering* é o caminho inverso: analisa-se um produto, sistema ou código já pronto para desconstruí-lo e entender a sua lógica interna.

- **Fluxo:** Produto Final ➔ Análise/Desmontagem ➔ Compreensão do Design ➔ Documentação/Código-fonte original.
- **Objetivo:** Entender o funcionamento, realizar manutenções, garantir interoperabilidade, encontrar falhas de segurança ou replicar tecnologias.
- **Exemplo:** Analisar um arquivo executável compilado (binário) para entender como ele valida uma licença ou como funciona um protocolo proprietário.

---

🗄️ SGBD (Sistema de Gerenciamento de Banco de Dados)
Software que serve de interface entre o banco de dados, os usuários e as aplicações, garantindo a organização, a segurança e a integridade dos dados.
* **Foco (Evitar Duplicidade):** Uma das suas principais funções, através da **normalização**, é evitar a redundância (duplicidade) de informações, garantindo que os dados sejam armazenados de forma centralizada e consistente.
* **Exemplos:** PostgreSQL, MySQL, SQL Server, Oracle.

---

📐 Modelo Entidade-Relacionamento Conceitual (MER Conceitual)
É a representação abstrata e de alto nível da estrutura de um banco de dados, focando apenas nos aspectos de negócio (o "o quê" será armazenado, e não o "como").
* **O que contém:** Entidades (tabelas/objetos), Atributos (características) e Relacionamentos (como as entidades se conectam, ex: *1 para N*, *N para N*).
* **Independência:** É totalmente independente de tecnologia, SGBD ou linguagem de programação específica.

---

🔑 Chave Primária (Primary Key)
Atributo ou conjunto de atributos que identifica de forma **única** cada registro (linha) dentro de uma tabela.
* **Regras:** Não pode ser nula (`NOT NULL`) e não pode se repetir (`UNIQUE`).
* **Exemplos:** O CPF de uma pessoa, o ID de um usuário ou o código de barras de um produto.

---

🗂️ Atributo Multivalorado
Um atributo que pode armazenar **mais de um valor** para uma mesma entidade.
* **Exemplos:** O atributo `Telefone` de uma pessoa (um cliente pode ter zero, um ou vários telefones) ou `IdiomasFalados` de um funcionário.
* **No modelo relacional tradicional:** Geralmente não é permitido diretamente nas tabelas (gerando a necessidade de criar uma nova tabela para representar esses múltiplos valores).

---

<img width="701" height="229" alt="image" src="https://github.com/user-attachments/assets/51d8e1ed-78b7-4232-be65-6036b103467c" />

```sql
/* Lógico_1: */

CREATE TABLE Funcionario (
    CPF CHAR(14) PRIMARY KEY,
    Nome VARCHAR(100),
    DataNascimento DATE,
    Salario DECIMAL(10,2),
    CEP CHAR(9),
    Rua VARCHAR(100),
    Numero INT,
    Complemento VARCHAR(255)
);
```

---

<img width="1011" height="228" alt="image" src="https://github.com/user-attachments/assets/fea385fa-3a62-40c9-8506-62a523a0151c" />

<img width="625" height="294" alt="image" src="https://github.com/user-attachments/assets/1a46763f-bf3e-40b0-a4fa-80c860ba665e" />

---

<img width="1241" height="485" alt="image" src="https://github.com/user-attachments/assets/4cddf483-75b7-471c-8113-5f7b5fb00cdb" />

<img width="728" height="517" alt="image" src="https://github.com/user-attachments/assets/76e7b77a-a5f0-4ce9-8c7e-88f1d4edb7e4" />
