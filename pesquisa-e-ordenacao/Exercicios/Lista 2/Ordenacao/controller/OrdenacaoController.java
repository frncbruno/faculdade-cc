package controller;

import model.OrdenacaoModel;
import model.ResultadoOrdenacao;
import view.OrdenacaoView;

public class OrdenacaoController {

    private OrdenacaoView view;

    public OrdenacaoController(
            OrdenacaoView view) {

        this.view = view;
    }

    public void executar() {

        view.exibirTitulo();

        int quantidade =
                view.solicitarQuantidade();

        OrdenacaoModel model =
                new OrdenacaoModel(quantidade);

        // Sort nativo
        ResultadoOrdenacao resultado =
                model.sortNativo();

        view.exibirResultado(resultado);

        // Bubble Sort
        resultado = model.bolha();

        view.exibirResultado(resultado);

        // Selection Sort
        resultado = model.selecao();

        view.exibirResultado(resultado);

        // Insertion Sort
        resultado = model.insercao();

        view.exibirResultado(resultado);
    }
}