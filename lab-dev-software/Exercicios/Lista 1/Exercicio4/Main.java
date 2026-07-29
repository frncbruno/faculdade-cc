package Projeto;

/**
 *
 * @author laboratorio
 */
public class Main {
    public static void main(String[] args) {

        MetodoPagamento cartao = new CartaoCreditoPagamento();
        MetodoPagamento paypal = new PayPalPagamento();
        MetodoPagamento pix = new PIXPagamento();

        cartao.processaPagamento(250.00);
        cartao.mostraDetalhesPagamento();

        System.out.println();

        paypal.processaPagamento(120.50);
        paypal.mostraDetalhesPagamento();

        System.out.println();

        pix.processaPagamento(89.90);
        pix.mostraDetalhesPagamento();
    }
}
