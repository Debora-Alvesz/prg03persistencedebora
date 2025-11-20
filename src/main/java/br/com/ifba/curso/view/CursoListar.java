package br.com.ifba.curso.view;

 
import br.com.ifba.curso.controller.CursoController;
import br.com.ifba.curso.controller.CursoIController;
import br.com.ifba.curso.dao.CursoDao;
import br.com.ifba.curso.view.components.BotaoRendererEditor;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoService;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CursoListar extends javax.swing.JFrame {
  
    private CursoIController controller;

    public CursoListar() {
    initComponents();

    controller = new CursoController(
        new CursoService(
            new CursoDao()
        )
    );

    configurarTabela();
    carregarDados();
}

        //Configura a tabela de cursos com colunas personalizadas e botões editar e remover
        private void configurarTabela() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"CODIGO", "NOME", "COORDENADOR", "TURNO", "EDITAR", "REMOVER"}, 0) {
            // Sobrescreve o método para tornar apenas as colunas de ação editáveis
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4 || column == 5; // colunas "EDITAR" e "REMOVER"
            }
        };
        // Aplica o modelo à tabela
        tblCursos.setModel(model);
        // Define a altura das linhas
        tblCursos.setRowHeight(40);
        
        // Configura a coluna "EDITAR" com renderizador e editor personalizado
        tblCursos.getColumn("EDITAR").setCellRenderer(new BotaoRendererEditor("Editar", tblCursos));
        tblCursos.getColumn("EDITAR").setCellEditor(new BotaoRendererEditor("Editar", tblCursos));
        
        // Configura a coluna "REMOVER" com renderizador e editor personalizado
        tblCursos.getColumn("REMOVER").setCellRenderer(new BotaoRendererEditor("Remover", tblCursos));
        tblCursos.getColumn("REMOVER").setCellEditor(new BotaoRendererEditor("Remover", tblCursos));
    }

    //Carrega os dados dos cursos do banco de dados
    private void carregarDados() {
        // Busca todos os cursos no banco
        List<Curso> cursos = buscarTodos();
        
        // Atualiza a tabela com os cursos encontrados
        atualizarTabela(cursos);
    }
    //Atualiza a tabela com a lista de cursos 
    private void atualizarTabela(List<Curso> cursos) {
        // Obtém o modelo da tabela
        DefaultTableModel model = (DefaultTableModel) tblCursos.getModel();
        // Limpa todas as linhas existentes
        model.setRowCount(0);
        
        // Adiciona cada curso como uma nova linha na tabela
        for (Curso curso : cursos) {
            model.addRow(new Object[]{
                curso.getId(),
                curso.getNome(),
                curso.getCoordenador(),
                curso.getDisponibilidade(), // mostra o turno do curso
                "Editar",
                "Remover"
            });
        }
    }
    //Busca cursos por nome no banco de dados
    private List<Curso> buscarPorNome(String nome) {
    return controller.findByName(nome);
    }

    
    //Busca todos os cursos cadastrados no banco de dados.
   private List<Curso> buscarTodos() {
    return controller.findAll();
    }


public void removerCurso(long id) {
    if (controller.delete(id)) {
        JOptionPane.showMessageDialog(this, "Curso removido com sucesso!");
        carregarDados();
    } else {
        JOptionPane.showMessageDialog(this, "Não foi possível remover o curso!");
    }
    }


public void editarCurso(long id) {

    Curso curso = controller.findById(id);
    
    if (curso == null) {
        JOptionPane.showMessageDialog(this, "Curso não encontrado!");
        return;
    }

    String novoNome = JOptionPane.showInputDialog(this, "Novo nome:", curso.getNome());
    String novoCoordenador = JOptionPane.showInputDialog(this, "Novo coordenador:", curso.getCoordenador());
    String novaDisponibilidade = JOptionPane.showInputDialog(this, "Nova disponibilidade:", curso.getDisponibilidade());

    if (novoNome != null && novoCoordenador != null && novaDisponibilidade != null) {

        curso.setNome(novoNome);
        curso.setCoordenador(novoCoordenador);
        curso.setDisponibilidade(novaDisponibilidade);

        controller.update(curso);
        JOptionPane.showMessageDialog(this, "Curso atualizado!");
        carregarDados();
    }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnGeral = new javax.swing.JPanel();
        scpnTabelaCurso = new javax.swing.JScrollPane();
        tblCursos = new javax.swing.JTable();
        lblDescricao = new javax.swing.JLabel();
        txtPesquisar = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnGeral.setBackground(new java.awt.Color(255, 0, 153));
        pnGeral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scpnTabelaCurso.setBackground(new java.awt.Color(127, 166, 83));
        scpnTabelaCurso.setForeground(new java.awt.Color(127, 166, 83));

        tblCursos.setBackground(new java.awt.Color(255, 153, 255));
        tblCursos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(99, 120, 61)));
        tblCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CODIGO", "NOME", "DOCENTE", "EDITAR", "REMOVER"
            }
        ));
        scpnTabelaCurso.setViewportView(tblCursos);

        pnGeral.add(scpnTabelaCurso, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 860, 310));

        lblDescricao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDescricao.setForeground(new java.awt.Color(0, 51, 51));
        lblDescricao.setText("GERENCIAMENTO DE CURSOS");
        pnGeral.add(lblDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        pnGeral.add(txtPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 160, 50));

        btnCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ifba/curso/images/cadastrar.png"))); // NOI18N
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });
        pnGeral.add(btnCadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ifba/curso/images/pesquisar.png"))); // NOI18N
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        pnGeral.add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnGeral, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnGeral, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
         // Cria uma nova instância da tela de cadastro
    CursoCadastro telaCadastro = new CursoCadastro();

    // Exibe a tela de cadastro
    telaCadastro.setVisible(true);

    // Fecha (ou esconde) a tela de listagem
    this.dispose();
    
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // TODO add your handling code here:
        String termo = txtPesquisar.getText().trim();
        
        if (termo.isEmpty()) {
            carregarDados();
        } else {
            List<Curso> cursos = buscarPorNome(termo);
            if (cursos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum curso encontrado com o termo: " + termo);
            } else {
                atualizarTabela(cursos);
            }
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed
    // Método para atualizar a lista após cadastro/edição
    public void atualizarLista() {
        carregarDados();
    }

   @Override
    public void dispose() {
        super.dispose();
    }
   
  
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CursoListar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CursoListar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CursoListar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CursoListar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CursoListar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JPanel pnGeral;
    private javax.swing.JScrollPane scpnTabelaCurso;
    private javax.swing.JTable tblCursos;
    private javax.swing.JTextField txtPesquisar;
    // End of variables declaration//GEN-END:variables
}