### CÓDIGOS SQL - TRIGGER DE AUDITORIA

FAZ A LIST NO WORD DESGRACADO PARA COLOCAR OS CODIGOS

<img width="637" height="514" alt="image" src="https://github.com/user-attachments/assets/c4d5f009-4101-4cd3-86e2-00c5c2d80b17" />


```sql
-- CRIAÇÃO DA TABELA DE LOG
CREATE TABLE log_item_acervo (
    id_log INT NOT NULL AUTO_INCREMENT,
    id_item_acervo INT NOT NULL,
    operacao VARCHAR(10) NOT NULL,
    data_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dados_anteriores TEXT,
    dados_novos TEXT,

    PRIMARY KEY (id_log)
);

-- TRIGGER LOG INSERT
DELIMITER //

CREATE TRIGGER trg_item_acervo_ai
AFTER INSERT ON item_acervo
FOR EACH ROW
BEGIN

    INSERT INTO log_item_acervo (
        id_item_acervo,
        operacao,
        dados_novos
    )
    VALUES (
        NEW.id_item_acervo,
        'INSERT',
        CONCAT_WS(' | ',
            CONCAT('titulo=', NEW.titulo),
            CONCAT('subtitulo=', IFNULL(NEW.subtitulo, 'NULL')),
            CONCAT('status=', NEW.status),
            CONCAT('idioma=', NEW.idioma),
            CONCAT('tipo=', NEW.tipo),
            CONCAT('descricao=', IFNULL(NEW.descricao, 'NULL')),
            CONCAT('notas=', IFNULL(NEW.notas, 'NULL')),
            CONCAT('visibilidade_notas=', IF(NEW.visibilidade_notas = b'1', '1', '0')),
            CONCAT('numero_paginas=', NEW.numero_paginas),
            CONCAT('localizacao_acervo=', NEW.localizacao_acervo),
            CONCAT('id_editora=', IFNULL(NEW.id_editora, 'NULL'))
        )
    );

END //

DELIMITER ;

-- TRIGGER LOG UPDATE
DELIMITER //

CREATE TRIGGER trg_item_acervo_au
AFTER UPDATE ON item_acervo
FOR EACH ROW
BEGIN

    INSERT INTO log_item_acervo (
        id_item_acervo,
        operacao,
        dados_anteriores,
        dados_novos
    )
    VALUES (
        OLD.id_item_acervo,
        'UPDATE',

        CONCAT_WS(' | ',
            CONCAT('titulo=', OLD.titulo),
            CONCAT('status=', OLD.status),
            CONCAT('idioma=', OLD.idioma),
            CONCAT('tipo=', OLD.tipo),
            CONCAT('numero_paginas=', OLD.numero_paginas),
            CONCAT('localizacao_acervo=', OLD.localizacao_acervo)
        ),

        CONCAT_WS(' | ',
            CONCAT('titulo=', NEW.titulo),
            CONCAT('status=', NEW.status),
            CONCAT('idioma=', NEW.idioma),
            CONCAT('tipo=', NEW.tipo),
            CONCAT('numero_paginas=', NEW.numero_paginas),
            CONCAT('localizacao_acervo=', NEW.localizacao_acervo)
        )
    );

END //

-- TRIGGER LOG DELETE
DELIMITER //

CREATE TRIGGER trg_item_acervo_ad
AFTER DELETE ON item_acervo
FOR EACH ROW
BEGIN

    INSERT INTO log_item_acervo (
        id_item_acervo,
        operacao,
        dados_anteriores,
        dados_novos
    )
    VALUES (
        OLD.id_item_acervo,
        'DELETE',

        CONCAT_WS(' | ',
            CONCAT('titulo=', OLD.titulo),
            CONCAT('subtitulo=', IFNULL(OLD.subtitulo, 'NULL')),
            CONCAT('status=', OLD.status),
            CONCAT('idioma=', OLD.idioma),
            CONCAT('tipo=', OLD.tipo),
            CONCAT('descricao=', IFNULL(OLD.descricao, 'NULL')),
            CONCAT('notas=', IFNULL(OLD.notas, 'NULL')),
            CONCAT('numero_paginas=', OLD.numero_paginas),
            CONCAT('localizacao_acervo=', OLD.localizacao_acervo)
        ),

        NULL
    );

END //

DELIMITER ;
```

### PRINT DO SQL

<img width="1274" height="102" alt="image" src="https://github.com/user-attachments/assets/78eaade2-46f8-41f9-aef9-1fa2672544fb" />

--- 

### FEATURE DE BACKUP

```sql
-- TABELA DE LOG BACKUP
CREATE TABLE log_backup (
    id_backup INT NOT NULL AUTO_INCREMENT,
    data_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    quantidade_registros INT NOT NULL,
    status VARCHAR(20) NOT NULL,

    PRIMARY KEY (id_backup)
);

-- CRIAÇÃO DA TABELA BACKUP
CREATE TABLE backup_item_acervo (
    id_backup INT NOT NULL,
    id_item_acervo INT NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    subtitulo VARCHAR(255),
    status VARCHAR(255) NOT NULL,
    idioma VARCHAR(255) NOT NULL,
    tipo VARCHAR(255) NOT NULL,
    descricao VARCHAR(2000),
    notas VARCHAR(2000),
    visibilidade_notas BIT(1) NOT NULL,
    numero_paginas INT NOT NULL,
    capa LONGBLOB,
    localizacao_acervo VARCHAR(255) NOT NULL,
    id_editora INT,

    PRIMARY KEY (id_backup, id_item_acervo),

    FOREIGN KEY (id_backup)
        REFERENCES log_backup(id_backup)
) ENGINE=InnoDB;


-- STORED PROCEDURE DE REALIZAR O BACKUP
DELIMITER //

CREATE PROCEDURE sp_realizar_backup()
BEGIN

    -- Declaracao das variaveis
    DECLARE v_id_backup INT;
    DECLARE v_quantidade INT;

    -- Inicia a transacao
    START TRANSACTION;

    -- Conta os registros da tabela original
    SELECT COUNT(*)
    INTO v_quantidade
    FROM item_acervo;

    -- Registra a execucao do backup
    INSERT INTO log_backup (
        quantidade_registros,
        status
    )
    VALUES (
        v_quantidade,
        'SUCESSO'
    );

    -- Recupera o ID do backup criado
    SET v_id_backup = LAST_INSERT_ID();

    -- Copia os registros para a tabela de backup
    INSERT INTO backup_item_acervo (
        id_backup,
        id_item_acervo,
        titulo,
        subtitulo,
        status,
        idioma,
        tipo,
        descricao,
        notas,
        visibilidade_notas,
        numero_paginas,
        capa,
        localizacao_acervo,
        id_editora
    )
    SELECT
        v_id_backup,
        id_item_acervo,
        titulo,
        subtitulo,
        status,
        idioma,
        tipo,
        descricao,
        notas,
        visibilidade_notas,
        numero_paginas,
        capa,
        localizacao_acervo,
        id_editora
    FROM item_acervo;

    -- Confirma a transacao
    COMMIT;

END //

DELIMITER ;

CALL sp_realizar_backup();
```

### PRINT DO SQL
- TABELA LOG BACKUP
<img width="491" height="101" alt="image" src="https://github.com/user-attachments/assets/68648cc1-159e-4540-a3dc-a73716488ed9" />

- TABELA BACKUP_ITEM_ACERVO
<img width="1467" height="157" alt="image" src="https://github.com/user-attachments/assets/6847c963-cfee-437a-bfc8-031b428c891a" />


