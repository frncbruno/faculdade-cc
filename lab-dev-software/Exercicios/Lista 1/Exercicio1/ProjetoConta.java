package com.mycompany.projetoconta;

/**
 *
 * @author laboratorio
 */
public class ProjetoConta {

    public static void main(String[] args) {
        ContaCorrente novaConta = new ContaCorrente();
        
        novaConta.definirSaldoInicial(1000);
        if(novaConta.sacar(500) == true){
            System.out.println("Saldo: "+novaConta.getSaldo());
        } else {
            System.out.println("Saldo não efetuado");
        }
        
        novaConta.depositar(500);
        System.out.println("Saldo: "+novaConta.getSaldo());
        if(novaConta.sacar(600)){
            System.out.println("Saque efetuado");
        } else {
            System.out.println("Saque não efetuado");
        }
    }
}
