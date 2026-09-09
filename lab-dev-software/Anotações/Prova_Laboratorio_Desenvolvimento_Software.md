# 📚 Cola de Prova — Laboratório e Desenvolvimento de Software
## Cadastro de Espécies — Java Swing / NetBeans

> **Objetivo:** guia rápido para montar do zero uma aplicação CRUD simples em Java Swing usando:
> - `JTextField`
> - `JComboBox`
> - `JRadioButton`
> - `ButtonGroup`
> - `JTable`
> - botões **Cadastrar / Editar / Salvar alterações / Excluir**
> - `ArrayList`
> - conversões `String ↔ int / float / double`
> - eventos `ActionPerformed`
>
> **Ideia principal:** a interface coleta os dados → transforma os valores para o tipo correto → cria/altera um objeto → coloca na `ArrayList` → atualiza a `JTable`.

---

# 1. 🧠 Visão geral do projeto

A aplicação tem basicamente esta estrutura:

```text
Tela (Cadastro.java)
   │
   ├── JTextField → nome científico
   ├── JTextField → apelido
   ├── JTextField → peso
   ├── JTextField → altura
   ├── JComboBox → grupo
   ├── JRadioButton → sexo
   │       └── ButtonGroup
   │
   ├── Cadastrar
   ├── Editar
   ├── Salvar alterações
   ├── Excluir
   │
   └── JTable
          ↑
          │
       ArrayList
          │
          ↓
  CadastroEspecies.java
```

A classe `CadastroEspecies` representa **uma espécie**.

A classe `Cadastro` representa a **tela** e controla os componentes.

---

# 2. 🏗️ Estrutura das classes

## Classe `CadastroEspecies`

Exemplo:

```java
public class CadastroEspecies {

    private String nomeCientifico;
    private String apelido;
    private float peso;
    private float altura;
    private String grupo;
    private String sexo;

    public CadastroEspecies(String nomeCientifico, String apelido,
            float peso, float altura, String grupo, String sexo) {

        this.nomeCientifico = nomeCientifico;
        this.apelido = apelido;
        this.peso = peso;
        this.altura = altura;
        this.grupo = grupo;
        this.sexo = sexo;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
```

### 🔑 Decore

```java
private → atributo

get → pega o valor

set → altera o valor
```

Exemplo:

```java
especie.getPeso();       // pega
especie.setPeso(10.5f);  // altera
```

---

# 3. 📦 ArrayList

Na classe da tela:

```java
ArrayList<CadastroEspecies> especies = new ArrayList<>();
```

Import:

```java
import java.util.ArrayList;
```

Ela funciona como uma lista de objetos.

### Adicionar

```java
especies.add(especie);
```

### Pegar pela posição

```java
CadastroEspecies especie = especies.get(0);
```

### Remover

```java
especies.remove(0);
```

### Quantidade

```java
especies.size();
```

---

# 4. 📝 JTextField

Para pegar texto:

```java
String nome = txtCientifico.getText();
```

Sempre que usamos:

```java
getText()
```

o retorno é:

```java
String
```

Mesmo que o usuário digite:

```text
25
```

o Java recebe:

```java
"25"
```

Isso é MUITO importante.

---

# 5. 🔄 CONVERSÕES — PARTE MAIS IMPORTANTE

## String → int

```java
int idade = Integer.parseInt(txtIdade.getText());
```

Exemplo:

```java
String texto = "25";

int numero = Integer.parseInt(texto);
```

Resultado:

```text
"25" → 25
String → int
```

---

## String → float

```java
float peso = Float.parseFloat(txtPeso.getText());
```

Exemplo:

```java
String texto = "72.5";

float valor = Float.parseFloat(texto);
```

Resultado:

```text
"72.5" → 72.5
String → float
```

### ⚠️ Atenção

Em Java, normalmente o decimal usa `.`:

```text
72.5
```

e não:

```text
72,5
```

Se quiser aceitar vírgula:

```java
float peso = Float.parseFloat(
    txtPeso.getText().replace(",", ".")
);
```

---

## String → double

```java
double altura = Double.parseDouble(txtAltura.getText());
```

---

## String → long

```java
long valor = Long.parseLong(txtValor.getText());
```

---

# 6. 🔄 Número → String

## int → String

```java
int idade = 20;

String texto = String.valueOf(idade);
```

Também:

```java
String texto = Integer.toString(idade);
```

---

## float → String

```java
float peso = 72.5f;

String texto = String.valueOf(peso);
```

---

## double → String

```java
double altura = 1.75;

String texto = String.valueOf(altura);
```

---

## Forma genérica

A mais fácil de decorar:

```java
String texto = String.valueOf(valor);
```

Funciona para vários tipos.

---

# 7. 📌 Tabela de conversões para a prova

| De | Para | Como fazer |
|---|---|---|
| `String` | `int` | `Integer.parseInt()` |
| `String` | `float` | `Float.parseFloat()` |
| `String` | `double` | `Double.parseDouble()` |
| `String` | `long` | `Long.parseLong()` |
| `int` | `String` | `String.valueOf()` |
| `float` | `String` | `String.valueOf()` |
| `double` | `String` | `String.valueOf()` |
| `long` | `String` | `String.valueOf()` |

### 🧠 Macete

```text
parse = transforma String em número

valueOf = transforma valor em String
```

---

# 8. 🔘 JRadioButton

Exemplo:

```java
rdbMasculino
rdbFeminino
rdbHermafrodita
```

Para saber se está selecionado:

```java
rdbMasculino.isSelected()
```

Retorna:

```java
true
```

ou:

```java
false
```

---

# 9. 🔘 ButtonGroup / Radio Group

O `ButtonGroup` serve para fazer os Radio Buttons funcionarem como um grupo.

Sem ele:

```text
☑ Masculino
☑ Feminino
☑ Hermafrodita
```

poderiam ficar todos selecionados.

Com `ButtonGroup`:

```text
○ Masculino
○ Feminino
○ Hermafrodita
```

somente um pode ser selecionado.

### Criando

```java
ButtonGroup grupoSexo = new ButtonGroup();

grupoSexo.add(rdbMasculino);
grupoSexo.add(rdbFeminino);
grupoSexo.add(rdbHermafrodita);
```

No NetBeans, normalmente isso pode ser feito pelas propriedades do formulário.

---

# 10. 🧠 Pegando o valor do Radio Button

Uma forma simples:

```java
String sexo = "";

if (rdbMasculino.isSelected()) {
    sexo = "Masculino";
} else if (rdbFeminino.isSelected()) {
    sexo = "Feminino";
} else if (rdbHermafrodita.isSelected()) {
    sexo = "Hermafrodita";
}
```

Depois:

```java
especie.setSexo(sexo);
```

### Para lembrar:

```java
isSelected()
```

= pergunta se está selecionado.

---

# 11. ⬇️ JComboBox

Para pegar o item selecionado:

```java
String grupo = cmbGrupo.getSelectedItem().toString();
```

Exemplo:

```java
String grupo = cmbGrupo.getSelectedItem().toString();
```

Se estiver selecionado:

```text
Mamíferos
```

então:

```java
grupo = "Mamíferos";
```

### Alternativa

```java
String grupo = (String) cmbGrupo.getSelectedItem();
```

---

# 12. ➕ BOTÃO CADASTRAR

A lógica é:

```text
1. Pegar os valores dos campos
2. Converter os números
3. Descobrir o Radio Button selecionado
4. Pegar o ComboBox
5. Criar objeto
6. Adicionar no ArrayList
7. Atualizar tabela
8. Limpar campos
```

Exemplo completo:

```java
private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {

    String nomeCientifico = txtCientifico.getText();
    String apelido = txtApelido.getText();

    float peso = Float.parseFloat(txtPeso.getText());
    float altura = Float.parseFloat(txtAltura.getText());

    String grupo = cmbGrupo.getSelectedItem().toString();

    String sexo = "";

    if (rdbMasculino.isSelected()) {
        sexo = "Masculino";
    } else if (rdbFeminino.isSelected()) {
        sexo = "Feminino";
    } else if (rdbHermafrodita.isSelected()) {
        sexo = "Hermafrodita";
    }

    CadastroEspecies especie = new CadastroEspecies(
        nomeCientifico,
        apelido,
        peso,
        altura,
        grupo,
        sexo
    );

    especies.add(especie);

    atualizarTabela();
    limparCampos();
}
```

---

# 13. 📊 JTable

A tabela geralmente possui:

```text
Nome científico | Apelido | Peso | Altura | Grupo | Sexo
```

Uma forma simples de atualizar:

```java
private void atualizarTabela() {

    DefaultTableModel modelo =
        (DefaultTableModel) tblEspecies.getModel();

    modelo.setRowCount(0);

    for (CadastroEspecies especie : especies) {

        modelo.addRow(new Object[]{
            especie.getNomeCientifico(),
            especie.getApelido(),
            especie.getPeso(),
            especie.getAltura(),
            especie.getGrupo(),
            especie.getSexo()
        });
    }
}
```

Import:

```java
import javax.swing.table.DefaultTableModel;
```

---

# 14. 🧠 Como a JTable funciona

A tabela não precisa necessariamente guardar os objetos.

Os objetos ficam:

```java
ArrayList<CadastroEspecies> especies
```

A tabela mostra os dados desses objetos.

Fluxo:

```text
ArrayList
    ↓
CadastroEspecies
    ↓
getNomeCientifico()
getApelido()
getPeso()
...
    ↓
JTable
```

Por isso existe:

```java
atualizarTabela();
```

sempre que alguma coisa muda.

---

# 15. ✏️ BOTÃO EDITAR

A ideia do botão Editar é:

```text
Seleciona uma linha da tabela
        ↓
Pega o objeto correspondente
        ↓
Coloca os dados nos campos
        ↓
Usuário altera
        ↓
Clica em "Salvar alterações"
```

Primeiro precisamos descobrir qual linha foi selecionada:

```java
int linha = tblEspecies.getSelectedRow();
```

Se nenhuma foi selecionada:

```java
linha == -1
```

Então:

```java
if (linha != -1) {
    // existe uma linha selecionada
}
```

---

# 16. ✏️ Código do EDITAR

```java
private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {

    int linha = tblEspecies.getSelectedRow();

    if (linha != -1) {

        CadastroEspecies especie = especies.get(linha);

        txtCientifico.setText(especie.getNomeCientifico());
        txtApelido.setText(especie.getApelido());

        txtPeso.setText(String.valueOf(especie.getPeso()));
        txtAltura.setText(String.valueOf(especie.getAltura()));

        cmbGrupo.setSelectedItem(especie.getGrupo());

        if (especie.getSexo().equals("Masculino")) {
            rdbMasculino.setSelected(true);

        } else if (especie.getSexo().equals("Feminino")) {
            rdbFeminino.setSelected(true);

        } else if (especie.getSexo().equals("Hermafrodita")) {
            rdbHermafrodita.setSelected(true);
        }
    }
}
```

### Aqui aparecem DUAS conversões importantes:

O atributo é:

```java
float
```

mas `setText()` precisa de:

```java
String
```

Então:

```java
String.valueOf(especie.getPeso())
```

faz:

```text
float → String
```

---

# 17. 💾 BOTÃO SALVAR ALTERAÇÕES

Esse é um dos códigos mais importantes da prova.

```java
private void btnSalvarAlteracoesActionPerformed(
        java.awt.event.ActionEvent evt) {

    int linha = tblEspecies.getSelectedRow();

    if (linha != -1) {

        CadastroEspecies especie = especies.get(linha);

        especie.setNomeCientifico(txtCientifico.getText());
        especie.setApelido(txtApelido.getText());

        especie.setPeso(
            Float.parseFloat(txtPeso.getText())
        );

        especie.setAltura(
            Float.parseFloat(txtAltura.getText())
        );

        especie.setGrupo(
            cmbGrupo.getSelectedItem().toString()
        );

        String sexo = "";

        if (rdbMasculino.isSelected()) {
            sexo = "Masculino";
        } else if (rdbFeminino.isSelected()) {
            sexo = "Feminino";
        } else if (rdbHermafrodita.isSelected()) {
            sexo = "Hermafrodita";
        }

        especie.setSexo(sexo);

        atualizarTabela();
        limparCampos();
    }
}
```

---

# 18. 🧠 ENTENDA o Salvar Alterações

Esta linha:

```java
int linha = tblEspecies.getSelectedRow();
```

descobre a posição da tabela.

Depois:

```java
CadastroEspecies especie = especies.get(linha);
```

pega o objeto daquela posição.

Então alteramos o objeto:

```java
especie.setNomeCientifico(...);
especie.setApelido(...);
especie.setPeso(...);
```

Finalmente:

```java
atualizarTabela();
```

faz a tabela mostrar os novos valores.

---

# 19. 🗑️ BOTÃO EXCLUIR

Lógica:

```text
1. Descobrir linha selecionada
2. Verificar se existe seleção
3. Remover do ArrayList
4. Atualizar tabela
```

Código:

```java
private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {

    int linha = tblEspecies.getSelectedRow();

    if (linha != -1) {

        especies.remove(linha);

        atualizarTabela();
        limparCampos();
    }
}
```

### ⚠️ Muito importante

Não confundir:

```java
tblEspecies.getSelectedRow()
```

com:

```java
especies.get(...)
```

O primeiro pega a **linha selecionada**.

O segundo pega o **objeto da lista**.

---

# 20. 🧹 LIMPAR CAMPOS

Crie um método:

```java
private void limparCampos() {

    txtCientifico.setText("");
    txtApelido.setText("");
    txtPeso.setText("");
    txtAltura.setText("");

    cmbGrupo.setSelectedIndex(0);

    grupoSexo.clearSelection();
}
```

Se o `ButtonGroup` tiver outro nome, use o nome dele.

Exemplo:

```java
buttonGroupSexo.clearSelection();
```

---

# 21. ⚠️ TRY/CATCH PARA CONVERSÕES

Se o usuário digitar:

```text
abc
```

aqui:

```java
float peso = Float.parseFloat(txtPeso.getText());
```

vai ocorrer erro.

O ideal:

```java
try {

    float peso = Float.parseFloat(txtPeso.getText());

} catch (NumberFormatException e) {

    JOptionPane.showMessageDialog(
        this,
        "Digite um número válido!"
    );
}
```

Import:

```java
import javax.swing.JOptionPane;
```

---

# 22. ✅ CADASTRAR COM VALIDAÇÃO

Uma versão mais segura:

```java
private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {

    try {

        String nomeCientifico = txtCientifico.getText();
        String apelido = txtApelido.getText();

        float peso = Float.parseFloat(
            txtPeso.getText().replace(",", ".")
        );

        float altura = Float.parseFloat(
            txtAltura.getText().replace(",", ".")
        );

        String grupo =
            cmbGrupo.getSelectedItem().toString();

        String sexo = "";

        if (rdbMasculino.isSelected()) {
            sexo = "Masculino";
        } else if (rdbFeminino.isSelected()) {
            sexo = "Feminino";
        } else if (rdbHermafrodita.isSelected()) {
            sexo = "Hermafrodita";
        }

        CadastroEspecies especie =
            new CadastroEspecies(
                nomeCientifico,
                apelido,
                peso,
                altura,
                grupo,
                sexo
            );

        especies.add(especie);

        atualizarTabela();
        limparCampos();

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
            this,
            "Peso e altura devem ser números!"
        );
    }
}
```

---

# 23. 🔁 Conversões dentro do projeto

## Ao PEGAR do JTextField

Tudo começa como `String`.

```java
txtPeso.getText()
```

retorna:

```text
String
```

Se o atributo for `float`:

```java
Float.parseFloat(txtPeso.getText())
```

Resultado:

```text
String → float
```

---

## Ao COLOCAR um número no JTextField

`setText()` quer `String`.

Então:

```java
txtPeso.setText(String.valueOf(especie.getPeso()));
```

faz:

```text
float → String
```

---

# 24. ⭐ As linhas que você precisa saber de cabeça

### JTextField → String

```java
String nome = txtNome.getText();
```

### String → int

```java
int idade = Integer.parseInt(txtIdade.getText());
```

### String → float

```java
float peso = Float.parseFloat(txtPeso.getText());
```

### String → double

```java
double altura = Double.parseDouble(txtAltura.getText());
```

### Número → String

```java
String texto = String.valueOf(numero);
```

### ComboBox → String

```java
String grupo = cmbGrupo.getSelectedItem().toString();
```

### RadioButton

```java
if (rdbMasculino.isSelected()) {
    sexo = "Masculino";
}
```

### JTable → linha selecionada

```java
int linha = tblEspecies.getSelectedRow();
```

### ArrayList → objeto

```java
CadastroEspecies especie = especies.get(linha);
```

### ArrayList → adicionar

```java
especies.add(especie);
```

### ArrayList → remover

```java
especies.remove(linha);
```

### JTextField ← String

```java
txtNome.setText(nome);
```

### ComboBox ← valor

```java
cmbGrupo.setSelectedItem(grupo);
```

### RadioButton ← seleção

```java
rdbMasculino.setSelected(true);
```

---

# 25. 🧩 Estrutura completa do CRUD

## CREATE — Cadastrar

```java
especies.add(especie);
```

---

## READ — Mostrar

```java
atualizarTabela();
```

---

## UPDATE — Alterar

```java
especie.setNomeCientifico(...);
especie.setPeso(...);
```

---

## DELETE — Excluir

```java
especies.remove(linha);
```

### Macete:

```text
C → add()
R → get() / tabela
U → set()
D → remove()
```

---

# 26. 📊 Atualização da JTable

Sempre que houver:

```java
add()
```

```java
set()
```

ou:

```java
remove()
```

é uma boa prática fazer:

```java
atualizarTabela();
```

Método:

```java
private void atualizarTabela() {

    DefaultTableModel modelo =
        (DefaultTableModel) tblEspecies.getModel();

    modelo.setRowCount(0);

    for (CadastroEspecies especie : especies) {

        modelo.addRow(new Object[]{
            especie.getNomeCientifico(),
            especie.getApelido(),
            especie.getPeso(),
            especie.getAltura(),
            especie.getGrupo(),
            especie.getSexo()
        });
    }
}
```

---

# 27. 🔄 For-each

Esse trecho:

```java
for (CadastroEspecies especie : especies) {
```

significa:

> Para cada objeto `CadastroEspecies` dentro de `especies`...

Exemplo:

```java
for (CadastroEspecies especie : especies) {
    System.out.println(especie.getNomeCientifico());
}
```

---

# 28. 🛠️ Criando o projeto do zero no NetBeans

## Passo 1 — Criar projeto

```text
File
→ New Project
→ Java Application
```

---

## Passo 2 — Criar classe do objeto

```text
Source Packages
→ botão direito
→ New
→ Java Class
```

Nome:

```text
CadastroEspecies
```

Crie os atributos:

```java
private String nomeCientifico;
private String apelido;
private float peso;
private float altura;
private String grupo;
private String sexo;
```

Depois:

```text
Insert Code
→ Getter and Setter
```

E, se necessário:

```text
Insert Code
→ Constructor
```

---

# 29. 🖥️ Criando a tela

Crie:

```text
JFrame Form
```

Nome:

```text
Cadastro
```

Arraste:

```text
JLabel
JTextField
JComboBox
JRadioButton
JTable
JButton
```

Monte aproximadamente:

```text
Nome científico    Apelido       Peso       Altura

Grupo: [Mamíferos]

Sexo:
○ Masculino  ○ Feminino  ○ Hermafrodita

---------------------------------------------------
| Nome | Apelido | Peso | Altura | Grupo | Sexo |
---------------------------------------------------

[Cadastrar] [Editar] [Salvar alterações] [Excluir]
```

---

# 30. 🔘 Configurando os Radio Buttons

Crie um `ButtonGroup`.

Depois adicione:

```text
rdbMasculino
rdbFeminino
rdbHermafrodita
```

ao mesmo grupo.

Assim, apenas um pode ser selecionado.

---

# 31. ⬇️ Configurando o ComboBox

No `JComboBox`, adicione itens:

```text
Mamíferos
Aves
Répteis
Anfíbios
Peixes
```

ou os grupos solicitados pelo professor.

Para pegar:

```java
String grupo =
    cmbGrupo.getSelectedItem().toString();
```

---

# 32. 📋 Configurando a JTable

Defina as colunas:

```text
Nome científico
Apelido
Peso
Altura
Grupo
Sexo
```

O código pode trabalhar com:

```java
DefaultTableModel
```

---

# 33. ⚠️ Erros comuns na prova

## Erro 1 — Esquecer que getText() retorna String

Errado:

```java
float peso = txtPeso.getText();
```

Certo:

```java
float peso = Float.parseFloat(txtPeso.getText());
```

---

## Erro 2 — Colocar float diretamente no setText

Errado:

```java
txtPeso.setText(especie.getPeso());
```

Certo:

```java
txtPeso.setText(String.valueOf(especie.getPeso()));
```

---

## Erro 3 — Esquecer o `f` em float

```java
float peso = 72.5f;
```

O `f` indica que o literal é `float`.

Sem `f`:

```java
float peso = 72.5;
```

pode gerar erro porque `72.5` é tratado como `double`.

---

## Erro 4 — Não verificar linha selecionada

Sempre:

```java
if (linha != -1) {
```

antes de:

```java
especies.get(linha);
```

ou:

```java
especies.remove(linha);
```

---

## Erro 5 — Radio Button sem verificar

Use:

```java
isSelected()
```

Exemplo:

```java
if (rdbMasculino.isSelected()) {
    sexo = "Masculino";
}
```

---

## Erro 6 — Esquecer de atualizar a tabela

Depois de cadastrar:

```java
atualizarTabela();
```

Depois de editar:

```java
atualizarTabela();
```

Depois de excluir:

```java
atualizarTabela();
```

---

# 34. 🧯 NullPointerException no ComboBox

Se houver possibilidade de nenhum item:

```java
Object item = cmbGrupo.getSelectedItem();

if (item != null) {
    String grupo = item.toString();
}
```

Na maioria dos exercícios simples, o ComboBox já começa com um item selecionado.

---

# 35. 🧠 Equals

Para comparar `String`, prefira:

```java
if (sexo.equals("Masculino")) {
```

ou:

```java
if ("Masculino".equals(sexo)) {
```

Evite:

```java
if (sexo == "Masculino")
```

Porque `==` compara referências, não o conteúdo da String da maneira esperada.

---

# 36. 🧪 Exemplo completo de fluxo

Usuário digita:

```text
Nome científico: Panthera leo
Apelido: Leão
Peso: 190
Altura: 1.2
Grupo: Mamíferos
Sexo: Masculino
```

O programa recebe inicialmente:

```java
String nome = "Panthera leo";
String apelido = "Leão";
String pesoTexto = "190";
String alturaTexto = "1.2";
String grupo = "Mamíferos";
```

Converte:

```java
float peso = Float.parseFloat("190");
float altura = Float.parseFloat("1.2");
```

Resultado:

```java
peso = 190.0f;
altura = 1.2f;
```

Cria:

```java
CadastroEspecies especie =
    new CadastroEspecies(
        "Panthera leo",
        "Leão",
        190.0f,
        1.2f,
        "Mamíferos",
        "Masculino"
    );
```

Adiciona:

```java
especies.add(especie);
```

Atualiza:

```java
atualizarTabela();
```

---

# 37. 🧠 O mapa mental da prova

```text
JTextField
   │
   └── getText()
          ↓
       String
          │
          ├── Integer.parseInt()
          │       ↓
          │      int
          │
          ├── Float.parseFloat()
          │       ↓
          │     float
          │
          └── Double.parseDouble()
                  ↓
                double


Número
   │
   └── String.valueOf()
             ↓
          String
             ↓
       setText(String)
```

---

# 38. 🚨 Se der erro na hora da prova

## `NumberFormatException`

Provavelmente:

```java
Float.parseFloat(...)
```

recebeu algo que não é número.

Exemplo:

```text
"abc"
```

Solução:

```java
try {
    float peso = Float.parseFloat(txtPeso.getText());
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Número inválido!");
}
```

---

## `IndexOutOfBoundsException`

Provavelmente tentou:

```java
especies.get(linha);
```

com uma posição inválida.

Confira:

```java
if (linha != -1)
```

---

## `NullPointerException`

Alguma variável/componente está `null`.

Verifique principalmente:

```java
getSelectedItem()
```

e nomes dos componentes.

---

## `cannot find symbol`

Provavelmente:

- nome de variável errado;
- nome do componente errado;
- método não existe;
- import faltando.

Confira o nome exato no NetBeans.

---

# 39. 📝 Imports que provavelmente serão necessários

```java
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
```

Dependendo do projeto, outros imports podem ser adicionados automaticamente pelo NetBeans.

---

# 40. ⭐ COLA ULTRARRÁPIDA

Se tiver pouquíssimo tempo, decore isto:

### Pegar texto

```java
txtNome.getText()
```

### Colocar texto

```java
txtNome.setText("texto");
```

### String → int

```java
Integer.parseInt(texto)
```

### String → float

```java
Float.parseFloat(texto)
```

### String → double

```java
Double.parseDouble(texto)
```

### Número → String

```java
String.valueOf(numero)
```

### ComboBox

```java
cmbGrupo.getSelectedItem().toString()
```

### Radio Button

```java
rdbMasculino.isSelected()
```

### Linha da tabela

```java
int linha = tblEspecies.getSelectedRow();
```

### Verificar seleção

```java
if (linha != -1)
```

### Pegar objeto

```java
CadastroEspecies especie = especies.get(linha);
```

### Adicionar

```java
especies.add(especie);
```

### Remover

```java
especies.remove(linha);
```

### Alterar

```java
especie.setNomeCientifico(...);
```

### Atualizar tabela

```java
atualizarTabela();
```

### Limpar

```java
txtNome.setText("");
```

---

# 41. 🏆 RECEITA PARA FAZER O PROJETO NA PROVA

Se o professor entregar uma tela parecida e pedir um cadastro, siga esta ordem:

```text
1. Criar classe do objeto
        ↓
2. Criar atributos
        ↓
3. Criar construtor
        ↓
4. Criar getters/setters
        ↓
5. Criar ArrayList na tela
        ↓
6. Configurar componentes
        ↓
7. Configurar ButtonGroup
        ↓
8. Criar atualizarTabela()
        ↓
9. Fazer CADASTRAR
        ↓
10. Fazer EDITAR
        ↓
11. Fazer SALVAR ALTERAÇÕES
        ↓
12. Fazer EXCLUIR
        ↓
13. Fazer limparCampos()
        ↓
14. Testar
```

---

# 42. 💡 Estratégia para não se perder

Faça **primeiro o cadastro**.

Quando cadastrar estiver funcionando:

```text
Cadastrar → ArrayList → JTable
```

depois faça:

```text
Editar → campos
```

depois:

```text
Salvar alterações → setters → JTable
```

por último:

```text
Excluir → remove() → JTable
```

Não tente fazer tudo ao mesmo tempo.

---

# 43. 🎯 O conceito mais importante

O professor pode mudar:

- `Animal`
- `Aluno`
- `Produto`
- `Funcionário`
- `Espécie`
- `Livro`
- `Veículo`

Mas a lógica é praticamente a mesma:

```java
ArrayList<Objeto> lista;
```

### Cadastrar

```java
lista.add(objeto);
```

### Selecionar

```java
int linha = tabela.getSelectedRow();
Objeto objeto = lista.get(linha);
```

### Alterar

```java
objeto.setAlgumaCoisa(valor);
```

### Excluir

```java
lista.remove(linha);
```

### Mostrar

```java
atualizarTabela();
```

---

# 🔥 RESUMO FINAL

```text
CAMPO
↓
getText()
↓
String
↓
parseFloat / parseInt / parseDouble
↓
tipo numérico
↓
objeto
↓
ArrayList
↓
JTable
```

Para voltar:

```text
objeto.getPeso()
↓
float
↓
String.valueOf()
↓
String
↓
setText()
↓
JTextField
```

### Decore estas 6 coisas:

```java
getText()
setText()

Integer.parseInt()
Float.parseFloat()
Double.parseDouble()

String.valueOf()
```

E estas 6 da tabela/lista:

```java
getSelectedRow()
get()
add()
remove()
set()
atualizarTabela()
```

**Se você dominar essas operações, consegue reconstruir a maior parte desse tipo de projeto durante a prova.**
