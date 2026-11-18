
package controller;

import java.util.ArrayList;
import java.util.Comparator;
import model.ProdutoModel;

public class Ordenador {

    // ALGORITMO BUBBLE SORT (ORDENACAO BOLHA)
    public static boolean bolha(ArrayList<ProdutoModel> lista,
            Comparator<ProdutoModel> comparador, long limiteNanos) {

        // Guarda o instante em que a ordenacao comeca
        long inicio = System.nanoTime();

        boolean houveTroca;

        // Repete o processo enquanto houver trocas
        do {
            houveTroca = false;

            // Percorre a lista comparando os produtos vizinhos
            for (int i = 0; i < lista.size() - 1; i++) {

                // Verifica se o produto atual deve vir depois do proximo
                if (comparador.compare(lista.get(i), lista.get(i + 1)) > 0) {

                    // Realiza a troca de posicao dos produtos
                    ProdutoModel aux = lista.get(i);
                    lista.set(i, lista.get(i + 1));
                    lista.set(i + 1, aux);

                    // Indica que aconteceu uma troca
                    houveTroca = true;
                }

                // Verifica se o limite de tempo foi atingido
                if (System.nanoTime() - inicio >= limiteNanos) {
                    return false;
                }
            }

        // Se nao houve nenhuma troca, a lista esta ordenada
        } while (houveTroca);

        // Retorna true quando a ordenacao termina
        return true;
    }

    // ALGORITMO QUICK SORT
    public static void quickSort(ArrayList<ProdutoModel> lista,
            Comparator<ProdutoModel> comparador) {

        // Chama o metodo auxiliar com os limites da lista
        quickSort(lista, 0, lista.size() - 1, comparador);
    }

    // Metodo recursivo que realiza a ordenacao
    private static void quickSort(ArrayList<ProdutoModel> lista,
            int inicio,
            int fim,
            Comparator<ProdutoModel> comparador) {

        // Verifica se ainda existe uma parte da lista para ordenar
        if (inicio < fim) {

            // Organiza os elementos em relacao ao pivo
            int pivo = particionar(lista, inicio, fim, comparador);

            // Ordena os elementos que ficaram antes do pivo
            quickSort(lista, inicio, pivo - 1, comparador);

            // Ordena os elementos que ficaram depois do pivo
            quickSort(lista, pivo + 1, fim, comparador);
        }
    }

    // METODO QUE SEPARA OS ELEMENTOS EM RELACAO AO PIVO
    private static int particionar(ArrayList<ProdutoModel> lista,
            int inicio,
            int fim,
            Comparator<ProdutoModel> comparador) {

        // Escolhe o ultimo elemento como pivo
        ProdutoModel pivo = lista.get(fim);

        // Indice que controla a posicao dos elementos menores ou iguais ao pivo
        int i = inicio - 1;

        // Percorre os elementos ate a posicao anterior ao pivo
        for (int j = inicio; j < fim; j++) {

            // Verifica se o elemento atual deve ficar antes do pivo
            if (comparador.compare(lista.get(j), pivo) <= 0) {

                i++;

                // Troca os elementos de posicao
                ProdutoModel aux = lista.get(i);

                lista.set(i, lista.get(j));
                lista.set(j, aux);
            }
        }

        // Coloca o pivo na sua posicao correta
        ProdutoModel aux = lista.get(i + 1);

        lista.set(i + 1, lista.get(fim));
        lista.set(fim, aux);

        // Retorna a posicao final do pivo
        return i + 1;
    }
}
