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
	ELSE
END
GO
