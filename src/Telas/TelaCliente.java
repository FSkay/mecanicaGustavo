/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import java.sql.*;
import dal.ModuloCoexao;
import javax.swing.JOptionPane;
// a linha abaixo importa recursos da biblioteca rs2xml.jar facilita a ligacao tabela com o bco de dados
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Skay
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    // criando variáveis especias para conexão com o banco de dados
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
        initComponents();
        conexao = ModuloCoexao.conector();
    }
 //metodo para adicionar clientes
    public void adicionarcli() {
        String sql = "insert into tbclientes( nomecli, cpfcli, enderecocli, cidadecli, fonecli, emailcli) values(?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeCli.getText());
            pst.setString(2, txtCpfCli.getText());
            pst.setString(3, txtEndCli.getText());
            pst.setString(4, txtCidaCli.getText());
            pst.setString(5, txtFoneCli.getText());
            pst.setString(6, txtEmailCli.getText());
            // valida se os dados forma preenchidos
            if ((txtNomeCli.getText().isEmpty()) || (txtCpfCli.getText().isEmpty()) || (txtEndCli.getText().isEmpty()) || (txtCidaCli.getText().isEmpty()) || (txtFoneCli.getText().isEmpty()) || (txtEmailCli.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");
            } else {
                int adicionadocli = pst.executeUpdate();
                // Essa linha faz que que "limpa" os campos após a inserção dos dados
                // cliente com sucesso
                if (adicionadocli > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
                    //limpa os campos apos update
                    txtNomeCli.setText(null);
                    txtCpfCli.setText(null);
                    txtEndCli.setText(null);
                    txtCidaCli.setText(null);
                    txtFoneCli.setText(null);
                    txtEmailCli.setText(null);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
//metodo para alterar clientes pelo nome
    public void alterarcli() {
        String sql = "update tbclientes set nomecli=?, cpfcli=?, enderecocli=?, cidadecli=?, fonecli=?, emailcli=?  where idcli=?";
        try {
            pst = conexao.prepareStatement(sql);
     
            pst.setString(1, txtNomeCli.getText());
            pst.setString(2, txtCpfCli.getText());
            pst.setString(3, txtEndCli.getText());
            pst.setString(4, txtCidaCli.getText());
            pst.setString(5, txtFoneCli.getText());
            pst.setString(6, txtEmailCli.getText());
                   pst.setString(7, txtCliId.getText());
            if (( (txtNomeCli.getText().isEmpty()) || (txtCpfCli.getText().isEmpty()) || (txtEndCli.getText().isEmpty()) || (txtCidaCli.getText().isEmpty()) || (txtFoneCli.getText().isEmpty()) || (txtEmailCli.getText().isEmpty()))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");
            } else {
                int adicionadocli = pst.executeUpdate();
                if (adicionadocli > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do cliente alterado com sucesso");
               //     txtCliId.setText(null);
                    txtNomeCli.setText(null);
                    txtCpfCli.setText(null);
                    txtEndCli.setText(null);
                    txtCidaCli.setText(null);
                    txtFoneCli.setText(null);
                    txtEmailCli.setText(null);
                    btnAddCli.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //metodo para remover um cliente
    private void removercli() {
        int confirmacli = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este cliente", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirmacli == JOptionPane.YES_OPTION) {
            String sql = "delete from tbclientes where idcli=?";
            try {
                pst = conexao.prepareStatement(sql);
           pst.setString(1, txtCliId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso");
                  txtCliId.setText(null);
                    txtNomeCli.setText(null);
                    txtCpfCli.setText(null);
                    txtEndCli.setText(null);
                    txtCidaCli.setText(null);
                    txtFoneCli.setText(null);
                    txtEmailCli.setText(null);
                    // a linha abaixo desabilita o botão adicionar
                    btnAddCli.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }
    // metodo para fazer consulta avançada

    private void pesquisar_Cliente() {
        String sql = "select * from tbclientes where nomecli like ?";
        try {
            pst = conexao.prepareStatement(sql);
            //passando o conteúdo da caixa de pesquisa para o ? (interroga)
            //atenção ao "%"  - continuação da String sql
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();
            //a linha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // metdodo para setar os campos do formularios com os campos da tabela
    public void setar_campos() {
        int setar = tblClientes.getSelectedRow();
               txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        txtNomeCli.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        txtCpfCli.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
        txtEndCli.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
        txtCidaCli.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
        txtFoneCli.setText(tblClientes.getModel().getValueAt(setar, 6).toString());
        txtEmailCli.setText(tblClientes.getModel().getValueAt(setar, 7).toString());
        // a linha abaixo desabilita o botão adicionar
        btnAddCli.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAddCli = new javax.swing.JButton();
        btnEdiCli = new javax.swing.JButton();
        btnExcluCli = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNomeCli = new javax.swing.JTextField();
        txtcpfcli = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCpfCli = new javax.swing.JTextField();
        txtEndCli = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCidaCli = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEmailCli = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtFoneCli = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCliPesquisar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Mecanica Gustavo Clientes");
        setEnabled(false);

        btnAddCli.setText("Incluir");
        btnAddCli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCliActionPerformed(evt);
            }
        });

        btnEdiCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/editar.png"))); // NOI18N
        btnEdiCli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdiCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdiCliActionPerformed(evt);
            }
        });

        btnExcluCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/excluir.png_20x21.jpg"))); // NOI18N
        btnExcluCli.setText("Excluir");
        btnExcluCli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcluCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluCliActionPerformed(evt);
            }
        });

        jLabel2.setText("* Nome");

        txtcpfcli.setText("* CPF:");

        jLabel5.setText("* Endereço");

        jLabel6.setText("* Cidade");

        txtCidaCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCidaCliActionPerformed(evt);
            }
        });

        jLabel7.setText("* E-mail");

        jLabel10.setText("* Telefone");

        txtFoneCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFoneCliActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 0, 0));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("* Campos obrigatorios");

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        jButton1.setText("Consultar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "CPF", "Endereço"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel1.setText("Id Cliente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(198, 198, 198))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddCli)
                                .addGap(33, 33, 33)
                                .addComponent(btnEdiCli, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnExcluCli)
                                .addGap(97, 97, 97))
                            .addComponent(txtcpfcli, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(392, 392, 392))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEndCli, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCpfCli, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFoneCli, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                                    .addComponent(txtEmailCli)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtCidaCli, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcpfcli)
                    .addComponent(txtCpfCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEndCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCidaCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFoneCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtEmailCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdiCli)
                    .addComponent(btnAddCli)
                    .addComponent(btnExcluCli))
                .addGap(98, 98, 98))
        );

        setBounds(0, 0, 510, 539);
    }// </editor-fold>//GEN-END:initComponents
//metodo qe adiciona cliente
    private void btnAddCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCliActionPerformed
        adicionarcli();
    }//GEN-LAST:event_btnAddCliActionPerformed

    //Sem utilidade criado por engano
    private void txtFoneCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFoneCliActionPerformed

    }//GEN-LAST:event_txtFoneCliActionPerformed
    //Sem utilidade criado por engano
    private void txtCidaCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCidaCliActionPerformed

    }//GEN-LAST:event_txtCidaCliActionPerformed
//metodo chamado para remover(excluir)cliente
    private void btnExcluCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluCliActionPerformed
        removercli();
    }//GEN-LAST:event_btnExcluCliActionPerformed
// o evento abaixo é do tipo "enquanto for digitando" e logo abaixo chama o metodo pesquisar cliente
    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        pesquisar_Cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased
// evento que será usado para setar os campos da tabela (clicando com o botão esquerdo do mouse)
    // logo apos chama o metodo para setar os campo(setar_campos)
    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnEdiCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdiCliActionPerformed
        alterarcli();
    }//GEN-LAST:event_btnEdiCliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCli;
    private javax.swing.JButton btnEdiCli;
    private javax.swing.JButton btnExcluCli;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCidaCli;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtCpfCli;
    private javax.swing.JTextField txtEmailCli;
    private javax.swing.JTextField txtEndCli;
    private javax.swing.JTextField txtFoneCli;
    private javax.swing.JTextField txtNomeCli;
    private javax.swing.JLabel txtcpfcli;
    // End of variables declaration//GEN-END:variables
}
