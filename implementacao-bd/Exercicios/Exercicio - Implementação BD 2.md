# Questão 4

```sql
CREATE DATABASE biblioteca;

USE BIBLIOTECA;
```

# Questão 5

- (Feito no BRModelo)
```sql
/* Lógico_1: */

CREATE TABLE Autor (
    Nacionalidade VARCHAR(50),
    Nome VARCHAR(255),
    id INTEGER PRIMARY KEY
);

CREATE TABLE Livro (
    ISBN INTEGER(13) PRIMARY KEY,
    Titulo VARCHAR(255),
    Ano DATE,
    Editora VARCHAR(100),
    Autor VARCHAR(255),
    fk_Categoria_id INTEGER
);

CREATE TABLE Categoria (
    id INTEGER PRIMARY KEY,
    Descricao VARCHAR(255)
);

CREATE TABLE LivroAutor_Relacionamento_1 (
    fk_Autor_id INTEGER,
    fk_Livro_ISBN INTEGER(13)
);
 
ALTER TABLE Livro ADD CONSTRAINT FK_Livro_2
    FOREIGN KEY (fk_Categoria_id)
    REFERENCES Categoria (id)
    ON DELETE CASCADE;
 
ALTER TABLE LivroAutor_Relacionamento_1 ADD CONSTRAINT FK_LivroAutor_Relacionamento_1_1
    FOREIGN KEY (fk_Autor_id)
    REFERENCES Autor (id);
 
ALTER TABLE LivroAutor_Relacionamento_1 ADD CONSTRAINT FK_LivroAutor_Relacionamento_1_2
    FOREIGN KEY (fk_Livro_ISBN)
    REFERENCES Livro (ISBN);
```
# Questão 6

```sql
INSERT INTO Categoria VALUES
(1,'Literatura Juvenil'),
(2,'Ficção Científica'),
(3,'Humor');

INSERT INTO Autor VALUES
(1,'J. K. Rowling','Inglaterra'),
(2,'Clive Staples Lewis','Inglaterra'),
(3,'Affonso Solano','Brasil'),
(4,'Marcos Piangers','Brasil'),
(5,'Ciro Botelho - Tiririca','Brasil'),
(6,'Bianca Mól','Brasil');

INSERT INTO Livro VALUES
(8532511015,'Harry Potter e A Pedra Filosofal',2000,'Rocco',1),
(9788578270698,'As Crônicas de Nárnia',2009,'WMF Martins Fontes',1),
(9788577343348,'O Espadachim de Carvão',2013,'Casa da Palavra',2),
(9788581742458,'O Papai É Pop',2015,'Belas Letras',3),
(9788582302026,'Pior Que Tá Não Fica',2015,'Matrix',3),
(9788577345670,'Garota Desdobrável',2015,'Casa da Palavra',1),
(8532512062,'Harry Potter e o Prisioneiro de Azkaban',2000,'Rocco',1);

INSERT INTO LivroAutor_Relacionamento_1 VALUES
(1,8532511015),
(2,9788578270698),
(3,9788577343348),
(4,9788581742458),
(5,9788582302026),
(6,9788577345670),
(1,8532512062);
```

# Questão 7

```sql
SELECT *
FROM Livro
ORDER BY Titulo;
```

# Questão 8 
```sql
SELECT
    L.ISBN,
    L.Titulo,
    L.Ano,
    L.Editora,
    A.Nome AS Autor,
    A.Nacionalidade,
    C.Descricao AS Categoria
FROM Livro L
JOIN LivroAutor_Relacionamento_1 LA
    ON L.ISBN = LA.fk_Livro_ISBN
JOIN Autor A
    ON A.id = LA.fk_Autor_id
JOIN Categoria C
    ON C.id = L.fk_Categoria_id
ORDER BY A.Nome;
```

# Questão 9 

```sql
SELECT
    L.ISBN,
    L.Titulo,
    L.Ano,
    L.Editora,
    A.Nome AS Autor,
    A.Nacionalidade,
    C.Descricao AS Categoria
FROM Livro L
JOIN LivroAutor_Relacionamento_1 LA
    ON L.ISBN = LA.fk_Livro_ISBN
JOIN Autor A
    ON A.id = LA.fk_Autor_id
JOIN Categoria C
    ON C.id = L.fk_Categoria_id
WHERE C.Descricao = 'Literatura Juvenil'
ORDER BY L.Ano;
```

# Questão 10

```sql
SELECT
    L.ISBN,
    L.Titulo,
    L.Ano,
    L.Editora,
    A.Nome AS Autor,
    A.Nacionalidade,
    C.Descricao AS Categoria
FROM Livro L
JOIN LivroAutor_Relacionamento_1 LA
    ON L.ISBN = LA.fk_Livro_ISBN
JOIN Autor A
    ON A.id = LA.fk_Autor_id
JOIN Categoria C
    ON C.id = L.fk_Categoria_id
WHERE C.Descricao IN ('Humor', 'Ficção Científica')
-- Poderia ser também, de forma mais simples: WHERE C.Descricacao = 'Humor' OR C.Descricao = 'Ficção Científica'
AND L.Ano BETWEEN 2000 AND 2010
ORDER BY L.Ano;
```
