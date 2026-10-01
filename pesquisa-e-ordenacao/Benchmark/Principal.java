import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) {

        ArrayList<Integer> lista = new ArrayList<>();

        // Carrega os 100.000 números
        boolean carregou = Util.carregarArquivoEmLista("numeros.txt", lista);

        if (!carregou) {
            System.out.println("Erro ao carregar o arquivo.");
            return;
        }

        System.out.println("Total de elementos na lista: " + lista.size());

        // Ordenação pelo método da pente
        System.out.println("\nOrdenando com o método da pente...");

        long inicio = System.nanoTime();

        ArrayList<Float> metricas = Ordenacao.pente(lista);

        long fim = System.nanoTime();

        double tempo = (fim - inicio) / 1_000_000.0;

        System.out.println("Ordenação concluída!");
        System.out.println("Tempo: " + tempo + " ms");
        System.out.println("Comparações: " + metricas.get(0).longValue());
        System.out.println("Trocas: " + metricas.get(1).longValue());

        // Não exibir os 100.000 números
        // Util.exibirLista(lista);

        // Pesquisa
        String entrada = JOptionPane.showInputDialog(
                "Digite um número inteiro para pesquisar:"
        );

        if (entrada == null) {
            return;
        }

        int numeroPesquisa;

        try {
            numeroPesquisa = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Digite um número inteiro válido."
            );
            return;
        }

        // Pesquisa sequencial
        long inicioSequencial = System.nanoTime();

        boolean resultadoSequencial =
                Ordenacao.contido(numeroPesquisa, lista);

        long fimSequencial = System.nanoTime();

        double tempoSequencial =
                (fimSequencial - inicioSequencial) / 1_000_000.0;

        System.out.println("\n=== PESQUISA SEQUENCIAL ===");
        System.out.println("Encontrado: " + resultadoSequencial);
        System.out.println("Comparações: "
                + Ordenacao.getComparacoesSequencial());
        System.out.println("Tempo: " + tempoSequencial + " ms");

        // Pesquisa binária
        long inicioBinaria = System.nanoTime();

        boolean resultadoBinaria =
                Ordenacao.pesquisaBinaria(numeroPesquisa, lista);

        long fimBinaria = System.nanoTime();

        double tempoBinaria =
                (fimBinaria - inicioBinaria) / 1_000_000.0;

        System.out.println("\n=== PESQUISA BINÁRIA ===");
        System.out.println("Encontrado: " + resultadoBinaria);
        System.out.println("Comparações: "
                + Ordenacao.getComparacoesBinaria());
        System.out.println("Tempo: " + tempoBinaria + " ms");

        JOptionPane.showMessageDialog(
                null,
                "Pesquisa sequencial: " + resultadoSequencial
                        + "\nComparações: "
                        + Ordenacao.getComparacoesSequencial()
                        + "\n\nPesquisa binária: " + resultadoBinaria
                        + "\nComparações: "
                        + Ordenacao.getComparacoesBinaria()
        );
    }
}
