
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import model.ProdutoModel;

public class Buscador {

    // BUSCA LINEAR POR ID
    public static ProdutoModel buscaLinear(
            int id, ArrayList<ProdutoModel> lista) {

        // Percorre todos os produtos da lista
        for (int i = 0; i < lista.size(); i++) {

            // Verifica se o ID do produto atual corresponde ao ID procurado
            if (lista.get(i).getId() == id) {

                // Retorna o produto encontrado
                return lista.get(i);
            }
        }

        // Retorna null caso o produto nao seja encontrado
        return null;
    }

    // BUSCA BINARIA POR ID
    public static ProdutoModel buscaBinaria(
            int id, ArrayList<ProdutoModel> lista) {

        // Define o inicio e o final da lista
        int inicio = 0;
        int fim = lista.size() - 1;

        // Continua enquanto existir uma parte da lista para pesquisar
        while (inicio <= fim) {

            // Calcula a posicao central da lista
            int meio = inicio + (fim - inicio) / 2;

            // Verifica se o produto do meio possui o ID procurado
            if (lista.get(meio).getId() == id) {
                return lista.get(meio);
            }

            // Se o ID procurado for menor, pesquisa na metade esquerda
            if (id < lista.get(meio).getId()) {
                fim = meio - 1;
            } else {

                // Caso contrario, pesquisa na metade direita
                inicio = meio + 1;
            }
        }

        // Retorna null caso o produto nao seja encontrado
        return null;
    }

    // CRIAR HASHMAP POR ID
    public static HashMap<Integer, ProdutoModel> criarIndiceId(
            ArrayList<ProdutoModel> lista) {

        // Cria um HashMap que relaciona o ID ao objeto ProdutoModel
        HashMap<Integer, ProdutoModel> mapa = new HashMap<>();

        // Percorre todos os produtos da lista
        for (int i = 0; i < lista.size(); i++) {

            ProdutoModel p = lista.get(i);

            // Adiciona o produto utilizando seu ID como chave
            mapa.put(p.getId(), p);
        }

        // Retorna o HashMap criado
        return mapa;
    }

    // BUSCA POR ID NO HASHMAP
    public static ProdutoModel buscaHash(
            int id, HashMap<Integer, ProdutoModel> mapa) {

        // Retorna diretamente o produto associado ao ID informado
        return mapa.get(id);
    }

    // CRIAR HASHMAP POR CATEGORIA
    public static HashMap<String, ArrayList<ProdutoModel>> criarIndiceCategoria(
            ArrayList<ProdutoModel> lista) {

        // Cria um HashMap que relaciona categorias a listas de produtos
        HashMap<String, ArrayList<ProdutoModel>> mapa = new HashMap<>();

        // Percorre todos os produtos
        for (int i = 0; i < lista.size(); i++) {

            ProdutoModel p = lista.get(i);

            // Converte a categoria para letras minusculas
            String categoria = p.getCategoria().toLowerCase();

            // Verifica se a categoria ainda nao existe no HashMap
            if (!mapa.containsKey(categoria)) {

                // Cria uma nova lista para armazenar os produtos da categoria
                mapa.put(categoria, new ArrayList<ProdutoModel>());
            }

            // Adiciona o produto na lista correspondente a sua categoria
            mapa.get(categoria).add(p);
        }

        // Retorna o indice de categorias
        return mapa;
    }

    // BUSCAR PRODUTOS POR CATEGORIA
    public static ArrayList<ProdutoModel> buscaCategoria(
            String categoria,
            HashMap<String, ArrayList<ProdutoModel>> mapa) {

        // Retorna a lista de produtos correspondente a categoria informada
        return mapa.get(categoria.toLowerCase());
    }
}
