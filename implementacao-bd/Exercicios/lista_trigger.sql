-- 1.
GO
CREATE TRIGGER trg_after_insert_LivrosTitulo
ON Livro
AFTER INSERT
AS
BEGIN
	IF EXISTS (
    SELECT 1
    FROM Livro AS L
    INNER JOIN inserted AS I
        ON I.ISBN <> L.ISBN
    WHERE I.Titulo = L.Titulo
	)
	BEGIN
		PRINT 'Já existe um livro com esse título';
		ROLLBACK TRANSACTION;
	END
END
GO

-- 2.
GO
CREATE TRIGGER trg_after_insert_anoPublicacaoLivroAtual
ON Livro
AFTER INSERT
AS
BEGIN
	DECLARE @anoAtual INT;
	SET @anoAtual = YEAR(GETDATE());

	UPDATE L
	SET L.ano = @anoAtual
	FROM Livro AS L
		INNER JOIN inserted AS I
    ON L.ISBN = I.ISBN
END
GO

-- 3.
GO
CREATE TRIGGER after_delete_excluirLivro
ON Livro
AFTER DELETE
AS
BEGIN
	DELETE LA
	FROM LivroAutor AS LA

	INNER JOIN deleted AS D
		ON LA.fk_livro = D.isbn;
END
GO

-- 4.
GO
CREATE TRIGGER trg_after_insert_totalLivrosCategoria
ON Livro
AFTER INSERT
AS
BEGIN
    DECLARE @total INT;

    SELECT @total = COUNT(*)
    FROM Livro
    WHERE fk_categoria IN (
        SELECT fk_categoria
        FROM inserted
    );

    PRINT 'Total de livros na categoria: ' + CAST(@total AS VARCHAR);
END
GO

-- 5.
GO
CREATE TRIGGER trg_after_delete_categoria
ON CATEGORIA
AFTER DELETE
AS
BEGIN
    IF EXISTS(
        SELECT *
        FROM LIVRO AS L
        INNER JOIN DELETED AS D
        ON L.fk_categoria = D.id
    )
    BEGIN
        RAISERROR('Não é possível excluir uma categoria que possui livros associados!', 16, 1);
        ROLLBACK TRANSACTION;
    END
END;
GO

-- 6.
GO
CREATE TABLE LOG_LIVRO(
    log_id INT IDENTITY (1,1) PRIMARY KEY,
    isbn_livro VARCHAR(50),
    nome_livro VARCHAR(100),
    operacao VARCHAR(20),
    data_hora DATETIME DEFAULT GETDATE()
);
GO

GO
CREATE TRIGGER trg_after_update_logLivro
ON Livro
AFTER UPDATE
AS
BEGIN
    INSERT INTO LOG_LIVRO(isbn_livro, nome_livro, operacao)
    SELECT I.isbn, I.titulo, 'UPDATE'
    FROM inserted AS I;
END
GO

-- 7.
GO
CREATE TRIGGER trg_after_insert_totalLivrosAutor
ON LivroAutor
AFTER INSERT
AS
BEGIN
    SELECT
        A.nome,
        COUNT(LA.fk_livro) AS total_livros
    FROM Autor AS A
    INNER JOIN LivroAutor AS LA
        ON A.id = LA.fk_autor
    WHERE A.id IN (SELECT fk_autor FROM inserted)
    GROUP BY A.id, A.nome;
END
GO

-- 8.
GO
CREATE TRIGGER trg_instead_update_restringirISBN
ON Livro
INSTEAD OF UPDATE
AS
BEGIN
	IF UPDATE(isbn)
	BEGIN
		PRINT 'A atualização do ISBN na tabela Livro foi restringida';
	END
	ELSE
	BEGIN
		UPDATE Livro
		SET titulo = I.titulo,
		ano = I.ano,
		fk_editora = I.fk_editora,
		fk_categoria = I.fk_categoria
		FROM Livro AS L
		INNER JOIN inserted AS I
			ON L.isbn = I.isbn;
	END
END
GO

-- 9.
GO
CREATE TRIGGER trg_after_insert_limite5Autores
ON LivroAutor
AFTER INSERT
AS
BEGIN
	IF EXISTS (
		SELECT I.fk_autor
        FROM inserted AS I
        INNER JOIN LivroAutor AS LA
            ON I.fk_autor = LA.fk_autor
        GROUP BY I.fk_autor
        HAVING COUNT(*) > 5
		)
		BEGIN
		PRINT 'Alcançou o limite máximo de autores';
		ROLLBACK TRANSACTION;
		END
END

-- 11.
GO
-- se nao existir o mesmo autor, remova automaticamente da tabela autor
GO
CREATE TRIGGER trg_after_delete_deletarAutor
ON Livro
AFTER DELETE
AS
BEGIN
    DELETE FROM Autor
    WHERE id IN (
        SELECT fk_autor
        FROM LivroAutor
        WHERE fk_livro IN (
            SELECT isbn
            FROM deleted
        )
    )
    AND id NOT IN (
        SELECT fk_autor
        FROM LivroAutor
    );
END
GO

-- 12.
GO
CREATE TRIGGER trg_after_insert_100livros
ON Livro
AFTER INSERT
AS
BEGIN
    IF EXISTS (SELECT L.fk_categoria 
    FROM Livro AS L
    
    WHERE fk_categoria IN (SELECT fk_categoria FROM inserted)

    GROUP BY L.fk_categoria
    HAVING COUNT(*) > 100)
    BEGIN
        PRINT 'Existem mais de 100 livros nessa categoria!';
        ROLLBACK TRANSACTION;
    END
END
GO

-- 13.
GO
CREATE TRIGGER trg_after_delete_nacionalidadeDesconhecida
ON Autor
INSTEAD OF DELETE
AS
BEGIN
    UPDATE Autor
    SET nacionalidade = 'Desconhecida'
    WHERE id IN (SELECT id FROM deleted);
END
GO

-- 14.
GO
CREATE TRIGGER trg_after_delete_LogExclusaoLivros
ON Livro
AFTER DELETE
AS
BEGIN
    INSERT INTO LOG_LIVRO (isbn_livro, nome_livro, operacao)
    SELECT D.isbn, D.titulo, 'DELETE'
    FROM deleted AS D;
END
GO

-- 15.
GO
CREATE TRIGGER trg_after_update_LivroProibido
ON Livro
AFTER UPDATE
AS
BEGIN
    IF EXISTS (SELECT 1 FROM inserted WHERE titulo LIKE '%Proibido%')
        BEGIN
        ROLLBACK TRAN;
        PRINT 'O título possui a palavra "Proibido"';
        END
END
GO

-- 16.
GO
CREATE TABLE EditoraProibida (
    id_editora INT PRIMARY KEY,
    FOREIGN KEY (id_editora) REFERENCES Editora(id)
);

INSERT INTO EditoraProibida (id_editora)
VALUES (1);

SELECT * FROM EditoraProibida;
GO

GO
CREATE TRIGGER trg_after_insert_EditoraProibida
ON Livro
AFTER INSERT
AS
BEGIN
    IF EXISTS (SELECT 1 FROM EditoraProibida WHERE id_editora IN (SELECT fk_editora FROM inserted))
    BEGIN
        ROLLBACK TRAN;
        PRINT 'Foi adicionado um livro que pertence a editora proibida.';
    END
END
GO

-- 18.
GO
CREATE TRIGGER trg_after_insert_update_delete_LogAtualizacaoLivros
ON Livro
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    IF EXISTS (SELECT 1 FROM inserted)
       AND NOT EXISTS (SELECT 1 FROM deleted)
    BEGIN
        INSERT INTO LOG_LIVRO (isbn_livro, nome_livro, operacao)
        SELECT I.isbn, I.titulo, 'INSERT'
        FROM inserted AS I;
    END

    ELSE IF EXISTS (SELECT 1 FROM inserted)
        AND EXISTS (SELECT 1 FROM deleted)
    BEGIN
        INSERT INTO LOG_LIVRO (isbn_livro, nome_livro, operacao)
        SELECT I.isbn, I.titulo, 'UPDATE'
        FROM inserted AS I;
    END

    ELSE
    BEGIN
        INSERT INTO LOG_LIVRO (isbn_livro, nome_livro, operacao)
        SELECT D.isbn, D.titulo, 'DELETE'
        FROM deleted AS D;
    END
END
GO
