package view;

import java.util.List;

public class ListaView {

    public void exibirLista(List<Integer> lista, String titulo) {
        System.out.println(titulo);

        for (Integer numero : lista) {
            System.out.println(numero);
        }

        System.out.println("---------------------");
        System.out.println("Total: " + lista.size());
    }

    public void exibirTempo(String rotina, long tempo) {
        System.out.println("Tempo (ms) " + rotina + ": " + tempo);
    }
}
