/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.ifba.curso.view;

import br.com.ifba.curso.controller.CursoIController;
import javax.swing.JOptionPane;
import br.com.ifba.curso.entity.Curso;
import javax.swing.JFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j // Adiciona o objeto 'log'

public class CursoCadastro extends JFrame {
    
    @Autowired
    private CursoIController controller;
    
    @Autowired
    private ApplicationContext context;

    //construtor
    public CursoCadastro() {
        initComponents();
    }

    public void limparCampos() {
    txtNome.setText("");
    txtCodigo.setText("");
    jTextField1.setText("");
    jComboBox1.setSelectedIndex(0); // volta para a primeira opção
} 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnSalvar = new javax.swing.JButton();
        lblDescricao = new javax.swing.JLabel();

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 0, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("Nome");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 50, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 51));
        jLabel2.setText("Código");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 50, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 51));
        jLabel3.setText("Coordenador");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 100, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 51));
        jLabel4.setText("Turno");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 50, 20));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 310, 30));
        jPanel1.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 310, 30));
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 310, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Matutino", "Vespertino", "Integral", "Noturno" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 310, 30));

        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 80, 30));

        lblDescricao.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDescricao.setForeground(new java.awt.Color(0, 51, 51));
        lblDescricao.setText("GERENCIAMENTO DE CURSOS");
        jPanel1.add(lblDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
   try {
        Curso curso = new Curso();
        // ... (coleta de dados)
        curso.setNome(txtNome.getText().trim());
        curso.setCodigoCurso(txtCodigo.getText().trim());
        curso.setCoordenador(jTextField1.getText().trim());
        curso.setDisponibilidade(jComboBox1.getSelectedItem().toString());

        // A View faz apenas a validação de coleta (se campos estão vazios)
        if (curso.getNome().isEmpty() || curso.getCodigoCurso().isEmpty()) {
             JOptionPane.showMessageDialog(this,
                     "Preencha nome e código antes de salvar.");
             return;
        }

        // DELEGAÇÃO: Chama APENAS o Controller.
        // O Controller chamará o Service, que contém a Regra de Negócio (unicidade)
       controller.save(curso);

       log.info("Curso salvo com sucesso. Nome: {}", curso.getNome()); // ✅ Log de sucesso
        
       log.info("Curso salvo com sucesso. Atualizando lista."); // Log no sucesso

            JOptionPane.showMessageDialog(this, "Curso salvo com sucesso!");
            limparCampos();

        // Abre tela lista e fecha a atual
        CursoListar telaListar = context.getBean(CursoListar.class);
        telaListar.atualizarLista();
        telaListar.setVisible(true);
        this.dispose();

    } catch (Exception e) {
        // Captura a exceção lançada pela Camada Service
       // ✅ ADICIONAR: Log o erro detalhado
        log.error("Falha ao salvar o curso no sistema. Erro: {}", e.getMessage(), e); 

        // Mensagem para o usuário final
        JOptionPane.showMessageDialog(this, "Erro ao salvar o curso: " + e.getMessage());
    }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
