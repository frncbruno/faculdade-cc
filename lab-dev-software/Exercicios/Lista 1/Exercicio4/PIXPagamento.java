package Projeto;

/**
 *
 * @author laboratorio
 */
class PIXPagamento extends MetodoPagamento {

    public PIXPagamento() {
        super("PIX");
    }

    @Override
    public void processaPagamento(double valor) {
        System.out.println("Pagamento de R$ " + valor +
                " processado com " + nomeMetodo + ".");
    }

    @Override
    public void mostraDetalhesPagamento() {
        System.out.println("Método: " + nomeMetodo);
        System.out.println("ID do Pagamento: " + idPagamento);
    }
}
