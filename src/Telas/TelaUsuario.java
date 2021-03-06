/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

/**
 *
 * @author Skay
 */
import java.sql.*;
import dal.ModuloCoexao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {
// Frameworks que estão na biblioteca java.sql

    Connection conexao = null;
    // criando variáveis especias para conexão com o banco de dados
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaUsuario() {
        initComponents();
        conexao = ModuloCoexao.conector();
    }

    private void consultar() {
        String sql = "select * from tbeusuario where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsoNome.setText(rs.getString(2));
                txtUsoEmail.setText(rs.getString(3));
                txtUsoLogin.setText(rs.getString(4));
                txtUsoSenha.setText(rs.getString(5));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                // as linhas abaixo "limpam" os campos
                txtUsoNome.setText(null);
                txtUsoEmail.setText(null);
                txtUsoLogin.setText(null);
                txtUsoSenha.setText(null);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void adicionar() {
        String sql = "insert into tbeusuario(nomeuser, emailuser, login, senha) values(?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsoNome.getText());
            pst.setString(2, txtUsoEmail.getText());
            pst.setString(3, txtUsoLogin.getText());
            pst.setString(4, txtUsoSenha.getText());
// validação dos campos obrigatorios
            if ((txtUsoNome.getText().isEmpty()) || (txtUsoEmail.getText().isEmpty()) || (txtUsoLogin.getText().isEmpty()) || (txtUsoSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");
            } else {
                int adicionado = pst.executeUpdate();
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
     
                    txtUsoNome.setText(null);
                    txtUsoEmail.setText(null);
                    txtUsoLogin.setText(null);
                    txtUsoSenha.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void alterar() {
        String sql = "update tbeusuario set nomeuser=?, emailuser=?, login=?, senha=? where iduser=";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsoNome.getText());
            pst.setString(2, txtUsoEmail.getText());
            pst.setString(3, txtUsoLogin.getText());
            pst.setString(4, txtUsoSenha.getText());
            if ((txtUsuId.getText().isEmpty() || (txtUsoNome.getText().isEmpty()) || (txtUsoEmail.getText().isEmpty()) || (txtUsoLogin.getText().isEmpty()) || (txtUsoSenha.getText().isEmpty()))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");
            } else {
                int adicionado = pst.executeUpdate();
                //System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso");
                    txtUsoNome.setText(null);
                    txtUsoEmail.setText(null);
                    txtUsoLogin.setText(null);
                    txtUsoSenha.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbeusuario where iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso");
                    txtUsuId.setText(null);
                    txtUsoNome.setText(null);
                    txtUsoEmail.setText(null);
                    txtUsoLogin.setText(null);
                    txtUsoSenha.setText(null);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtUsoNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUsoEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtUsoLogin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUsoSenha = new javax.swing.JPasswordField();
        btnUsoCreate = new javax.swing.JButton();
        btnUsoRead = new javax.swing.JButton();
        btnUsoUpdate = new javax.swing.JButton();
        btnUsoDelete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setToolTipText("Adicionar");
        setPreferredSize(new java.awt.Dimension(770, 0));

        jLabel2.setText("* Nome");

        jLabel3.setText("* E-mail");

        jLabel4.setText("* Login");

        jLabel5.setText("* Senha");

        btnUsoCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/adicionar.png_160x41.jpg"))); // NOI18N
        btnUsoCreate.setToolTipText("Adicionar");
        btnUsoCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsoCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoCreateActionPerformed(evt);
            }
        });

        btnUsoRead.setText("Consultar");
        btnUsoRead.setToolTipText("Consultar");
        btnUsoRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsoRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoReadActionPerformed(evt);
            }
        });

        btnUsoUpdate.setText("Alterar");
        btnUsoUpdate.setToolTipText("Editar");
        btnUsoUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsoUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoUpdateActionPerformed(evt);
            }
        });

        btnUsoDelete.setText("Excluir");
        btnUsoDelete.setToolTipText("Excluir");
        btnUsoDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsoDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoDeleteActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("* Campos obrigatorios");

        jLabel1.setText("Id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUsoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUsoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnUsoCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(btnUsoRead)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(btnUsoUpdate)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(btnUsoDelete))
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUsoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtUsoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(202, 202, 202)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUsoRead)
                        .addComponent(btnUsoUpdate)
                        .addComponent(btnUsoDelete))
                    .addComponent(btnUsoCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(146, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnUsoCreate, btnUsoDelete, btnUsoRead, btnUsoUpdate});

        setBounds(0, 0, 685, 619);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsoReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoReadActionPerformed
        // chamando o metodo consultar
        consultar();
    }//GEN-LAST:event_btnUsoReadActionPerformed

    private void btnUsoCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoCreateActionPerformed
        adicionar();
    }//GEN-LAST:event_btnUsoCreateActionPerformed

    private void btnUsoUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoUpdateActionPerformed
        alterar();
    }//GEN-LAST:event_btnUsoUpdateActionPerformed

    private void btnUsoDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoDeleteActionPerformed
        remover();
    }//GEN-LAST:event_btnUsoDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsoCreate;
    private javax.swing.JButton btnUsoDelete;
    private javax.swing.JButton btnUsoRead;
    private javax.swing.JButton btnUsoUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtUsoEmail;
    private javax.swing.JTextField txtUsoLogin;
    private javax.swing.JTextField txtUsoNome;
    private javax.swing.JPasswordField txtUsoSenha;
    private javax.swing.JTextField txtUsuId;
    // End of variables declaration//GEN-END:variables
}
