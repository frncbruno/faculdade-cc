package model;

import java.util.ArrayList;
import java.util.List;

public class ListaModel {

    private List<Integer> listaAleatoria;
    private List<Integer> listaSequencial;

    public ListaModel() {
        listaAleatoria = new ArrayList<>();
        listaSequencial = new ArrayList<>();
    }

    public List<Integer> getListaAleatoria() {
        return listaAleatoria;
    }

    public List<Integer> getListaSequencial() {
        return listaSequencial;
    }
}
