
package utilidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import model.ProdutoModel;

public class GerenciadorArquivos {

    // Carrega os produtos de um arquivo e retorna uma lista
    public static ArrayList<ProdutoModel> carregarArquivo(String nomeArquivo) {

        // Cria uma lista vazia para armazenar os produtos
        ArrayList<ProdutoModel> lista = new ArrayList<>();

        try {

            // Abre o arquivo para leitura
            FileReader fr = new FileReader(nomeArquivo);

            // Permite ler o arquivo linha por linha
            BufferedReader bf = new BufferedReader(fr);

            String linha;

            // Ignora a primeira linha, que contém o cabeçalho
            bf.readLine();

            // Continua lendo enquanto existirem linhas no arquivo
            while ((linha = bf.readLine()) != null) {

                // Separa os dados utilizando o ponto e vírgula
                String[] dados = linha.split(";");

                // Converte os dados do arquivo para seus respectivos tipos
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String categoria = dados[2];
                double preco = Double.parseDouble(dados[3]);
                int estoque = Integer.parseInt(dados[4]);

                // Cria um objeto com os dados do produto
                ProdutoModel p = new ProdutoModel(id, nome, categoria, preco, estoque);

                // Adiciona o produto na lista
                lista.add(p);
            }

            // Fecha o arquivo após terminar a leitura
            bf.close();

        } catch (Exception e) {

            // Exibe uma mensagem caso aconteça algum erro
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }

        // Retorna a lista de produtos carregados
        return lista;
    }

    // Exporta uma lista de produtos para um arquivo
    public static void exportarArquivo(ArrayList<ProdutoModel> lista, String nomeArquivo) {

        try {

            // Cria ou sobrescreve o arquivo de destino
            FileWriter fw = new FileWriter(nomeArquivo);

            // Escreve o cabeçalho do arquivo
            fw.write("id;nome;categoria;preco;estoque\n");

            // Percorre todos os produtos da lista
            for (int i = 0; i < lista.size(); i++) {

                // Obtém o produto da posição atual
                ProdutoModel p = lista.get(i);

                // Escreve os dados do produto separados por ponto e vírgula
                fw.write(
                    p.getId() + ";" +
                    p.getNome() + ";" +
                    p.getCategoria() + ";" +

                    // Formata o preço com duas casas decimais e ponto
                    String.format(java.util.Locale.US, "%.2f", p.getPreco()) + ";" +

                    p.getEstoque() + "\n"
                );
            }

            // Fecha o arquivo após terminar a escrita
            fw.close();

        } catch (Exception e) {

            // Exibe uma mensagem caso aconteça algum erro
            System.out.println("Erro ao exportar arquivo: " + e.getMessage());
        }
    }
}
