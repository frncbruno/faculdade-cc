
package model;

// Classe que representa um produto
public class ProdutoModel {

    // Atributos do produto
    private int id;
    private String nome;
    private String categoria;
    private double preco;
    private int estoque;

    // Construtor do produtomodel - recebe os dados e inicializa os atributos
    public ProdutoModel(int id, String nome, String categoria, double preco, int estoque) {

        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;
    }

    // Retorna o ID do produto
    public int getId() {
        return id;
    }

    // Retorna o nome do produto
    public String getNome() {
        return nome;
    }

    // Retorna a categoria do produto
    public String getCategoria() {
        return categoria;
    }

    // Retorna o preço do produto
    public double getPreco() {
        return preco;
    }

    // Retorna a quantidade em estoque
    public int getEstoque() {
        return estoque;
    }
}
