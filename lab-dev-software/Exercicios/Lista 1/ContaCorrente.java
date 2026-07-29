package com.mycompany.projetoconta;

/**
 *
 * @author laboratorio
 */
public class ContaCorrente {
    private float saldo;

    public ContaCorrente(float saldo) {
        this.saldo = saldo;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    public void definirSaldoInicial(float valor){
        saldo = valor;
    }
    
    public void depositar(float valor){
        saldo = saldo + valor;
    }
    
    public boolean sacar(float valor) {
    if (saldo >= valor) {
        saldo -= valor;
        return true;
        }
    return false;
    }
}
