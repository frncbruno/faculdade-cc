package servidor.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import servidor.model.Sessao;

public class Utilidades {

    public static boolean carregarArquivoEmHashMap(String nomeArquivo, HashMap<String, Sessao> sessoes, StringBuilder dadosSessoes) {
        try {
            Path caminho = Paths.get(nomeArquivo);
            dadosSessoes.append(Files.readString(caminho));

            BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo));

            String linha;

            while ((linha = leitor.readLine()) != null) {
                String dadosLinha[] = linha.split(";", 2);

                Sessao sessao = new Sessao(dadosLinha[0], dadosLinha[1]);

                sessoes.put(sessao.numeroCelular, sessao);
            }

            leitor.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

}
