package com.mycompany.projetoconta;

/**
 *
 * @author laboratorio
 */
public class Main {
    public static void main(String[] args) {
        Casa casa = new Casa();

        System.out.println("Preço da casa (apenas tamanho): R$ " +
                casa.calcularPreco(100));

        System.out.println("Preço da casa (tamanho + quartos): R$ " +
                casa.calcularPreco(100, 3));
    }
}
