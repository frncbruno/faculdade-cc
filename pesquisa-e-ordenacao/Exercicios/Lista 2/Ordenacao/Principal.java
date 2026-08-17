import controller.OrdenacaoController;
import view.OrdenacaoView;

public class Principal {

    public static void main(String[] args) {

        OrdenacaoView view =
                new OrdenacaoView();

        OrdenacaoController controller =
                new OrdenacaoController(view);

        controller.executar();
    }
}