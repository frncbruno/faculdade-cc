package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrdenacaoModel {

    private List<Integer> listaOriginal;

    public OrdenacaoModel(int quantidade) {

        listaOriginal = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < quantidade; i++) {
            listaOriginal.add(
                    random.nextInt(100000));
        }
    }

    public List<Integer> getListaOriginal() {
        return listaOriginal;
    }

    public ResultadoOrdenacao sortNativo() {

        List<Integer> lista =
                new ArrayList<>(listaOriginal);

        long inicio = System.nanoTime();

        lista.sort(Integer::compareTo);

        long fim = System.nanoTime();

        long tempo =
                (fim - inicio) / 1_000_000;

        return new ResultadoOrdenacao(
                "Sort nativo",
                tempo,
                0,
                0
        );
    }

    public ResultadoOrdenacao bolha() {

        List<Integer> lista =
                new ArrayList<>(listaOriginal);

        long comparacoes = 0;
        long trocas = 0;

        long inicio = System.nanoTime();

        boolean houveTroca;

        do {

            houveTroca = false;

            for (int i = 0;
                 i < lista.size() - 1;
                 i++) {

                comparacoes++;

                if (lista.get(i) >
                    lista.get(i + 1)) {

                    int tmp = lista.get(i);

                    lista.set(
                            i,
                            lista.get(i + 1));

                    lista.set(
                            i + 1,
                            tmp);

                    trocas++;

                    houveTroca = true;
                }
            }

        } while (houveTroca);

        long fim = System.nanoTime();

        long tempo =
                (fim - inicio) / 1_000_000;

        return new ResultadoOrdenacao(
                "Bubble Sort",
                tempo,
                comparacoes,
                trocas
        );
    }

    public ResultadoOrdenacao selecao() {

        List<Integer> lista =
                new ArrayList<>(listaOriginal);

        long comparacoes = 0;
        long trocas = 0;

        long inicio = System.nanoTime();

        for (int i = 0;
             i < lista.size() - 1;
             i++) {

            int posMenor = i;

            for (int j = i + 1;
                 j < lista.size();
                 j++) {

                comparacoes++;

                if (lista.get(j) <
                    lista.get(posMenor)) {

                    posMenor = j;
                }
            }

            if (i != posMenor) {

                int tmp = lista.get(i);

                lista.set(
                        i,
                        lista.get(posMenor));

                lista.set(
                        posMenor,
                        tmp);

                trocas++;
            }
        }

        long fim = System.nanoTime();

        long tempo =
                (fim - inicio) / 1_000_000;

        return new ResultadoOrdenacao(
                "Selection Sort",
                tempo,
                comparacoes,
                trocas
        );
    }

    public ResultadoOrdenacao insercao() {

        List<Integer> lista =
                new ArrayList<>(listaOriginal);

        long comparacoes = 0;
        long trocas = 0;

        long inicio = System.nanoTime();

        for (int i = 1;
             i < lista.size();
             i++) {

            int tmp = lista.get(i);

            int j = i - 1;

            while (j >= 0) {

                comparacoes++;

                if (tmp < lista.get(j)) {

                    lista.set(
                            j + 1,
                            lista.get(j));

                    trocas++;

                    j--;

                } else {

                    break;
                }
            }

            lista.set(j + 1, tmp);

            trocas++;
        }

        long fim = System.nanoTime();

        long tempo =
                (fim - inicio) / 1_000_000;

        return new ResultadoOrdenacao(
                "Insertion Sort",
                tempo,
                comparacoes,
                trocas
        );
    }
}