GO
-- 1.
	-- A transação serve para para garantir a integridade dos dados, reverter alterações em caso de erro e controlar alterações de forma simultânea.
	-- O ACID são as quatro propriedades que servem para garantir que o banco se mantenha estável e com integridades. 
	-- Atomicidade: garante que deve ser concluída ou totalmente desfeita se uma parte falhar, o resto também.
	-- Isolamento: executado de forma isolada, sem que as operações de uma transação afetem as outras.
	-- Consistência: leva uma transação de um estádo válido para outro estado válido, respeitando todas as regras.
	-- Durabilidade: uma vez que a transação é confirmada, ela permanecerá no banco.
GO

GO
-- 2.
GO
	BEGIN TRAN;
		DECLARE @linhasAlteradas INT = 0;

		UPDATE Procedimento
		SET PrecoPadrao = 105.00
		WHERE ProcedimentoID = 'HEMO';

		SET @linhasAlteradas = @@ROWCOUNT;

		IF @linhasAlteradas = 1
			BEGIN
				PRINT 'Linhas alteradas: '+CAST(@linhasAlteradas AS VARCHAR(2));
				PRINT 'Procedimento alterado com sucesso.';
				COMMIT TRAN;
			END
			ELSE
			BEGIN
				PRINT 'Linhas alteradas: '+CAST(@linhasAlteradas AS VARCHAR(3));
				PRINT 'Nenhuma ou mais de uma linha alterada. Erro na alteração do procedimento.';
				ROLLBACK TRAN;
			END
GO

GO
-- 4.
GO
CREATE OR ALTER TRIGGER trg_Consulta_Ativos
ON Consulta
AFTER INSERT
AS
BEGIN
	IF EXISTS (
		SELECT 1 FROM Animal WHERE Ativo IN (SELECT Ativo FROM inserted WHERE Ativo = 1)
	) AND EXISTS (
		SELECT 1 FROM Veterinario WHERE Ativo IN (SELECT Ativo FROM inserted WHERE Ativo = 1)
	)
		BEGIN
			COMMIT TRAN;
			PRINT 'Animal ou veterinário está ativo no momento. Linha inserida.';
		END
	
	ELSE IF NOT EXISTS (
		SELECT 1 FROM Animal WHERE Ativo IN (SELECT Ativo FROM inserted WHERE Ativo = 1)
	) OR NOT EXISTS (
		SELECT 1 FROM Veterinario WHERE Ativo IN (SELECT Ativo FROM inserted WHERE Ativo = 1)
	)
		BEGIN
			PRINT 'Animal ou veterinário está inativo no momento.';
			ROLLBACK TRAN;
		END
END

	
	INSERT INTO Consulta(AnimalID, VeterinarioID, DataConsulta, Motivo)
	VALUES (1, 2, '2026-11-20 09:00:00', 'Consulta de rotina');

GO

GO
-- 3.
	-- É um tipo de stored procedure executado pós DML (INSERT, UPDATE, DELETE). 
	-- Ele possui dois tipos: o trigger after, que é executado após o oitavo fluxo de transação, um antes do commit, que é o décimo.
	-- O outro tipo é o instead of, no qual ele é disparado para "substituir" o DML, ou seja, quando é realizado um INSERT/DELETE/UPDATE, 
	-- ele executa e substitui antes do comando fazer as alterações.
	-- Ele pode ser vinculado aos comandos de DELETE, INSERT e UPDATE.
GO

-- 5.
CREATE TRIGGER trg_ConsultaProcedimento_ValorTotal
ON ConsultaProcedimento
AFTER INSERT
AS
BEGIN
	DECLARE @QuantidadeCP DECIMAL(10,2), @PrecoUnitarioCP DECIMAL (10,2);

	SELECT @QuantidadeCP = Quantidade, @PrecoUnitarioCP = PrecoUnitario
	FROM ConsultaProcedimento
	WHERE ConsultaID IN (SELECT ConsultaID FROM inserted);

	UPDATE Consulta
	SET ValorTotal = (@QuantidadeCP * @PrecoUnitarioCP)
	WHERE ConsultaID IN (SELECT ConsultaID FROM inserted);
END

-- 6. 
GO
CREATE TABLE HistoricoPrecoProcedimento(
	HistoricoID INT IDENTITY (1,1) PRIMARY KEY,
	ProcedimentoID VARCHAR(10) NOT NULL,
	PrecoAnterior DECIMAL(10,2) NOT NULL,
	PrecoNovo DECIMAL(10,2) NOT NULL,
	DataAlteracao DATETIME2(0) NOT NULL DEFAULT SYSDATETIME()
);
GO

GO
CREATE TRIGGER trg_Procedimento_Preco
ON Procedimento
AFTER UPDATE
AS
BEGIN
	INSERT INTO HistoricoPrecoProcedimento (ProcedimentoID, PrecoAnterior, PrecoNovo)
	SELECT I.ProcedimentoID, D.PrecoPadrao, I.PrecoPadrao FROM inserted AS I INNER JOIN deleted AS D ON D.ProcedimentoID = I.ProcedimentoID;
END

-- 7.
GO
	CREATE TRIGGER trg_Animal_Desativar
	ON Animal
	INSTEAD OF DELETE
	AS
	BEGIN
		IF EXISTS (SELECT 1 FROM Animal WHERE Ativo IN (SELECT Ativo FROM deleted))
			BEGIN
				UPDATE Animal
				SET Ativo = 0
				WHERE AnimalID IN (SELECT AnimalID FROM deleted);
			END
	END
GO

GO
-- 8.
	-- Integridade de domínio: Valores inseridos obedecem os valores da coluna, exemplo: Int precisa ser int, varchar precisa ser varchar
	-- Integridade referencial: faz parte do CASCADE, no qual sua função é utilizada para quando for excluir uma coluna principal, 
	-- associada a outras, irá avisar que não pode porque são dependentes e possuem outras atreladas a essa principal, não sendo permitida a exclusão, por exemplo.
	
	-- Declarativas: parametrizadas durante a criação das estruturas das tabelas, fazendo uso de PK (chave primária), não nulos, tipos e domínios de dados e aplicando regras de integridade referencial.
	-- Procedural: recursos como triggers, stored procedures, assertion, usada com regra de negócio.
GO

GO
-- 9.A
	ALTER TABLE ConsultaProcedimento
		ADD CONSTRAINT FK_ConsultaProcedimento_Procedimento FOREIGN KEY (ConsultaProcedimentoID) REFERENCES Procedimento (ProcedimentoID)
		ON UPDATE CASCADE;

-- 9.B
	UPDATE Procedimento
	SET ProcedimentoID = 'VACINA'
	WHERE ProcedimentoID = 'VAC';

	-- Com isso, o CASCADE permitirá que ao atualizar o ProcedimentoID na tabela Procedimento, também seja atualizado automaticamente na tabela de ConsultaProcedimento.
GO

GO
-- 10.
	-- Uma view é um select salvo no formato de uma tabela, ela possui suas vantagens por conta da velocidade de acesso às informações, mascara complexidade e pode simplificar o gerenciamento de permissão de usuários.
	-- É uma tabela virtual de uma consulta SQL.
GO

GO
-- 11.
GO
CREATE VIEW vw_RelatorioConsultas
AS
	SELECT  C.ConsultaID, 
			C.DataConsulta AS DataConsulta, 
			T.Nome AS NomeTutor, 
			A.Nome AS NomeAnimal, 
			V.Nome AS NomeVeterinario, 
			C.Situacao AS SituacaoConsulta, 
			CASE WHEN P.Descricao IS NOT NULL THEN P.Descricao WHEN P.Descricao IS NULL THEN 'Sem procedimento' END AS DescricaoProcedimento,
			CASE WHEN CP.Quantidade IS NOT NULL THEN CP.Quantidade WHEN CP.Quantidade IS NULL THEN NULL END AS QuantidadeProcedimento,
			CASE WHEN CP.PrecoUnitario IS NOT NULL THEN CP.PrecoUnitario WHEN CP.PrecoUnitario IS NULL THEN NULL END AS QuantidadePrecoUnitario,
			CASE WHEN CP.Quantidade > 0 THEN (CP.Quantidade * CP.PrecoUnitario) WHEN CP.Quantidade <= 0 THEN 0 END AS SubTotalItem
	FROM CONSULTA AS C

	INNER JOIN Animal AS A
	ON A.AnimalID = C.AnimalID

	INNER JOIN Tutor AS T
	ON T.TutorID = A.TutorID

	INNER JOIN Veterinario AS V
	ON V.VeterinarioID = C.ConsultaID

	INNER JOIN ConsultaProcedimento AS CP
	ON C.ConsultaID = CP.ConsultaID

	INNER JOIN Procedimento AS P
	ON P.ProcedimentoID = CP.ProcedimentoID;
GO

-- 12.
GO
GRANT SELECT ON [dbo].[vw_RelatorioConsultas] TO usuarioRecepcao;
GRANT SELECT ON [dbo].[Consulta] TO usuarioRecepcao;
DENY UPDATE, DELETE ON [dbo].[Consulta] to usuarioRecepcao;
GO

-- 13.
GO
CREATE VIEW vw_AnimalOwners
AS
SELECT A.Nome AS NomeAnimal, A.Especie, T.Nome AS NomeTutor
FROM Animal AS A

INNER JOIN Tutor AS T
ON T.TutorID = A.TutorID;
GO
