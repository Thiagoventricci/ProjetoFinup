/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package VIEW;

import DAO.PessoaFisicaDAO;
import DTO.PessoaFisicaDTO;
import javax.swing.JOptionPane;

/**
 *
 * @author thiag
 */
public class SaldoVIEW extends javax.swing.JInternalFrame {

    /**
     * Creates new form SaldoVIEW
     */
    public SaldoVIEW() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campoSaldo = new javax.swing.JTextField();
        mostrarSenha = new javax.swing.JCheckBox();
        btnHistoricoRecebimento = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("R$ ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, -1, -1));

        campoSaldo.setBackground(new java.awt.Color(204, 255, 204));
        campoSaldo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        campoSaldo.setForeground(new java.awt.Color(0, 0, 0));
        campoSaldo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoSaldo.setText("****");
        campoSaldo.setBorder(null);
        campoSaldo.setFocusable(false);
        campoSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoSaldoActionPerformed(evt);
            }
        });
        jPanel1.add(campoSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 200, 30));

        mostrarSenha.setBackground(new java.awt.Color(204, 255, 204));
        mostrarSenha.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        mostrarSenha.setForeground(new java.awt.Color(0, 0, 0));
        mostrarSenha.setText("Mostrar senha");
        mostrarSenha.setFocusPainted(false);
        mostrarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarSenhaActionPerformed(evt);
            }
        });
        jPanel1.add(mostrarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, -1, -1));

        btnHistoricoRecebimento.setBackground(new java.awt.Color(204, 255, 204));
        btnHistoricoRecebimento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHistoricoRecebimento.setForeground(new java.awt.Color(0, 0, 0));
        btnHistoricoRecebimento.setText("Historico de Recebimento");
        btnHistoricoRecebimento.setContentAreaFilled(false);
        btnHistoricoRecebimento.setFocusPainted(false);
        btnHistoricoRecebimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoRecebimentoActionPerformed(evt);
            }
        });
        jPanel1.add(btnHistoricoRecebimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 280, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostrarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarSenhaActionPerformed
        SaldoVIEW objsaldo  = new SaldoVIEW();
        if (mostrarSenha.isSelected()) {
            listarSaldo();
            mostrarSenha.setText("Esconder saldo");
        }else{
            
            campoSaldo.setText("****");
            mostrarSenha.setText("Mostrar saldo");
        }
    }//GEN-LAST:event_mostrarSenhaActionPerformed

    private void campoSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoSaldoActionPerformed

    private void btnHistoricoRecebimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoricoRecebimentoActionPerformed
        HistoricoDeRecebimentoVIEW objh = new HistoricoDeRecebimentoVIEW();
        PessoaFisicaDTO objdto = new PessoaFisicaDTO();
        objh.listarValoresPIX(objdto.getCpf_titular());
        objh.setVisible(true);
        
    }//GEN-LAST:event_btnHistoricoRecebimentoActionPerformed
        
      public void listarSaldo(){        
        /*Método responsavel por imprimir na interface grafica as informações
        recebidas pelo retorno do UsuarioDAO
        */
        
        try {
            /*chama a classe que guarda mesmo que brevenmente só para comparar
            com o banco de dados*/
            PessoaFisicaDAO objpdao = new PessoaFisicaDAO();
            
            /*Cria um Array com os valores retornados pelo UsuarioDAO*/
            PessoaFisicaDTO objpdto = objpdao.Saldo();
             campoSaldo.setText(objpdto.getSaldo());
             
            // Caso a conexão falhar, cai aqui 
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Listar saldo" 
            + erro);
        }  
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHistoricoRecebimento;
    private javax.swing.JTextField campoSaldo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox mostrarSenha;
    // End of variables declaration//GEN-END:variables
}
