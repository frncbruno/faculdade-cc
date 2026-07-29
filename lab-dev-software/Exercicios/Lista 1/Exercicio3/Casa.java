package com.mycompany.projetoconta;

/**
 *
 * @author laboratorio
 */
public class Casa {
    // Método que calcula o preço apenas pelo tamanho
    public double calcularPreco(int tamanho) {
        return tamanho * 2000; // R$ 2.000 por metro quadrado
    }

    // Método sobrecarregado que calcula o preço pelo tamanho e número de quartos
    public double calcularPreco(int tamanho, int quartos) {
        return (tamanho * 2000) + (quartos * 15000);
    }
}
