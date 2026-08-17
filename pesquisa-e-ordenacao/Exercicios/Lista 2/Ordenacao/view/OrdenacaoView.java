package view;

import model.ResultadoOrdenacao;

import java.util.Scanner;

public class OrdenacaoView {

    private Scanner scanner;

    public OrdenacaoView() {
        scanner = new Scanner(System.in);
    }

    public int solicitarQuantidade() {

        System.out.print(
                "Quantos números deseja trabalhar? ");

        return scanner.nextInt();
    }

    public void exibirResultado(
            ResultadoOrdenacao resultado) {

        System.out.println();

        System.out.println(
                "===== " +
                resultado.getAlgoritmo() +
                " =====");

        System.out.println(
                "Tempo: " +
                resultado.getTempo() +
                " ms");

        System.out.println(
                "Comparações: " +
                resultado.getComparacoes());

        System.out.println(
                "Trocas: " +
                resultado.getTrocas());
    }

    public void exibirTitulo() {

        System.out.println(
                "====================================");

        System.out.println(
                "       TESTE DE ORDENAÇÃO");

        System.out.println(
                "====================================");
    }
}