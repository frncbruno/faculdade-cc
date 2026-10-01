import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Util {

    public static boolean carregarArquivoEmLista(
            String nomeArquivo,
            ArrayList<Integer> lista) {

        try {

            FileReader procurador =
                    new FileReader(nomeArquivo);

            BufferedReader leitor =
                    new BufferedReader(procurador);

            String linha;

            while ((linha = leitor.readLine()) != null) {

                linha = linha.trim();

                if (!linha.isEmpty()) {
                    lista.add(Integer.parseInt(linha));
                }
            }

            leitor.close();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Erro ao carregar arquivo: "
                            + e.getMessage()
            );

            return false;
        }
    }

    public static void exibirLista(
            ArrayList<Integer> lista) {

        for (Integer item : lista) {
            System.out.println(item);
        }
    }
}
