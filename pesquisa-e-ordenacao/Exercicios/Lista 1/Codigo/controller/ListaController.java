package controller;

import model.ListaModel;
import model.Utilidades;
import view.ListaView;

public class ListaController {

    private ListaModel model;
    private ListaView view;

    public ListaController() {
        model = new ListaModel();
        view = new ListaView();
    }

    public void executar() {

        long inicio;
        long fim;

        inicio = System.nanoTime();

        Utilidades.popularLista(
                model.getListaAleatoria(),
                100000,
                100,
                100000,
                true);

        fim = System.nanoTime();

        view.exibirTempo("Rotina 1", (fim - inicio) / 1000000);

        inicio = System.nanoTime();

        Utilidades.popularLista(
                model.getListaSequencial(),
                100000,
                1,
                100000,
                false);

        fim = System.nanoTime();

        view.exibirTempo("Rotina 2", (fim - inicio) / 1000000);

        // Caso queira mostrar as listas:
        // view.exibirLista(model.getListaAleatoria(), "Lista Aleatória");
        // view.exibirLista(model.getListaSequencial(), "Lista Sequencial");
    }
}
