# 📝 Cola — Cadastro de Alunos em Java Swing

Guia rápido para montar o projeto de cadastro de alunos com **ArrayList + JTable + arquivo TXT** no NetBeans.

---

# 1. Estrutura do projeto

Criar duas classes:

```text
projeto
├── Aluno.java
└── Cadastro.java
```

- `Aluno.java` → representa um aluno.
- `Cadastro.java` → tela e lógica do sistema.

---

# 2. Classe `Aluno`

## Atributos

```java
private String nome;
private String dataNascimento;
private String sexo;
private int matricula;
private String curso;
private String cpf;
private String rua;
private String numero;
private String bairro;
private String cidade;
private String cep;
private String estado;
private String telefone;
```

## Construtor

A ordem precisa ser a mesma dos atributos:

```java
public Aluno(String nome, String dataNascimento, String sexo, int matricula,
        String curso, String cpf, String rua, String numero,
        String bairro, String cidade, String cep, String estado,
        String telefone) {

    this.nome = nome;
    this.dataNascimento = dataNascimento;
    this.sexo = sexo;
    this.matricula = matricula;
    this.curso = curso;
    this.cpf = cpf;
    this.rua = rua;
    this.numero = numero;
    this.bairro = bairro;
    this.cidade = cidade;
    this.cep = cep;
    this.estado = estado;
    this.telefone = telefone;
}
```

## Getters e setters

No NetBeans:

**Botão direito na classe → Insert Code → Getter and Setter**

Gerar para todos os atributos.

Exemplos:

```java
public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public String getDataNascimento() {
    return dataNascimento;
}

public void setDataNascimento(String dataNascimento) {
    this.dataNascimento = dataNascimento;
}

public int getMatricula() {
    return matricula;
}

public void setMatricula(int matricula) {
    this.matricula = matricula;
}
```

## `toString()` para salvar no TXT

Cada aluno será salvo em **uma linha**, usando `;` como separador.

```java
@Override
public String toString() {
    return nome + ";" + dataNascimento + ";" + sexo + ";" + matricula + ";"
            + curso + ";" + cpf + ";" + rua + ";" + numero + ";"
            + bairro + ";" + cidade + ";" + cep + ";" + estado + ";"
            + telefone;
}
```

Resultado no arquivo:

```text
Bruno;28072005;Masculino;21391293;CC;054729319;Rua X;151;Centro;Santa Maria;97010160;RS;55999999999
```

---

# 3. `Cadastro.java` — imports

Para usar lista:

```java
import java.util.ArrayList;
```

Para JTable:

```java
import javax.swing.table.DefaultTableModel;
```

Para arquivo:

```java
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
```

Para mensagens:

```java
import javax.swing.JOptionPane;
```

---

# 4. Criar a ArrayList

Dentro da classe `Cadastro`:

```java
private ArrayList<Aluno> alunos = new ArrayList<>();
```

## O que significa?

```text
ArrayList<Aluno>
       ↓
lista que só aceita objetos Aluno
```

Adicionar:

```java
alunos.add(aluno);
```

Pegar pela posição:

```java
alunos.get(linha);
```

Remover pela posição:

```java
alunos.remove(linha);
```

---

# 5. Criar a JTable

No NetBeans GUI Builder:

1. Arrastar `JTable`.
2. Ela ficará dentro de um `JScrollPane`.
3. Nomear a tabela como:

```text
tabelaAlunos
```

Configurar as colunas:

```text
Nome completo
Data nascimento
Sexo
Matrícula
Curso
CPF
Rua
Número
Bairro
Cidade
CEP
Estado
Telefone
```

Deixar as colunas **não editáveis**.

---

# 6. Atualizar a JTable

Importante:

```java
import javax.swing.table.DefaultTableModel;
```

Criar o método:

```java
private void atualizarTabela() {

    DefaultTableModel modelo =
            (DefaultTableModel) tabelaAlunos.getModel();

    modelo.setRowCount(0);

    for (Aluno aluno : alunos) {

        modelo.addRow(new Object[]{
            aluno.getNome(),
            aluno.getDataNascimento(),
            aluno.getSexo(),
            aluno.getMatricula(),
            aluno.getCurso(),
            aluno.getCpf(),
            aluno.getRua(),
            aluno.getNumero(),
            aluno.getBairro(),
            aluno.getCidade(),
            aluno.getCep(),
            aluno.getEstado(),
            aluno.getTelefone()
        });
    }
}
```

## Ideia

```text
ArrayList
   ↓
for
   ↓
cada Aluno
   ↓
addRow()
   ↓
JTable
```

---

# 7. Cadastrar aluno

No botão **Cadastrar**:

## 7.1 Pegar os dados dos campos

```java
String nome = txtNome.getText();
String dataNascimento = txtDataNascimento.getText();
String sexo = (String) cbSexo.getSelectedItem();

int matricula = Integer.parseInt(txtMatricula.getText());

String curso = txtCurso.getText();
String cpf = txtCpf.getText();
String rua = txtRua.getText();
String numero = txtNumero.getText();
String bairro = txtBairro.getText();
String cidade = txtCidade.getText();
String cep = txtCep.getText();
String estado = (String) cbEstado.getSelectedItem();
String telefone = txtTelefone.getText();
```

### Atenção

`JTextField`:

```java
txtNome.getText()
```

`JComboBox`:

```java
(String) cbSexo.getSelectedItem()
```

`String → int`:

```java
Integer.parseInt(txtMatricula.getText())
```

---

# 8. Criar o objeto Aluno

Depois de pegar os dados:

```java
Aluno aluno = new Aluno(
        nome,
        dataNascimento,
        sexo,
        matricula,
        curso,
        cpf,
        rua,
        numero,
        bairro,
        cidade,
        cep,
        estado,
        telefone
);
```

---

# 9. Adicionar na lista

```java
alunos.add(aluno);
```

Depois:

```java
atualizarTabela();
```

E:

```java
salvarArquivo();
```

Fluxo:

```text
Formulário
   ↓
new Aluno(...)
   ↓
alunos.add(aluno)
   ↓
atualizarTabela()
   ↓
salvarArquivo()
```

---

# 10. Salvar no arquivo TXT

Imports:

```java
import java.io.FileWriter;
import java.io.IOException;
```

Método:

```java
private void salvarArquivo() {
    try {

        FileWriter arquivo = new FileWriter("alunos.txt");

        for (Aluno aluno : alunos) {
            arquivo.write(aluno.toString() + "\n");
        }

        arquivo.close();

    } catch (IOException e) {

    }
}
```

## O que acontece?

```text
ArrayList
   ↓
for
   ↓
aluno.toString()
   ↓
alunos.txt
```

`FileWriter("alunos.txt")` cria o arquivo se ele não existir.

---

# 11. Carregar o arquivo

Imports:

```java
import java.io.FileReader;
import java.io.BufferedReader;
```

Método:

```java
private void carregarArquivo() {
    try {

        FileReader arquivo = new FileReader("alunos.txt");

        BufferedReader leitor = new BufferedReader(arquivo);

        String linha;

        while ((linha = leitor.readLine()) != null) {

            String[] dados = linha.split(";");

            Aluno aluno = new Aluno(
                    dados[0],
                    dados[1],
                    dados[2],
                    Integer.parseInt(dados[3]),
                    dados[4],
                    dados[5],
                    dados[6],
                    dados[7],
                    dados[8],
                    dados[9],
                    dados[10],
                    dados[11],
                    dados[12]
            );

            alunos.add(aluno);
        }

        atualizarTabela();

    } catch (IOException e) {

    }
}
```

---

# 12. Entender `split(";")`

Se o arquivo tiver:

```text
Bruno;28072005;Masculino;21391293;CC
```

Então:

```java
String[] dados = linha.split(";");
```

vira:

```text
dados[0] → Bruno
dados[1] → 28072005
dados[2] → Masculino
dados[3] → 21391293
dados[4] → CC
```

Por isso o construtor usa:

```java
dados[0]
dados[1]
dados[2]
Integer.parseInt(dados[3])
dados[4]
...
```

---

# 13. Carregar automaticamente ao abrir

No construtor de `Cadastro`:

```java
public Cadastro() {
    initComponents();
    carregarArquivo();
}
```

Fluxo:

```text
Abre programa
    ↓
carregarArquivo()
    ↓
lê alunos.txt
    ↓
cria objetos Aluno
    ↓
alunos.add(aluno)
    ↓
atualizarTabela()
    ↓
JTable
```

---

# 14. EXCLUIR aluno

Criar botão:

```text
Excluir
```

No evento:

```java
private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {

    int linha = tabelaAlunos.getSelectedRow();

    if (linha != -1) {

        alunos.remove(linha);

        atualizarTabela();

        salvarArquivo();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Selecione um aluno para excluir!"
        );
    }
}
```

## Lógica

```text
getSelectedRow()
       ↓
descobre linha
       ↓
linha != -1?
    ↙       ↘
  SIM       NÃO
   ↓          ↓
remove      aviso
   ↓
atualiza tabela
   ↓
salva TXT
```

### Importante

Se nada estiver selecionado:

```java
tabelaAlunos.getSelectedRow()
```

retorna:

```text
-1
```

---

# 15. EDITAR aluno

Criar botão:

```text
Editar
```

Primeiro descobrir a linha:

```java
int linha = tabelaAlunos.getSelectedRow();
```

Validar:

```java
if (linha != -1) {
```

Pegar o objeto:

```java
Aluno aluno = alunos.get(linha);
```

Depois preencher os campos.

## JTextField

```java
txtNome.setText(aluno.getNome());
txtDataNascimento.setText(aluno.getDataNascimento());
txtCurso.setText(aluno.getCurso());
txtCpf.setText(aluno.getCpf());
txtRua.setText(aluno.getRua());
txtNumero.setText(aluno.getNumero());
txtBairro.setText(aluno.getBairro());
txtCidade.setText(aluno.getCidade());
txtCep.setText(aluno.getCep());
txtTelefone.setText(aluno.getTelefone());
```

## ComboBox

```java
cbSexo.setSelectedItem(aluno.getSexo());
cbEstado.setSelectedItem(aluno.getEstado());
```

## Matrícula

Como matrícula é `int` e o JTextField recebe `String`:

```java
txtMatricula.setText(String.valueOf(aluno.getMatricula()));
```

## Código completo do botão Editar

```java
private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {

    int linha = tabelaAlunos.getSelectedRow();

    if (linha != -1) {

        Aluno aluno = alunos.get(linha);

        txtNome.setText(aluno.getNome());
        txtDataNascimento.setText(aluno.getDataNascimento());
        cbSexo.setSelectedItem(aluno.getSexo());
        txtMatricula.setText(String.valueOf(aluno.getMatricula()));
        txtCurso.setText(aluno.getCurso());
        txtCpf.setText(aluno.getCpf());
        txtRua.setText(aluno.getRua());
        txtNumero.setText(aluno.getNumero());
        txtBairro.setText(aluno.getBairro());
        txtCidade.setText(aluno.getCidade());
        txtCep.setText(aluno.getCep());
        cbEstado.setSelectedItem(aluno.getEstado());
        txtTelefone.setText(aluno.getTelefone());

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Selecione um aluno para editar!"
        );
    }
}
```

---

# 16. SALVAR ALTERAÇÕES

Criar botão:

```text
Salvar Alterações
```

Primeiro:

```java
int linha = tabelaAlunos.getSelectedRow();
```

Depois:

```java
if (linha != -1) {
    Aluno aluno = alunos.get(linha);
```

Agora usar os setters.

## JTextField

```java
aluno.setNome(txtNome.getText());
aluno.setDataNascimento(txtDataNascimento.getText());
aluno.setCurso(txtCurso.getText());
aluno.setCpf(txtCpf.getText());
aluno.setRua(txtRua.getText());
aluno.setNumero(txtNumero.getText());
aluno.setBairro(txtBairro.getText());
aluno.setCidade(txtCidade.getText());
aluno.setCep(txtCep.getText());
aluno.setTelefone(txtTelefone.getText());
```

## ComboBox

```java
aluno.setSexo((String) cbSexo.getSelectedItem());
aluno.setEstado((String) cbEstado.getSelectedItem());
```

## Matrícula

Como o campo é String e o atributo é int:

```java
aluno.setMatricula(Integer.parseInt(txtMatricula.getText()));
```

## Atualizar tabela e arquivo

No final:

```java
atualizarTabela();
salvarArquivo();
```

## Código completo

```java
private void btnSalvarAlteracoesActionPerformed(java.awt.event.ActionEvent evt) {

    int linha = tabelaAlunos.getSelectedRow();

    if (linha != -1) {

        Aluno aluno = alunos.get(linha);

        aluno.setNome(txtNome.getText());
        aluno.setDataNascimento(txtDataNascimento.getText());
        aluno.setSexo((String) cbSexo.getSelectedItem());
        aluno.setMatricula(Integer.parseInt(txtMatricula.getText()));
        aluno.setCurso(txtCurso.getText());
        aluno.setCpf(txtCpf.getText());
        aluno.setRua(txtRua.getText());
        aluno.setNumero(txtNumero.getText());
        aluno.setBairro(txtBairro.getText());
        aluno.setCidade(txtCidade.getText());
        aluno.setCep(txtCep.getText());
        aluno.setEstado((String) cbEstado.getSelectedItem());
        aluno.setTelefone(txtTelefone.getText());

        atualizarTabela();
        salvarArquivo();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Selecione um aluno para editar!"
        );
    }
}
```

---

# 17. GETTER x SETTER — decorar

Essa é uma das partes mais importantes.

## GETTER

**Pega informação do objeto.**

```java
aluno.getNome()
```

Exemplo:

```java
txtNome.setText(aluno.getNome());
```

Significa:

```text
Aluno → tela
```

---

## SETTER

**Altera informação do objeto.**

```java
aluno.setNome(...)
```

Exemplo:

```java
aluno.setNome(txtNome.getText());
```

Significa:

```text
Tela → Aluno
```

---

# 18. JTextField x JComboBox

## JTextField

Pegar:

```java
txtNome.getText()
```

Colocar:

```java
txtNome.setText(...)
```

---

## JComboBox

Pegar:

```java
cbSexo.getSelectedItem()
```

Colocar:

```java
cbSexo.setSelectedItem(...)
```

---

# 19. String x int

## String → int

Usado ao cadastrar/editar matrícula:

```java
Integer.parseInt(txtMatricula.getText())
```

Exemplo:

```java
aluno.setMatricula(
    Integer.parseInt(txtMatricula.getText())
);
```

---

## int → String

Usado para colocar matrícula no JTextField:

```java
String.valueOf(aluno.getMatricula())
```

Exemplo:

```java
txtMatricula.setText(
    String.valueOf(aluno.getMatricula())
);
```

---

# 20. Fluxos para decorar na prova

## Cadastrar

```text
getText()
   ↓
new Aluno()
   ↓
alunos.add()
   ↓
atualizarTabela()
   ↓
salvarArquivo()
```

---

## Editar

```text
getSelectedRow()
   ↓
alunos.get(linha)
   ↓
getters
   ↓
preenche campos
```

---

## Salvar alterações

```text
getSelectedRow()
   ↓
alunos.get(linha)
   ↓
setters
   ↓
atualizarTabela()
   ↓
salvarArquivo()
```

---

## Excluir

```text
getSelectedRow()
   ↓
alunos.remove(linha)
   ↓
atualizarTabela()
   ↓
salvarArquivo()
```

---

## Abrir programa

```text
carregarArquivo()
   ↓
readLine()
   ↓
split(";")
   ↓
new Aluno()
   ↓
alunos.add()
   ↓
atualizarTabela()
```

---

# 21. Diferença entre os principais métodos

| Código | Função |
|---|---|
| `alunos.add(aluno)` | Adiciona aluno na lista |
| `alunos.get(linha)` | Pega aluno da lista |
| `alunos.remove(linha)` | Remove aluno da lista |
| `tabelaAlunos.getSelectedRow()` | Descobre linha selecionada |
| `atualizarTabela()` | Atualiza JTable |
| `salvarArquivo()` | Lista → TXT |
| `carregarArquivo()` | TXT → Lista |
| `aluno.getNome()` | Pega nome |
| `aluno.setNome(...)` | Altera nome |
| `getText()` | Pega texto do campo |
| `setText(...)` | Coloca texto no campo |
| `getSelectedItem()` | Pega item do combo |
| `setSelectedItem(...)` | Seleciona item do combo |

---

# 22. ⚠️ Erros comuns

## Esquecer `()`

Errado:

```java
salvarArquivo;
```

Certo:

```java
salvarArquivo();
```

Métodos precisam de `()` quando são chamados.

---

## Esquecer o `-1`

Errado:

```java
alunos.remove(linha);
```

sem verificar se existe seleção.

Certo:

```java
if (linha != -1) {
    alunos.remove(linha);
}
```

---

## Matrícula

Errado:

```java
txtMatricula.setText(aluno.getMatricula());
```

Certo:

```java
txtMatricula.setText(String.valueOf(aluno.getMatricula()));
```

E para o caminho contrário:

```java
aluno.setMatricula(Integer.parseInt(txtMatricula.getText()));
```

---

# 23. Checklist final

Antes de entregar:

```text
[ ] Classe Aluno criada
[ ] Atributos criados
[ ] Construtor criado
[ ] Getters criados
[ ] Setters criados
[ ] toString() criado
[ ] ArrayList<Aluno> criada
[ ] JTable criada
[ ] atualizarTabela()
[ ] Botão Cadastrar
[ ] salvarArquivo()
[ ] carregarArquivo()
[ ] carregarArquivo() no construtor
[ ] Botão Editar
[ ] Botão Salvar Alterações
[ ] Botão Excluir
```

---

# 🧠 RESUMÃO PARA A PROVA

Se esquecer tudo, lembre desta ideia:

```text
              ALUNO
                ↕
        ArrayList<Aluno>
          ↙           ↘
       JTable       TXT
```

### Tela → objeto

```java
aluno.setNome(txtNome.getText());
```

### Objeto → tela

```java
txtNome.setText(aluno.getNome());
```

### Lista → tabela

```java
atualizarTabela();
```

### Lista → arquivo

```java
salvarArquivo();
```

### Arquivo → lista

```java
carregarArquivo();
```

### Pegar linha selecionada

```java
int linha = tabelaAlunos.getSelectedRow();
```

### Pegar aluno

```java
Aluno aluno = alunos.get(linha);
```

### Adicionar

```java
alunos.add(aluno);
```

### Remover

```java
alunos.remove(linha);
```

### Atualizar

```java
atualizarTabela();
```

### Persistir

```java
salvarArquivo();
```
