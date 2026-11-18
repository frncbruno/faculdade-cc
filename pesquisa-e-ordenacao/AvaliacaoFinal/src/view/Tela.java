/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.ProdutoModel;
import utilidades.GerenciadorArquivos;
import java.util.Comparator;
import controller.Ordenador;
import java.util.HashMap;
import controller.Buscador;
import controller.Relatorio;

public class Tela extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Tela.class.getName());

    public Tela() {
        // Inicializa os componentes criados pelo Design do NetBeans
        initComponents();

        // Centraliza a janela na tela
        setLocationRelativeTo(null);
    }

    // Lista que armazena os produtos carregados
    ArrayList<ProdutoModel> produtos = new ArrayList<>();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        botaoCarregar = new javax.swing.JButton();
        comboArquivo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboCampo = new javax.swing.JComboBox<>();
        comboAlgoritmo = new javax.swing.JComboBox<>();
        botaoOrdenar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        comboBusca = new javax.swing.JComboBox<>();
        campoBusca = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();
        botaoExportar = new javax.swing.JButton();
        botaoRelatorio = new javax.swing.JButton();
        labelTempo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Carregar Produtos");

        botaoCarregar.setText("Carregar");
        botaoCarregar.addActionListener(this::botaoCarregarActionPerformed);

        comboArquivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "produtos_1000.txt", "produtos_10000.txt", "produtos_50000.txt" }));

        jLabel3.setText("Campo Ordenação");

        jLabel4.setText("Nome Algoritmo");

        jLabel5.setText("Busca Técnica");

        comboCampo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Preço" }));

        comboAlgoritmo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bubble Sort", "Quick Sort" }));

        botaoOrdenar.setText("Ordenar");
        botaoOrdenar.addActionListener(this::botaoOrdenarActionPerformed);

        jLabel6.setText("Busca ID/Categoria");

        comboBusca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Linear", "Binária", "HashMap" }));

        botaoBuscar.setText("Buscar");
        botaoBuscar.addActionListener(this::botaoBuscarActionPerformed);

        tabelaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Categoria", "Preço", "Estoque"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaProdutos);

        botaoExportar.setText("Exportar");
        botaoExportar.addActionListener(this::botaoExportarActionPerformed);

        botaoRelatorio.setText("Gerar Relatório");
        botaoRelatorio.addActionListener(this::botaoRelatorioActionPerformed);

        labelTempo.setText("Tempo da operação: aguardando");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)))
                            .addComponent(comboArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboAlgoritmo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(botaoOrdenar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoCarregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTempo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoExportar)
                        .addGap(18, 18, 18)
                        .addComponent(botaoRelatorio))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(botaoCarregar)
                    .addComponent(comboArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(comboAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoOrdenar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(comboBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoExportar)
                    .addComponent(botaoRelatorio)
                    .addComponent(labelTempo))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCarregarActionPerformed

        // Obtém o nome do arquivo selecionado
        String arquivo = comboArquivo.getSelectedItem().toString();

        // Carrega os produtos do arquivo para a lista
        produtos = GerenciadorArquivos.carregarArquivo(arquivo);

        // Obtém o modelo da tabela para permitir adicionar linhas
        DefaultTableModel modelo
                = (DefaultTableModel) tabelaProdutos.getModel();

        // Limpa os dados anteriores da tabela
        modelo.setRowCount(0);

        // Percorre todos os produtos carregados
        for (int i = 0; i < produtos.size(); i++) {

            ProdutoModel p = produtos.get(i);

            // Adiciona os dados do produto em uma nova linha da tabela
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getCategoria(),
                p.getPreco(),
                p.getEstoque()
            });
        }

        // Exibe a quantidade de produtos carregados
        labelTempo.setText(produtos.size() + " produtos carregados");

    }//GEN-LAST:event_botaoCarregarActionPerformed

    private void botaoOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoOrdenarActionPerformed

        // Verifica se existem produtos carregados
        if (produtos.isEmpty()) {
            labelTempo.setText("Carregue os produtos primeiro!");
            return;
        }

        // Variavel que define como os produtos serao comparados
        Comparator<ProdutoModel> comparador;

        // Verifica se a ordenacao sera pelo nome
        if (comboCampo.getSelectedItem().toString().equals("Nome")) {

            // Compara os nomes ignorando maiusculas e minusculas
            comparador = (p1, p2)
                    -> p1.getNome().compareToIgnoreCase(p2.getNome());

        } else {

            // Compara os precos numericamente
            comparador = (p1, p2)
                    -> Double.compare(p1.getPreco(), p2.getPreco());
        }

        // Registra o inicio da ordenacao
        long inicio = System.nanoTime();

        // Variavel que indica se a ordenacao terminou
        boolean terminou = true;

        // Verifica qual algoritmo foi selecionado
        if (comboAlgoritmo.getSelectedItem().toString().equals("Bubble Sort")) {

            // Executa o Bubble Sort com limite de dois minutos
            terminou = Ordenador.bolha(
                    produtos, comparador, 120_000_000_000L);

        } else {

            // Executa o Quick Sort
            Ordenador.quickSort(produtos, comparador);
        }

        // Registra o fim da ordenacao
        long fim = System.nanoTime();

        // Obtém o modelo da tabela
        DefaultTableModel modelo
                = (DefaultTableModel) tabelaProdutos.getModel();

        // Limpa a tabela antes de atualizar
        modelo.setRowCount(0);

        // Percorre a lista ja ordenada
        for (int i = 0; i < produtos.size(); i++) {

            ProdutoModel p = produtos.get(i);

            // Atualiza a tabela com os produtos ordenados
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getCategoria(),
                p.getPreco(),
                p.getEstoque()
            });
        }

        // Verifica se a ordenacao foi concluida
        if (terminou) {

            // Converte o tempo para milissegundos
            double tempo = (fim - inicio) / 1000000.0;

            // Exibe o tempo gasto
            labelTempo.setText("Ordenado em " + tempo + " ms");

        } else {

            // Informa que o limite de tempo foi atingido
            labelTempo.setText("Ordenacao interrompida: limite de 2 minutos");
        }

    }//GEN-LAST:event_botaoOrdenarActionPerformed

    private void botaoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBuscarActionPerformed

        // Verifica se existem produtos carregados
        if (produtos.isEmpty()) {
            labelTempo.setText("Carregue os produtos primeiro!");
            return;
        }

        // Obtém o texto digitado e remove espaços extras
        String pesquisa = campoBusca.getText().trim();

        // Verifica se o campo esta vazio
        if (pesquisa.isEmpty()) {
            labelTempo.setText("Digite um ID ou categoria!");
            return;
        }

        // Registra o inicio da busca
        long inicio = System.nanoTime();

        // Obtém o modelo da tabela
        DefaultTableModel modelo
                = (DefaultTableModel) tabelaProdutos.getModel();

        // Limpa os resultados anteriores
        modelo.setRowCount(0);

        try {

            // Tenta converter o texto digitado para numero
            // Se conseguir, realiza uma busca por ID
            int id = Integer.parseInt(pesquisa);

            ProdutoModel p = null;

            // Obtém a tecnica de busca selecionada
            String tecnica = comboBusca.getSelectedItem().toString();

            // BUSCA LINEAR
            if (tecnica.equals("Linear")) {

                // Procura o ID percorrendo a lista
                p = Buscador.buscaLinear(id, produtos);

                // BUSCA BINARIA
            } else if (tecnica.equals("Binária")) {

                // Cria uma copia da lista original
                ArrayList<ProdutoModel> listaID
                        = new ArrayList<>(produtos);

                // Cria um comparador por ID
                Comparator<ProdutoModel> porID
                        = (a, b) -> Integer.compare(a.getId(), b.getId());

                // Ordena a copia pelo ID para permitir a busca binaria
                Ordenador.quickSort(listaID, porID);

                // Realiza a busca binaria
                p = Buscador.buscaBinaria(id, listaID);

                // BUSCA HASHMAP
            } else {

                // Cria um indice HashMap utilizando os IDs
                HashMap<Integer, ProdutoModel> mapa
                        = Buscador.criarIndiceId(produtos);

                // Procura o produto diretamente pelo ID
                p = Buscador.buscaHash(id, mapa);
            }

            // Verifica se o produto foi encontrado
            if (p != null) {

                // Adiciona o produto encontrado na tabela
                modelo.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getCategoria(),
                    p.getPreco(),
                    p.getEstoque()
                });

            } else {

                labelTempo.setText("Produto nao encontrado!");
                return;
            }

        } catch (NumberFormatException e) {

            // Se o texto nao for um numero, realiza uma busca por categoria
            // Cria um indice HashMap por categoria
            HashMap<String, ArrayList<ProdutoModel>> mapa
                    = Buscador.criarIndiceCategoria(produtos);

            // Procura os produtos da categoria informada
            ArrayList<ProdutoModel> encontrados
                    = Buscador.buscaCategoria(pesquisa, mapa);

            // Verifica se a categoria existe
            if (encontrados != null) {

                // Percorre todos os produtos encontrados
                for (int i = 0; i < encontrados.size(); i++) {

                    ProdutoModel p = encontrados.get(i);

                    // Adiciona cada produto na tabela
                    modelo.addRow(new Object[]{
                        p.getId(),
                        p.getNome(),
                        p.getCategoria(),
                        p.getPreco(),
                        p.getEstoque()
                    });
                }

            } else {

                labelTempo.setText("Categoria nao encontrada!");
                return;
            }
        }

        // Registra o fim da busca
        long fim = System.nanoTime();

        // Calcula o tempo gasto em milissegundos
        double tempo = (fim - inicio) / 1000000.0;

        // Exibe o tempo da busca
        labelTempo.setText("Busca realizada em " + tempo + " ms");

    }//GEN-LAST:event_botaoBuscarActionPerformed

    private void botaoExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExportarActionPerformed
        // Verifica se existem produtos carregados
        if (produtos.isEmpty()) {
            labelTempo.setText("Carregue os produtos primeiro!");
            return;
        }

        // Exporta os produtos para um novo arquivo de texto
        GerenciadorArquivos.exportarArquivo(
                produtos, "produtos_exportados.txt");

        // Informa que a exportacao foi realizada
        labelTempo.setText("Arquivo exportado com sucesso!");

    }//GEN-LAST:event_botaoExportarActionPerformed

    private void botaoRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRelatorioActionPerformed
        // Chama o metodo responsavel por executar os testes
        // e gerar o arquivo relatorio.txt
        Relatorio.gerarRelatorio();

        // Informa que o relatorio foi gerado
        labelTempo.setText("Relatorio gerado com sucesso!");

    }//GEN-LAST:event_botaoRelatorioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Tela().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoCarregar;
    private javax.swing.JButton botaoExportar;
    private javax.swing.JButton botaoOrdenar;
    private javax.swing.JButton botaoRelatorio;
    private javax.swing.JTextField campoBusca;
    private javax.swing.JComboBox<String> comboAlgoritmo;
    private javax.swing.JComboBox<String> comboArquivo;
    private javax.swing.JComboBox<String> comboBusca;
    private javax.swing.JComboBox<String> comboCampo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTempo;
    private javax.swing.JTable tabelaProdutos;
    // End of variables declaration//GEN-END:variables
}
