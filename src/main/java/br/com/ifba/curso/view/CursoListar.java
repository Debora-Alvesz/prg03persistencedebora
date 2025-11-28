package br.com.ifba.curso.view;

 
import br.com.ifba.curso.controller.CursoIController;
import br.com.ifba.curso.view.components.BotaoRendererEditor;
import br.com.ifba.curso.entity.Curso;
import jakarta.annotation.PostConstruct;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class CursoListar extends JFrame {
  
    @Autowired
    private CursoIController controller;

    @Autowired
    private ApplicationContext context;

    //construtor
    public CursoListar() {
    // Inicializa os componentes gráficos (botões, tabela, etc.)
    initComponents();
    configurarTabela();
}
    // Chama carregar dados depois que o Spring injetar o bean
    @PostConstruct
    public void initAfterInjection() {
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
    
    // Tenta deletar o curso chamando o método 'delete' do controller,
    // passando o ID do curso a ser removido.
    // Presume-se que 'controller.delete(id)' retorna 'true' se a operação for bem-sucedida.
    if (controller.delete(id)) {
        
        //Notifica o usuário com uma mensagem de sucesso se a exclusão foi bem sucedida.
        JOptionPane.showMessageDialog(this, "Curso removido com sucesso!");
        
        //Recarrega os dados na tabela para refletir a remoção.
        carregarDados();
    } else {
        // Se o controller.delete(id) retornou 'false' (ou houve uma exceção não tratada
        // que resultou em falha):
        
        // Notifica o usuário que a remoção não foi possível.
        JOptionPane.showMessageDialog(this, "Não foi possível remover o curso!");
    }
    }


public void editarCurso(long id) {

    // 1. Busca o curso a ser editado usando o ID. O Controller delega essa busca ao Service/Repository.
    Curso curso = controller.findById(id);
    
    // 2. Verifica se o curso foi realmente encontrado.
    if (curso == null) {
        // Se o curso for nulo (não encontrado no banco de dados):
        
        // Notifica o usuário e interrompe a execução do método (return).
        JOptionPane.showMessageDialog(this, "Curso não encontrado!");
        return;
    }

    // 3. Captura os novos dados do usuário usando janelas de input (pop-ups).
    // O valor atual do curso (curso.getNome(), etc.) é usado como valor padrão na caixa de diálogo.
    String novoNome = JOptionPane.showInputDialog(this, "Novo nome:", curso.getNome());
    String novoCoordenador = JOptionPane.showInputDialog(this, "Novo coordenador:", curso.getCoordenador());
    String novaDisponibilidade = JOptionPane.showInputDialog(this, "Nova disponibilidade:", curso.getDisponibilidade());

    // 4. Validação simples: verifica se o usuário não cancelou nenhum dos pop-ups.
    // Se o usuário clicar em 'Cancelar' em qualquer JOptionPane, a variável recebe 'null'.
    if (novoNome != null && novoCoordenador != null && novaDisponibilidade != null) {

        // 5. Se os dados forem válidos (não nulos), atualiza o objeto Curso com os novos valores.
        curso.setNome(novoNome);
        curso.setCoordenador(novoCoordenador);
        curso.setDisponibilidade(novaDisponibilidade);

        // 6. Envia o objeto Curso atualizado para a camada de persistência salvar as mudanças.
        controller.update(curso);
        
        // 7. Notifica o usuário sobre a atualização bem-sucedida.
        JOptionPane.showMessageDialog(this, "Curso atualizado!");
        
        // 8. Recarrega os dados na tabela para mostrar o curso com as novas informações.
        carregarDados();
    }
    // Se o usuário clicou em 'Cancelar' (ou fechou) em algum JOptionPane, o bloco 'if' é ignorado
    // e o método termina sem fazer nenhuma alteração.
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

    CursoCadastro telaCadastro = context.getBean(CursoCadastro.class);

    // Exibe a tela de cadastro
    telaCadastro.setVisible(true);

    // Fecha a tela de listagem atual
    this.dispose();
    
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
       // Obtém o texto digitado no campo de pesquisa (txtPesquisar).
        String termo = txtPesquisar.getText().trim();
        
        // Verifica se o termo de pesquisa está vazio após a remoção de espaços.
        if (termo.isEmpty()) {
            
            // Se o campo de pesquisa estiver vazio, carrega todos os dados novamente
            carregarDados();
        } else {
            // Se houver um termo de pesquisa, chama um método (que interage com o Service/Controller) para buscar os cursos que correspondam ao termo.
            List<Curso> cursos = buscarPorNome(termo);
            
            // Verifica se a lista de cursos retornada pela busca está vazia.
            if (cursos.isEmpty()) {
                
                // Se a lista estiver vazia, exibe uma mensagem de diálogo informando que nenhum curso foi encontrado.
                JOptionPane.showMessageDialog(this, "Nenhum curso encontrado com o termo: " + termo);
            } else {
                // Se cursos foram encontrados, chama o método para atualizar a tabela com a lista de resultados da pesquisa.
                atualizarTabela(cursos);
            }
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed
    // Método para atualizar a lista após cadastro/edição
    public void atualizarLista() {
        carregarDados();
    }
    //fecha a janela apos o cadastro
   @Override
    public void dispose() {
        super.dispose();
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