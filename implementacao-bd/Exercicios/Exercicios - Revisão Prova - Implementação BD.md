```sql
SELECT * FROM ALUNO;
SELECT * FROM DISCIPLINA;
SELECT * FROM HISTORICO_ESCOLAR;
SELECT * FROM PRE_REQUISITO;
SELECT * FROM TURMA;

-- Liste nome dos alunos e suas respectivas notas na disciplina "Banco de Dados"
SELECT A.Nome, HE.Nota, D.Nome_disciplina
FROM ALUNO AS A

INNER JOIN HISTORICO_ESCOLAR AS HE
    ON HE.Numero_aluno = A.Numero_aluno

INNER JOIN TURMA AS T
    ON T.Identificacao_turma = HE.Identificacao_turma

INNER JOIN DISCIPLINA AS D
    ON D.Numero_disciplina = T.Numero_disciplina

WHERE D.Numero_disciplina = 'CC2101'
ORDER BY T.Identificacao_turma DESC;

-- Quais são as disciplinas que têm pré-requisitos e quais são seus respectivos pré-requisitos?
SELECT D.Nome_disciplina AS Disciplina, PR_PR.Nome_disciplina AS Pré_Requisitos
FROM PRE_REQUISITO AS PR

INNER JOIN DISCIPLINA AS D
ON D.Numero_disciplina = PR.Numero_disciplina

INNER JOIN DISCIPLINA AS PR_PR
ON PR_PR.Numero_disciplina = PR.Numero_pre_requisito;

-- Liste todas as disciplinas cursadas pelo aluno 'Silva', mostrando o nome da disciplina, o semestre, o ano e a nota final
SELECT A.Nome, D.Nome_disciplina, T.Semestre, T.Ano, HE.Nota
FROM ALUNO AS A

INNER JOIN HISTORICO_ESCOLAR AS HE
    ON HE.Numero_aluno = A.Numero_aluno

INNER JOIN TURMA AS T
    ON T.Identificacao_turma = HE.Identificacao_turma

INNER JOIN DISCIPLINA AS D
    ON D.Numero_disciplina = T.Numero_disciplina

WHERE A.Nome LIKE '%Silva%';

-- Crie uma função (recebe o nome do aluno em questão, e a respectiva disciplina) que verifique se o aluno foi "Aprovado", está "Em Recuperação", ou "Reprovado" com base na nota final. Considere:
-- Nota maior que '70': Aprovado
-- Nota entre '60' e '69': Em Recuperação
-- Nota abaixo de '60': Reprovado

GO
CREATE FUNCTION fn_SituacaoAluno (@Nome VARCHAR(50), @Disciplina VARCHAR(50))
RETURNS VARCHAR(20)
AS
BEGIN
    DECLARE @Nota DECIMAL(4,2);
    DECLARE @Situacao VARCHAR(20);

    SELECT @Nota = HE.Nota
    FROM ALUNO AS A

    INNER JOIN HISTORICO_ESCOLAR AS HE
    ON HE.Numero_aluno = A.Numero_aluno

    INNER JOIN TURMA AS T
    ON T.Identificacao_turma = HE.Identificacao_turma

    INNER JOIN DISCIPLINA AS D
    ON D.Numero_disciplina = T.Numero_disciplina

    WHERE A.Nome = @Nome
    AND D.Nome_disciplina = @Disciplina;
    
    IF @Nota >= 70
        BEGIN
        SET @Situacao = 'Aprovado';
        END
    ELSE IF @Nota >= 60 AND @Nota < 70
        BEGIN
        SET @Situacao = 'Em Recuperação';
        END
    ELSE
        BEGIN
        SET @Situacao = 'Reprovado';
        END

    RETURN @Situacao;
END
GO

-- Crie uma função para verificar se uma turma está "Completamente Lotada", "Quase Cheia" ou "Com Vagas". Depois faça uma consulta que mostre a identificação da turma,
-- nome da disciplina, número de alunos e o status de lotação, contendo todas as turmas. Para determinação do status de lotação, utilize a seguinte regra:
-- 5 ou mais alunos: Completamente Lotada
-- 3 a 4 alunos: Quase Cheia
-- Menos de 3 alunos: Com Vagas

GO
CREATE FUNCTION fn_StatusLotacao (@QtdAlunos INT)
RETURNS VARCHAR(30)
AS
BEGIN
    DECLARE @Status VARCHAR(30);

    IF @QtdAlunos >= 5
        SET @Status = 'Completamente Lotada';

    ELSE IF @QtdAlunos >= 3
        SET @Status = 'Quase Cheia';

    ELSE
        SET @Status = 'Com Vagas';

    RETURN @Status;
END
GO

SELECT
    T.Identificacao_turma,
    D.Nome_disciplina,
    COUNT(DISTINCT HE.Numero_aluno) AS Numero_Alunos,
    dbo.fn_StatusLotacao(COUNT(DISTINCT HE.Numero_aluno)) AS Status_Lotacao

FROM TURMA AS T

INNER JOIN DISCIPLINA AS D
    ON D.Numero_disciplina = T.Numero_disciplina

LEFT JOIN HISTORICO_ESCOLAR AS HE
    ON HE.Identificacao_turma = T.Identificacao_turma

GROUP BY
    T.Identificacao_turma,
    D.Nome_disciplina;

-- Crie um procedimento armazenado chamado usp_CalcularIdadeAluno que receba o número do aluno como parâmetro e exiba a idade correta do aluno.
SELECT * FROM ALUNO;

GO
CREATE OR ALTER PROCEDURE usp_CalcularIdadeAluno (@NumeroAluno INT)
AS
BEGIN
    DECLARE @dataNascimento DATE;
    DECLARE @idade INT;

    SELECT @dataNascimento = Data_nascimento
    FROM ALUNO
    WHERE Numero_aluno = @NumeroAluno;

    SET @idade = DATEDIFF(YEAR, @dataNascimento, GETDATE());

    IF (DATEADD(YEAR, @idade, @dataNascimento) > GETDATE())
    BEGIN
        SET @idade = @idade - 1;
    END

    PRINT CAST(@idade AS VARCHAR(3));
END
GO

EXEC usp_CalcularIdadeAluno 1;

-- Crie uma procedure chamada usp_AtualizarNota que receba o número do aluno, a identificação da turma e a nova nota como parâmetro e atualize a nota do aluno
-- no histórico escolar, e exiba uma mensagem de sucesso ou falha

GO
CREATE PROCEDURE usp_AtualizarNota (@numeroAluno INT, @identificacaoTurma INT, @novaNota DECIMAL(4,2))
AS
BEGIN
        UPDATE HISTORICO_ESCOLAR
        SET Nota = @novaNota
        WHERE Numero_aluno = @numeroAluno
        AND Identificacao_turma = @identificacaoTurma;

        IF @@ROWCOUNT > 0
            PRINT 'Nota atualizada com sucesso!';
        ELSE 
            PRINT 'Falha: aluno ou turma não encontrado!';
END
GO

-- Crie uma função que converta as notas 'A', 'B', 'C', 'F', para os valores 10, 9, 8 e 0, respectivamente, depois liste o nome de todos alunos, as disciplinas que ele cursou
-- e suas notas no formato numérico

GO
CREATE FUNCTION fn_converterNota(@nota VARCHAR(1))
RETURNS INT
AS
BEGIN
    DECLARE @resultado INT;

    IF @nota = 'A'
        SET @resultado = 10;
    ELSE IF @nota = 'B'
        SET @resultado = 9;
    ELSE IF @nota = 'C'
        SET @resultado = 8;
    ELSE
        SET @resultado = 0;

    RETURN @resultado;
END
GO

SELECT A.Nome, dbo.fn_converterNota(HE.Nota), D.Nome_disciplina
FROM ALUNO AS A

INNER JOIN HISTORICO_ESCOLAR AS HE
    ON HE.Numero_aluno = A.Numero_aluno

INNER JOIN TURMA AS T
    ON T.Identificacao_turma = HE.Identificacao_turma

INNER JOIN DISCIPLINA AS D
    ON D.Numero_disciplina = T.Numero_disciplina;
```
