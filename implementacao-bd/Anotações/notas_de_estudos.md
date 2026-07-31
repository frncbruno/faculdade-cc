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
É a representação abstrata e dealto nível da estrutura de um banco de dados, focando apenas nos aspectos de negócio (o "o quê" será armazenado, e não o "como").
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
