# Guia de revisão --- Projeto Java Swing + MySQL

> Material de consulta para reproduzir o projeto de Categorias e
> Produtos no NetBeans, seguindo a organização das aulas do professor.
>
> **Atenção:** o código foi preparado durante a montagem do projeto, mas
> a conexão com o MySQL não foi validada neste computador. O erro
> `No suitable driver found` indica ausência do MySQL Connector/J no
> projeto. Sem o driver e um servidor MySQL ativo, as operações no banco
> não serão executadas.

## 1. Estrutura do projeto

Projeto: `ProjetoProdutos`

Pacotes:

-   `beans`
    -   `Categoria.java`
    -   `Produto.java`
-   `DAO`
    -   `CategoriaDAO.java`
    -   `ProdutoDAO.java`
-   `conexao`
    -   `Conexao.java`
-   `telas`
    -   `CadastroCategoria.java`
    -   `CadastroProduto.java`
    -   `EditarProduto.java`
    -   `ListarProdutos.java`
    -   `MenuPrincipal.java`

## 2. Banco de dados

Banco: `BDAula01`

``` sql
CREATE DATABASE BDAula01;
USE BDAula01;

CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    preco DOUBLE NOT NULL,
    quantidade INT NOT NULL,
    categoria_id INT NOT NULL,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

SHOW TABLES;
DESC categorias;
DESC produtos;
```

A tabela `produtos` possui a chave estrangeira `categoria_id`, que
referencia `categorias.id`.

## 3. Classe de conexão --- `Conexao.java`

``` java
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    public Connection getConexao() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/BDAula01?userTimeZone=true&serverTimeZone=UTC",
                "root", "1234"
            );
            System.out.println("Conexão efetuada!");
            return conn;
        } catch (Exception e) {
            System.out.println("Erro ao conectar no BD" + e.getMessage());
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
```

Ajuste usuário, senha e nome do banco conforme o ambiente da
prova/professor.

## 4. Beans

### `Categoria.java`

``` java
package beans;

public class Categoria {
    private int id;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
```

O `toString()` faz o `JComboBox` exibir o nome da categoria quando
recebe objetos `Categoria`.

### `Produto.java`

``` java
package beans;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private Categoria categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto{" + "nome=" + nome + ", preco=" + preco
                + ", quantidade=" + quantidade + ", categoria=" + categoria + '}';
    }
}
```

O atributo `Categoria categoria` representa o relacionamento entre
Produto e Categoria no Java.

## 5. DAO --- acesso ao banco de dados

### `CategoriaDAO.java`

``` java
package DAO;

import beans.Categoria;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection conn;

    public CategoriaDAO() {
        this.conn = new Conexao().getConexao();
    }

    public void inserir(Categoria categoria) {
        String sql = "INSERT INTO categorias (nome) VALUES (?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoria.getNome());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir categoria: " + ex.getMessage());
        }
    }

    public List<Categoria> getCategorias() {
        String sql = "SELECT * FROM categorias";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Categoria> listaCategorias = new ArrayList<>();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                listaCategorias.add(c);
            }
            return listaCategorias;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar categorias: " + ex.getMessage());
            return null;
        }
    }

    public Categoria getCategoria(int id) {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                return c;
            }
            return null;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar categoria: " + ex.getMessage());
            return null;
        }
    }

    public void editar(Categoria categoria) {
        String sql = "UPDATE categorias SET nome = ? WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar categoria: " + ex.getMessage());
        }
    }
}
```

### `ProdutoDAO.java`

``` java
package DAO;

import beans.Produto;
import beans.Categoria;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Connection conn;

    public ProdutoDAO() {
        this.conn = new Conexao().getConexao();
    }

    public void inserir(Produto produto) {
        String sql = "INSERT INTO produtos (nome, preco, quantidade, categoria_id) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getCategoria().getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir produto: " + ex.getMessage());
        }
    }

    public List<Produto> getProdutos() {
        String sql = "SELECT p.*, c.nome AS categoria_nome FROM produtos p "
                   + "INNER JOIN categorias c ON p.categoria_id = c.id";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Produto> listaProdutos = new ArrayList<>();

            while (rs.next()) {
                Produto p = new Produto();
                Categoria c = new Categoria();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidade(rs.getInt("quantidade"));

                c.setId(rs.getInt("categoria_id"));
                c.setNome(rs.getString("categoria_nome"));
                p.setCategoria(c);

                listaProdutos.add(p);
            }
            return listaProdutos;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar produtos: " + ex.getMessage());
            return null;
        }
    }

    public Produto getProduto(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Produto p = new Produto();
                Categoria c = new Categoria();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidade(rs.getInt("quantidade"));

                c.setId(rs.getInt("categoria_id"));
                p.setCategoria(c);

                return p;
            }
            return null;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar produto: " + ex.getMessage());
            return null;
        }
    }

    public void editar(Produto produto) {
        String sql = "UPDATE produtos SET nome=?, preco=?, quantidade=?, categoria_id=? WHERE id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setInt(4, produto.getCategoria().getId());
            stmt.setInt(5, produto.getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar produto: " + ex.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM produtos WHERE id=?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir produto: " + ex.getMessage());
        }
    }

    public List<Produto> buscar(String pesquisa) {
        String sql = "SELECT p.*, c.nome AS categoria_nome FROM produtos p "
                   + "INNER JOIN categorias c ON p.categoria_id = c.id "
                   + "WHERE p.nome LIKE ? OR c.nome LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + pesquisa + "%");
            stmt.setString(2, "%" + pesquisa + "%");
            ResultSet rs = stmt.executeQuery();
            List<Produto> listaProdutos = new ArrayList<>();

            while (rs.next()) {
                Produto p = new Produto();
                Categoria c = new Categoria();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidade(rs.getInt("quantidade"));

                c.setId(rs.getInt("categoria_id"));
                c.setNome(rs.getString("categoria_nome"));
                p.setCategoria(c);

                listaProdutos.add(p);
            }
            return listaProdutos;
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar produtos: " + ex.getMessage());
            return null;
        }
    }
}
```

## 6. Código das telas

Os formulários visuais são montados pelo Design do NetBeans. Os nomes
das variáveis precisam coincidir com os usados nos eventos abaixo.

### 6.1 `CadastroCategoria`

Componentes principais:

-   `txtId` --- não editável
-   `txtNome`
-   `txtConsultaId`
-   `tblCategorias`
-   `btnSalvar`
-   `btnLimpar`
-   `btnConsultar`
-   `btnAtualizar` (pode ter sido inicialmente chamado `btnEditar`)

Imports:

``` java
import beans.Categoria;
import DAO.CategoriaDAO;
import javax.swing.JOptionPane;
import java.util.List;
import javax.swing.table.DefaultTableModel;
```

Evento Salvar:

``` java
Categoria categoria = new Categoria();
categoria.setNome(txtNome.getText());

CategoriaDAO dao = new CategoriaDAO();
dao.inserir(categoria);

JOptionPane.showMessageDialog(null, "Categoria cadastrada!");
txtNome.setText("");
```

Evento Limpar:

``` java
txtId.setText("");
txtNome.setText("");
```

Método para carregar a tabela:

``` java
private void carregarTabela() {
    DefaultTableModel modelo = (DefaultTableModel) tblCategorias.getModel();
    modelo.setRowCount(0);

    CategoriaDAO dao = new CategoriaDAO();
    List<Categoria> lista = dao.getCategorias();

    for (Categoria c : lista) {
        modelo.addRow(new Object[]{
            c.getId(),
            c.getNome()
        });
    }
}
```

No construtor, depois de `initComponents();`:

``` java
carregarTabela();
```

Evento Consultar:

``` java
int id = Integer.parseInt(txtConsultaId.getText());

CategoriaDAO dao = new CategoriaDAO();
Categoria categoria = dao.getCategoria(id);

if (categoria == null) {
    txtId.setText("");
    txtNome.setText("");
    JOptionPane.showMessageDialog(null, "Categoria não encontrada!");
} else {
    txtId.setText(String.valueOf(categoria.getId()));
    txtNome.setText(categoria.getNome());
}
```

Evento Atualizar:

``` java
Categoria categoria = new Categoria();

categoria.setId(Integer.parseInt(txtId.getText()));
categoria.setNome(txtNome.getText());

CategoriaDAO dao = new CategoriaDAO();
dao.editar(categoria);

JOptionPane.showMessageDialog(null, "Categoria atualizada!");

txtConsultaId.setText("");
txtId.setText("");
txtNome.setText("");

carregarTabela();
```

### 6.2 `CadastroProduto`

Componentes principais:

-   `txtId` --- não editável
-   `txtNome`
-   `txtPreco`
-   `txtQuantidade`
-   `cbCategoria` --- tipo `JComboBox<Categoria>`
-   `btnSalvar`
-   `btnLimpar`

Imports:

``` java
import beans.Categoria;
import beans.Produto;
import DAO.CategoriaDAO;
import DAO.ProdutoDAO;
import java.util.List;
import javax.swing.JOptionPane;
```

Método para carregar categorias:

``` java
private void carregarCategorias() {
    CategoriaDAO dao = new CategoriaDAO();
    List<Categoria> lista = dao.getCategorias();

    cbCategoria.removeAllItems();

    for (Categoria c : lista) {
        cbCategoria.addItem(c);
    }
}
```

No construtor, depois de `initComponents();`:

``` java
carregarCategorias();
```

Evento Salvar:

``` java
Produto produto = new Produto();

produto.setNome(txtNome.getText());
produto.setPreco(Double.parseDouble(txtPreco.getText()));
produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));

Categoria categoria = (Categoria) cbCategoria.getSelectedItem();
produto.setCategoria(categoria);

ProdutoDAO dao = new ProdutoDAO();
dao.inserir(produto);

JOptionPane.showMessageDialog(null, "Produto cadastrado!");

txtNome.setText("");
txtPreco.setText("");
txtQuantidade.setText("");
```

Evento Limpar:

``` java
txtId.setText("");
txtNome.setText("");
txtPreco.setText("");
txtQuantidade.setText("");
cbCategoria.setSelectedIndex(0);
```

### 6.3 `ListarProdutos`

Componentes principais:

-   `tblProdutos`
-   `txtPesquisa`
-   `btnPesquisar`
-   `btnAtualizar`

Imports:

``` java
import beans.Produto;
import DAO.ProdutoDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
```

Método para carregar tabela:

``` java
private void carregarTabela() {
    DefaultTableModel modelo = (DefaultTableModel) tblProdutos.getModel();
    modelo.setRowCount(0);

    ProdutoDAO dao = new ProdutoDAO();
    List<Produto> lista = dao.getProdutos();

    for (Produto p : lista) {
        modelo.addRow(new Object[]{
            p.getId(),
            p.getNome(),
            p.getPreco(),
            p.getQuantidade(),
            p.getCategoria().getNome()
        });
    }
}
```

No construtor, depois de `initComponents();`:

``` java
carregarTabela();
```

Evento Pesquisar:

``` java
String pesquisa = txtPesquisa.getText();

ProdutoDAO dao = new ProdutoDAO();
List<Produto> lista = dao.buscar(pesquisa);

DefaultTableModel modelo = (DefaultTableModel) tblProdutos.getModel();
modelo.setRowCount(0);

for (Produto p : lista) {
    modelo.addRow(new Object[]{
        p.getId(),
        p.getNome(),
        p.getPreco(),
        p.getQuantidade(),
        p.getCategoria().getNome()
    });
}
```

Evento Atualizar:

``` java
txtPesquisa.setText("");
carregarTabela();
```

### 6.4 `EditarProduto`

Componentes principais:

-   `txtConsultaId`
-   `btnConsultar`
-   `txtId` --- não editável
-   `txtNome`
-   `txtPreco`
-   `txtQuantidade`
-   `cbCategoria` --- tipo `JComboBox<Categoria>`
-   `btnAtualizar`
-   `btnExcluir`

Imports:

``` java
import beans.Produto;
import beans.Categoria;
import DAO.ProdutoDAO;
import DAO.CategoriaDAO;
import java.util.List;
import javax.swing.JOptionPane;
```

Copiar o método `carregarCategorias()` da tela `CadastroProduto` para
essa tela. No construtor, depois de `initComponents();`, chamar:

``` java
carregarCategorias();
```

Evento Consultar:

``` java
int id = Integer.parseInt(txtConsultaId.getText());

ProdutoDAO dao = new ProdutoDAO();
Produto produto = dao.getProduto(id);

if (produto == null) {
    txtId.setText("");
    txtNome.setText("");
    txtPreco.setText("");
    txtQuantidade.setText("");

    JOptionPane.showMessageDialog(null, "Produto não encontrado!");
} else {
    txtId.setText(String.valueOf(produto.getId()));
    txtNome.setText(produto.getNome());
    txtPreco.setText(String.valueOf(produto.getPreco()));
    txtQuantidade.setText(String.valueOf(produto.getQuantidade()));

    Categoria categoria = produto.getCategoria();

    for (int i = 0; i < cbCategoria.getItemCount(); i++) {
        if (cbCategoria.getItemAt(i).getId() == categoria.getId()) {
            cbCategoria.setSelectedIndex(i);
            break;
        }
    }
}
```

Evento Atualizar:

``` java
Produto produto = new Produto();

produto.setId(Integer.parseInt(txtId.getText()));
produto.setNome(txtNome.getText());
produto.setPreco(Double.parseDouble(txtPreco.getText()));
produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));

Categoria categoria = (Categoria) cbCategoria.getSelectedItem();
produto.setCategoria(categoria);

ProdutoDAO dao = new ProdutoDAO();
dao.editar(produto);

JOptionPane.showMessageDialog(null, "Produto atualizado!");

txtConsultaId.setText("");
txtId.setText("");
txtNome.setText("");
txtPreco.setText("");
txtQuantidade.setText("");

carregarCategorias();
```

Evento Excluir:

``` java
int id = Integer.parseInt(txtId.getText());

ProdutoDAO dao = new ProdutoDAO();
dao.excluir(id);

JOptionPane.showMessageDialog(null, "Produto excluído!");

txtConsultaId.setText("");
txtId.setText("");
txtNome.setText("");
txtPreco.setText("");
txtQuantidade.setText("");

carregarCategorias();
```

### 6.5 `MenuPrincipal`

Itens do menu:

-   Cadastros
    -   Cadastrar Categoria
    -   Cadastrar Produto
    -   Editar Produto
-   Consultas
    -   Listar Produtos

Eventos dos itens:

``` java
CadastroCategoria tela = new CadastroCategoria();
tela.setVisible(true);
```

``` java
CadastroProduto tela = new CadastroProduto();
tela.setVisible(true);
```

``` java
EditarProduto tela = new EditarProduto();
tela.setVisible(true);
```

``` java
ListarProdutos tela = new ListarProdutos();
tela.setVisible(true);
```

Como as classes estão no mesmo pacote `telas`, não é necessário importar
essas classes entre si.

Configuração de fechamento:

-   Telas secundárias: `DISPOSE`
-   `MenuPrincipal`: `EXIT_ON_CLOSE`

## 7. Conceitos importantes para a prova

### Bean

Representa os dados de uma tabela. Normalmente possui:

-   Atributos privados.
-   Getters e setters.
-   `toString()` quando precisa exibir o objeto em um componente como
    `JComboBox`.

### DAO

É a classe responsável por acessar o banco de dados.

-   `inserir()` --- INSERT
-   `get...()` --- SELECT
-   `editar()` --- UPDATE
-   `excluir()` --- DELETE
-   `buscar()` --- SELECT com filtro

### PreparedStatement

Permite montar consultas com parâmetros `?`.

Exemplo:

``` java
String sql = "SELECT * FROM categorias WHERE id = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setInt(1, id);
ResultSet rs = stmt.executeQuery();
```

### ResultSet

Armazena o resultado de uma consulta SELECT.

``` java
while (rs.next()) {
    // lê uma linha do resultado
}
```

Para verificar se encontrou um único registro:

``` java
if (rs.next()) {
    // encontrou
}
```

### executeQuery e execute

-   `executeQuery()` é usado para consultas que retornam resultados,
    como SELECT.
-   Nas aulas e no código deste projeto, `execute()` foi utilizado para
    INSERT, UPDATE e DELETE.

### JComboBox com objetos

Para trabalhar com objetos Categoria:

``` java
private javax.swing.JComboBox<Categoria> cbCategoria;
```

Adicionar objetos:

``` java
cbCategoria.addItem(categoria);
```

Recuperar objeto selecionado:

``` java
Categoria categoria = (Categoria) cbCategoria.getSelectedItem();
```

O `toString()` de Categoria retorna o nome, então o combo exibe o nome,
mas mantém o objeto completo, inclusive seu ID.

### INNER JOIN

Usado para trazer dados de tabelas relacionadas:

``` sql
SELECT p.*, c.nome AS categoria_nome
FROM produtos p
INNER JOIN categorias c ON p.categoria_id = c.id;
```

### LIKE

Pesquisa parcial de texto:

``` sql
WHERE p.nome LIKE ? OR c.nome LIKE ?
```

Parâmetro:

``` java
stmt.setString(1, "%" + pesquisa + "%");
stmt.setString(2, "%" + pesquisa + "%");
```

## 8. Erro de conexão encontrado

Mensagem:

``` text
No suitable driver found for jdbc:mysql://localhost:3306/BDAula01
```

Significado: o driver JDBC do MySQL não foi encontrado no projeto.

Para resolver em um ambiente com MySQL:

1.  Baixar o MySQL Connector/J.
2.  No NetBeans, clicar com o botão direito no projeto.
3.  Abrir Properties → Libraries.
4.  Usar Add JAR/Folder.
5.  Selecionar o arquivo `mysql-connector-j-...jar`.
6.  Confirmar que o serviço MySQL está ativo.
7.  Conferir banco, usuário, senha e porta na classe `Conexao`.

O `NullPointerException` que apareceu depois ocorreu porque a conexão
retornou `null`, e o DAO tentou executar `prepareStatement()` sem uma
conexão válida.

## 9. Checklist final

-   [ ] Banco `BDAula01` criado.
-   [ ] Tabelas `categorias` e `produtos` criadas.
-   [ ] Chave estrangeira `produtos.categoria_id` configurada.
-   [ ] Driver MySQL Connector/J adicionado ao projeto.
-   [ ] Dados de conexão conferidos.
-   [ ] Pacotes `beans`, `DAO`, `conexao` e `telas` criados.
-   [ ] Beans Categoria e Produto criados.
-   [ ] DAOs CategoriaDAO e ProdutoDAO criados.
-   [ ] Cadastro e edição de categorias.
-   [ ] Cadastro de produtos com seleção de categoria.
-   [ ] Listagem e pesquisa de produtos.
-   [ ] Consulta, edição e exclusão de produtos.
-   [ ] Menu principal abrindo todas as telas.
-   [ ] Telas secundárias configuradas com `DISPOSE`.

> **Importante para a prova:** confira os nomes dos componentes no
> NetBeans. Os nomes utilizados nos eventos precisam ser exatamente
> iguais aos nomes das variáveis dos componentes no formulário.
