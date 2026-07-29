package Projeto;

/**
 *
 * @author laboratorio
 */
import java.util.Random;

abstract class MetodoPagamento {
    protected String nomeMetodo;
    protected int idPagamento;

    public MetodoPagamento(String nomeMetodo) {
        this.nomeMetodo = nomeMetodo;
        this.idPagamento = new Random().nextInt(100000);
    }

    public abstract void processaPagamento(double valor);

    public abstract void mostraDetalhesPagamento();
}
