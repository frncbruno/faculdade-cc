
package com.mycompany.projeto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class MenuPrincipal extends JFrame {

    // Componentes da janela
    private JTextArea txtAreaTexto;
    private JTextField txtPesquisa;
    private JTextField txtOcorrencia;
    private JButton btnLimpar;

    public MenuPrincipal() {

        // Configura a janela
        setTitle("Pesquisa de Texto");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Cria os componentes
        txtAreaTexto = new JTextArea();
        txtPesquisa = new JTextField(15);
        txtOcorrencia = new JTextField(10);
        btnLimpar = new JButton("Limpar");

        // Impede a edição do contador
        txtOcorrencia.setEditable(false);

        // Adiciona a área de texto com barra de rolagem
        add(new JScrollPane(txtAreaTexto), BorderLayout.CENTER);

        // Cria o painel dos campos
        JPanel painel = new JPanel(new GridLayout(3, 2, 5, 5));

        painel.add(new JLabel("Pesquisa:"));
        painel.add(txtPesquisa);

        painel.add(new JLabel("Ocorrências:"));
        painel.add(txtOcorrencia);

        painel.add(btnLimpar);

        // Adiciona o painel na parte inferior
        add(painel, BorderLayout.SOUTH);

        // Pesquisa quando o usuário digita
        txtPesquisa.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                pesquisar();
            }
        });

        // Atualiza a pesquisa quando o texto muda
        txtAreaTexto.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                pesquisar();
            }
        });

        // Limpa os campos ao clicar no botão
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtAreaTexto.setText("");
                txtPesquisa.setText("");
                txtOcorrencia.setText("");
                txtAreaTexto.getHighlighter().removeAllHighlights();
            }
        });
    }

    // Procura a palavra no texto
    private void pesquisar() {

        String texto = txtAreaTexto.getText().toLowerCase();
        String palavra = txtPesquisa.getText().toLowerCase();

        // Remove os destaques anteriores
        Highlighter marca = txtAreaTexto.getHighlighter();
        marca.removeAllHighlights();

        int contador = 0;
        int posicao = 0;

        // Procura todas as ocorrências
        if (!palavra.isEmpty()) {

            while ((posicao = texto.indexOf(palavra, posicao)) != -1) {

                try {
                    // Destaca a palavra encontrada em amarelo
                    marca.addHighlight(
                        posicao,
                        posicao + palavra.length(),
                        new DefaultHighlighter.DefaultHighlightPainter(
                            Color.YELLOW
                        )
                    );

                    contador++;

                } catch (Exception erro) {
                    // Trata um possível erro no destaque
                }

                // Continua a pesquisa após a palavra encontrada
                posicao += palavra.length();
            }
        }

        // Mostra a quantidade encontrada
        txtOcorrencia.setText(String.valueOf(contador));
    }
}
