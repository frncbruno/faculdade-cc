package model;

public class ResultadoOrdenacao {

    private String algoritmo;
    private long tempo;
    private long comparacoes;
    private long trocas;

    public ResultadoOrdenacao(
            String algoritmo,
            long tempo,
            long comparacoes,
            long trocas) {

        this.algoritmo = algoritmo;
        this.tempo = tempo;
        this.comparacoes = comparacoes;
        this.trocas = trocas;
    }

    public String getAlgoritmo() {
        return algoritmo;
    }

    public long getTempo() {
        return tempo;
    }

    public long getComparacoes() {
        return comparacoes;
    }

    public long getTrocas() {
        return trocas;
    }
}