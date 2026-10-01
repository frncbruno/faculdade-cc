import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class GerarNumeros {

    public static void main(String[] args) {

        Random random = new Random();

        try (PrintWriter arquivo =
                     new PrintWriter(new FileWriter("numeros.txt"))) {

            for (int i = 0; i < 100000; i++) {
                arquivo.println(random.nextInt(1000000));
            }

            System.out.println("Arquivo numeros.txt criado!");
            System.out.println("Quantidade de números: 100000");

        } catch (Exception e) {
            System.out.println("Erro ao gerar arquivo: " + e.getMessage());
        }
    }
}
