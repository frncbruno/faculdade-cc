
package controller;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import model.ProdutoModel;
import utilidades.GerenciadorArquivos;

public class Relatorio {

    // Metodo responsavel por gerar o relatorio de desempenho
    public static void gerarRelatorio() {

        // Lista com os tres arquivos utilizados nos testes
        String[] arquivos = {
            "produtos_1000.txt",
            "produtos_10000.txt",
            "produtos_50000.txt"
        };

        try {

            // Cria o arquivo onde os resultados serao escritos
            FileWriter fw = new FileWriter("relatorio.txt");

            fw.write("RELATORIO DE DESEMPENHO\n\n");

            // Percorre os tres arquivos para realizar os testes
            for (int i = 0; i < arquivos.length; i++) {

                // Carrega os produtos do arquivo atual
                ArrayList<ProdutoModel> lista =
                        GerenciadorArquivos.carregarArquivo(arquivos[i]);

                // Identifica qual arquivo esta sendo testado
                fw.write("Arquivo: " + arquivos[i] + "\n");

                // Verifica se o arquivo foi carregado corretamente
                if (lista.isEmpty()) {
                    fw.write("Erro ao carregar arquivo.\n\n");
                    continue;
                }

                // Cria um comparador para ordenar os produtos pelo preco
                Comparator<ProdutoModel> porPreco =
                        (a, b) -> Double.compare(a.getPreco(), b.getPreco());

                // BUBBLE SORT

                // Cria uma copia da lista original para realizar a ordenacao
                ArrayList<ProdutoModel> bolha = new ArrayList<>(lista);

                // Registra o inicio da execucao
                long inicio = System.nanoTime();

                // Executa o Bubble Sort com limite de dois minutos
                boolean terminou = Ordenador.bolha(
                        bolha, porPreco, 120_000_000_000L);

                // Registra o fim da execucao
                long fim = System.nanoTime();

                // Verifica se a ordenacao foi concluida
                if (terminou) {
                    fw.write("Bubble Sort: " + (fim - inicio) / 1000000.0 + " ms\n");
                } else {
                    fw.write("Bubble Sort: Nao concluido (> 2 min)\n");
                }

                // QUICK SORT

                // Cria outra copia da lista original
                ArrayList<ProdutoModel> quick = new ArrayList<>(lista);

                inicio = System.nanoTime();

                // Executa o Quick Sort
                Ordenador.quickSort(quick, porPreco);

                fim = System.nanoTime();

                // Registra o tempo gasto na ordenacao
                fw.write("Quick Sort: " + (fim - inicio) / 1000000.0 + " ms\n");

                // BUSCAS

                // Cria uma copia da lista para ordenar pelo ID
                ArrayList<ProdutoModel> listaID = new ArrayList<>(lista);

                // Cria um comparador para ordenar os produtos pelo ID
                Comparator<ProdutoModel> porID =
                        (a, b) -> Integer.compare(a.getId(), b.getId());

                // Ordena a lista pelo ID para permitir a busca binaria
                Ordenador.quickSort(listaID, porID);

                // Escolhe o ID do primeiro produto para realizar as buscas
                int id = lista.get(0).getId();

                // BUSCA LINEAR

                inicio = System.nanoTime();

                // Executa a busca linear mil vezes
                for (int j = 0; j < 1000; j++) {
                    Buscador.buscaLinear(id, lista);
                }

                fim = System.nanoTime();

                // Calcula e registra a media de tempo de cada busca
                fw.write("Busca Linear: " + (fim - inicio) / 1000.0 / 1000000.0 + " ms\n");

                // BUSCA BINARIA

                inicio = System.nanoTime();

                // Executa a busca binaria mil vezes
                for (int j = 0; j < 1000; j++) {
                    Buscador.buscaBinaria(id, listaID);
                }

                fim = System.nanoTime();

                // Calcula e registra a media de tempo de cada busca
                fw.write("Busca Binaria: " + (fim - inicio) / 1000.0 / 1000000.0 + " ms\n");

                // CRIACAO DO HASHMAP

                inicio = System.nanoTime();

                // Cria o indice HashMap utilizando os IDs dos produtos
                HashMap<Integer, ProdutoModel> mapa =
                        Buscador.criarIndiceId(lista);

                fim = System.nanoTime();

                // Registra o tempo necessario para construir o HashMap
                fw.write("Criacao HashMap: " + (fim - inicio) / 1000000.0 + " ms\n");

                // BUSCA HASHMAP

                inicio = System.nanoTime();

                // Executa a busca no HashMap mil vezes
                for (int j = 0; j < 1000; j++) {
                    Buscador.buscaHash(id, mapa);
                }

                fim = System.nanoTime();

                // Calcula e registra a media de tempo de cada busca
                fw.write("Busca HashMap: " + (fim - inicio) / 1000.0 / 1000000.0 + " ms\n\n");
            }

            // Escreve as conclusoes gerais dos testes
            fw.write("RESUMO:\n");
            fw.write("HashMap costuma ser mais rapido em buscas repetidas.\n");
            fw.write("Busca binaria exige que a lista esteja ordenada por ID.\n");
            fw.write("HashMap compensa quando existem muitas consultas.\n");

            // Fecha o arquivo apos terminar a escrita
            fw.close();

            System.out.println("Relatorio gerado!");

        } catch (Exception e) {

            // Exibe uma mensagem caso ocorra algum erro
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
