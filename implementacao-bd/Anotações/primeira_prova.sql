USE FACULDADE_2;

SELECT * FROM ALUNO;
SELECT * FROM DISCIPLINA;
SELECT * FROM HISTORICO_ESCOLAR;
SELECT * FROM PRE_REQUISITO;
SELECT * FROM TURMA;

GO
-- Questão 7
SELECT A.Nome, HE.Nota, T.Semestre, T.Ano, D.Nome_disciplina
FROM ALUNO AS A

INNER JOIN HISTORICO_ESCOLAR AS HE
ON HE.Numero_aluno = A.Numero_aluno

INNER JOIN TURMA AS T
ON HE.Identificacao_turma = T.Identificacao_turma

INNER JOIN DISCIPLINA AS D
ON D.Numero_disciplina = T.Numero_disciplina

WHERE D.Numero_disciplina = 'CC2101'
-- Utilizei o ORDER BY por turma para não ficar misturado o NULL das novas turmas com os alunos que são de outras turmas e já possuem nota
ORDER BY T.Identificacao_turma DESC;
GO

GO
-- Questão 8
SELECT A.Nome, D.Nome_disciplina, T.Semestre, T.Ano, HE.Frequencia
FROM ALUNO AS A

INNER JOIN HISTORICO_ESCOLAR AS HE
ON HE.Numero_aluno = A.Numero_aluno

INNER JOIN TURMA AS T
ON HE.Identificacao_turma = T.Identificacao_turma

INNER JOIN DISCIPLINA AS D
ON D.Numero_disciplina = T.Numero_disciplina

WHERE HE.Frequencia < 75
ORDER BY HE.Frequencia ASC;
GO

GO
-- Questão 9
SELECT T.Numero_disciplina, D.Nome_disciplina, COUNT(DISTINCT A.Numero_aluno) AS QtdAluno, T.Semestre, T.Ano
FROM TURMA AS T

LEFT JOIN DISCIPLINA AS D
ON D.Numero_disciplina = T.Numero_disciplina

LEFT JOIN HISTORICO_ESCOLAR AS HE
ON HE.Identificacao_turma = T.Identificacao_turma

LEFT JOIN ALUNO AS A
ON A.Numero_aluno = HE.Numero_aluno

GROUP BY T.Numero_disciplina, D.Nome_disciplina, T.Semestre, T.Ano;
GO

GO

-- Questão 10
GO
CREATE FUNCTION fn_SituacaoAluno(@Nota FLOAT, @Frequencia FLOAT)
RETURNS VARCHAR(30)
AS
BEGIN
	DECLARE @Situacao VARCHAR(30);

	IF (@Nota IS NULL OR @Frequencia IS NULL)
		BEGIN
			SET @Situacao = 'Em andamento';
			RETURN @Situacao;
		END

	ELSE IF (@Frequencia < 75)
		BEGIN
			SET @Situacao = 'Reprovado por frequência';
			RETURN @Situacao;
		END
	ELSE IF (@Frequencia >= 75 AND @Nota >= 7)
		BEGIN
			SET @Situacao = 'Aprovado';
			RETURN @Situacao;
		END
	ELSE IF (@Frequencia >= 75 AND @Nota BETWEEN 5 AND 6.99)
		BEGIN
			SET @Situacao = 'Em recuperação';
			RETURN @Situacao;
		END
	ELSE IF (@Frequencia >= 75 AND @Nota < 5)
		BEGIN
			SET @Situacao = 'Reprovado por nota';
			RETURN @Situacao;
		END
	ELSE
		SET @Situacao = 'Erro na avaliação';
		RETURN @Situacao;
END
GO

GO
-- Continuação (SELECT) da questão 10
SELECT A.Nome, D.Nome_disciplina, HE.Nota, HE.Frequencia, dbo.fn_SituacaoAluno(HE.Nota, HE.Frequencia) AS Status
FROM ALUNO AS A

LEFT JOIN HISTORICO_ESCOLAR AS HE
ON HE.Numero_aluno = A.Numero_aluno

LEFT JOIN TURMA AS T
ON HE.Identificacao_turma = T.Identificacao_turma

LEFT JOIN DISCIPLINA AS D
ON D.Numero_disciplina = T.Numero_disciplina;
GO

GO
-- Questão 11
CREATE FUNCTION fn_ConverterNotaConceito(@Nota FLOAT)
RETURNS VARCHAR(15)
AS
BEGIN
	DECLARE @NotaConceito VARCHAR(15);
	IF (@Nota BETWEEN 9 AND 10)
	RETURN 'A';

	ELSE IF (@Nota BETWEEN 7 AND 8.99)
	RETURN 'B';

	ELSE IF (@Nota BETWEEN 5 AND 6.99)
	RETURN 'C';

	ELSE IF (@Nota < 5)
	RETURN 'D';

	ELSE IF (@Nota IS NULL)
	RETURN 'Sem Nota';

	ELSE
	RETURN 'Erro ao converter';

	RETURN '';
END
GO

GO
-- Continuação (SELECT) da questão 11
SELECT A.Nome, D.Nome_disciplina, HE.Nota, dbo.fn_ConverterNotaConceito(HE.Nota) AS Nota_Conceito
FROM ALUNO AS A

LEFT JOIN HISTORICO_ESCOLAR AS HE
ON HE.Numero_aluno = A.Numero_aluno

LEFT JOIN TURMA AS T
ON HE.Identificacao_turma = T.Identificacao_turma

LEFT JOIN DISCIPLINA AS D
ON D.Numero_disciplina = T.Numero_disciplina;
GO

GO
-- Questão 12
CREATE PROCEDURE usp_ListarAlunosPorCurso(@SiglaCurso VARCHAR(3))
AS
BEGIN
	SELECT A.Numero_aluno, A.Nome
	FROM ALUNO AS A

	WHERE Curso = @SiglaCurso
	ORDER BY A.Nome;
END
GO

GO
-- Execução do Procedure da questão 12
EXEC usp_ListarAlunosPorCurso CC;
GO

GO
-- Questão 13
CREATE PROCEDURE usp_CadastrarDisciplina (@CodigoDisciplina VARCHAR(10), @NomeDisciplina VARCHAR(50), @Creditos INT, @Departamento VARCHAR(10))
AS
BEGIN
	IF EXISTS (SELECT 1 FROM DISCIPLINA WHERE Numero_Disciplina = @CodigoDisciplina)
	BEGIN
		PRINT 'O Código da disciplina já existe!';
	END

	ELSE IF EXISTS (SELECT 1 FROM DISCIPLINA WHERE Nome_disciplina = @NomeDisciplina)
	BEGIN
		PRINT 'Não é possível cadastrar duas disciplinas com o mesmo nome!';
	END

	ELSE IF (@Creditos = 0)
	BEGIN
		PRINT 'A quantidade de créditos é igual a 0';
	END

	ELSE
		INSERT INTO DISCIPLINA (Numero_disciplina, Nome_disciplina, Creditos, Departamento)
		VALUES (@CodigoDisciplina, @NomeDisciplina, @Creditos, @Departamento);

		IF @@ROWCOUNT > 0
			PRINT 'Cadastrado com sucesso';
		ELSE IF @@ROWCOUNT <= 0
			PRINT 'Erro no cadastro';
END
GO

GO
-- EXEC questão 13

-- Criação
EXEC usp_CadastrarDisciplina '2026IMPBD', 'Banco', 2, 'CC'

-- Teste de validações
EXEC usp_CadastrarDisciplina '2026IMPBDDD', 'Banco de dados I', 2, 'CC'
EXEC usp_CadastrarDisciplina '2026IMPBDDD', 'Banco de dados II', 0, 'CC'
GO

