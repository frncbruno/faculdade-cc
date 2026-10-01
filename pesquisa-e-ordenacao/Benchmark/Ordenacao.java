import java.util.ArrayList;

public class Ordenacao {

    private static long comparacoesSequencial = 0;
    private static long comparacoesBinaria = 0;

    // =========================================================
    // PESQUISA SEQUENCIAL
    // =========================================================

    public static boolean contido(int numero, ArrayList<Integer> lista) {

        comparacoesSequencial = 0;

        for (Integer item : lista) {

            comparacoesSequencial++;

            if (item == numero) {
                return true;
            }
        }

        return false;
    }

    // =========================================================
    // PESQUISA BINÁRIA
    // =========================================================

    public static boolean pesquisaBinaria(
            int numero,
            ArrayList<Integer> lista) {

        comparacoesBinaria = 0;

        int ini = 0;
        int fim = lista.size() - 1;

        while (ini <= fim) {

            int meio = ini + (fim - ini) / 2;

            comparacoesBinaria++;

            if (numero == lista.get(meio)) {
                return true;
            }

            comparacoesBinaria++;

            if (numero < lista.get(meio)) {
                fim = meio - 1;
            } else {
                ini = meio + 1;
            }
        }

        return false;
    }

    public static long getComparacoesSequencial() {
        return comparacoesSequencial;
    }

    public static long getComparacoesBinaria() {
        return comparacoesBinaria;
    }

    // =========================================================
    // BOLHA
    // =========================================================

    public static ArrayList<Float> bolha(ArrayList<Integer> lista) {

        ArrayList<Float> metricas = new ArrayList<>();

        long qtdComparacoes = 0;
        long qtdTrocas = 0;

        boolean houveTroca;

        do {

            houveTroca = false;

            for (int i = 0; i < lista.size() - 1; i++) {

                qtdComparacoes++;

                if (lista.get(i) > lista.get(i + 1)) {

                    int aux = lista.get(i);

                    lista.set(i, lista.get(i + 1));
                    lista.set(i + 1, aux);

                    qtdTrocas++;
                    houveTroca = true;
                }
            }

        } while (houveTroca);

        metricas.add((float) qtdComparacoes);
        metricas.add((float) qtdTrocas);

        return metricas;
    }

    // =========================================================
    // SELEÇÃO
    // =========================================================

    public static ArrayList<Float> selecao(ArrayList<Integer> lista) {

        ArrayList<Float> metricas = new ArrayList<>();

        long qtdComparacoes = 0;
        long qtdTrocas = 0;

        for (int i = 0; i < lista.size(); i++) {

            int posMenor = i;

            for (int j = i + 1; j < lista.size(); j++) {

                qtdComparacoes++;

                if (lista.get(j) < lista.get(posMenor)) {
                    posMenor = j;
                }
            }

            if (posMenor != i) {

                int aux = lista.get(i);

                lista.set(i, lista.get(posMenor));
                lista.set(posMenor, aux);

                qtdTrocas++;
            }
        }

        metricas.add((float) qtdComparacoes);
        metricas.add((float) qtdTrocas);

        return metricas;
    }

    // =========================================================
    // INSERÇÃO
    // =========================================================

    public static ArrayList<Float> insercao(ArrayList<Integer> lista) {

        ArrayList<Float> metricas = new ArrayList<>();

        long qtdComparacoes = 0;
        long qtdTrocas = 0;

        for (int i = 1; i < lista.size(); i++) {

            int aux = lista.get(i);

            int j = i - 1;

            while (j >= 0) {

                qtdComparacoes++;

                if (aux >= lista.get(j)) {
                    break;
                }

                lista.set(j + 1, lista.get(j));

                qtdTrocas++;

                j--;
            }

            lista.set(j + 1, aux);
        }

        metricas.add((float) qtdComparacoes);
        metricas.add((float) qtdTrocas);

        return metricas;
    }

    // =========================================================
    // PENTE
    // =========================================================

    public static ArrayList<Float> pente(ArrayList<Integer> lista) {

        ArrayList<Float> metricas = new ArrayList<>();

        long qtdComparacoes = 0;
        long qtdTrocas = 0;

        int distancia = lista.size();

        boolean houveTroca;

        do {

            distancia = (int) (distancia / 1.3);

            if (distancia <= 0) {
                distancia = 1;
            }

            houveTroca = false;

            for (int i = 0;
                 i + distancia < lista.size();
                 i++) {

                qtdComparacoes++;

                if (lista.get(i) > lista.get(i + distancia)) {

                    int aux = lista.get(i);

                    lista.set(i, lista.get(i + distancia));
                    lista.set(i + distancia, aux);

                    qtdTrocas++;

                    houveTroca = true;
                }
            }

        } while (distancia > 1 || houveTroca);

        metricas.add((float) qtdComparacoes);
        metricas.add((float) qtdTrocas);

        return metricas;
    }
}
