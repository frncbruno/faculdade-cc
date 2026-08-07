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
