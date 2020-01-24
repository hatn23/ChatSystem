/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import data.*;
import database.Database;
import java.awt.event.KeyEvent;
//import java.lang.System.Logger;
//import java.lang.System.Logger.Level;
import java.net.UnknownHostException;
import java.sql.SQLException;
import network.*;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;

/**
 *
 * @author HaHa
 */
@SuppressWarnings({ "serial", "unused" })
public class ChangePseudoForm extends javax.swing.JFrame {
    
     private HomeForm home;
     private Interface inter;
     private Boolean confirm = false;

    /**
     * Creates new form ChangePseudoForm
     */
    public ChangePseudoForm(Interface inter, HomeForm home) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.home = home;
	this.inter = inter;
        this.jTextFieldCurrentPseudo.setText(this.inter.getUser().getPseudo());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabelCurrentPseudo = new javax.swing.JLabel();
        jLabelNewPseudo = new javax.swing.JLabel();
        jLabelWarning = new javax.swing.JLabel();
        jTextFieldCurrentPseudo = new javax.swing.JTextField();
        jTextFieldNewPseudo = new javax.swing.JTextField();
        jButtonCancel = new javax.swing.JButton();
        jButtonConfirm = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jLabelClose = new javax.swing.JLabel();
        jLabelMin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(52, 73, 94));

        jLabelCurrentPseudo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelCurrentPseudo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCurrentPseudo.setText("Current Username :");

        jLabelNewPseudo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelNewPseudo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNewPseudo.setText("New Username :");

        jLabelWarning.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelWarning.setForeground(new java.awt.Color(241, 130, 141));
        jLabelWarning.setText("Please choose your new username");

        jTextFieldCurrentPseudo.setEditable(false);
        jTextFieldCurrentPseudo.setBackground(new java.awt.Color(108, 122, 137));
        jTextFieldCurrentPseudo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldCurrentPseudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCurrentPseudoActionPerformed(evt);
            }
        });

        jTextFieldNewPseudo.setBackground(new java.awt.Color(108, 122, 137));
        jTextFieldNewPseudo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldNewPseudo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldNewPseudoKeyPressed(evt);
            }
        });

        jButtonCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonConfirm.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonConfirm.setText("Confirm");
        jButtonConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelWarning)
                .addGap(86, 86, 86))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelCurrentPseudo)
                    .addComponent(jLabelNewPseudo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldCurrentPseudo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNewPseudo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabelWarning)
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCurrentPseudo)
                    .addComponent(jTextFieldCurrentPseudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNewPseudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNewPseudo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanel3.setBackground(new java.awt.Color(226, 106, 106));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });

        jLabelTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("Rename");

        jLabelClose.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelClose.setForeground(new java.awt.Color(255, 255, 255));
        jLabelClose.setText("X");
        jLabelClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseMouseClicked(evt);
            }
        });

        jLabelMin.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelMin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMin.setText("-");
        jLabelMin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitle)
                .addGap(157, 157, 157)
                .addComponent(jLabelMin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelClose)
                .addGap(12, 12, 12))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelCloseMouseClicked

    private void jLabelMinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinMouseClicked
         this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabelMinMouseClicked

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        this.setVisible(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jTextFieldCurrentPseudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCurrentPseudoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCurrentPseudoActionPerformed

    private void jButtonConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmActionPerformed
        String newName = jTextFieldNewPseudo.getText();
        String oldName = this.inter.getUser().getPseudo();
        if (this.checkPseudoUnique(newName) && !newName.equals("") && !newName.equals(oldName)) {
                try {
                    this.inter.getUser().setPseudo(newName);
                    this.home.setPseudoLabel("Your nickname : " + newName);
                    this.home.writeNotification(oldName + " has changed name to " + newName + System.lineSeparator());
                    this.confirm = true;
                    /* Ok, then sends a broadcast to inform */
                    try {
                        new UDPClientThread().sendRename(inter);
                    } catch (UnknownHostException ex) {
                        java.util.logging.Logger.getLogger(ChangePseudoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                    /* Done and close */
                    this.setVisible(false);
                    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    this.dispose();
                } catch (BadLocationException ex) {
                    java.util.logging.Logger.getLogger(ChangePseudoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            
        } else {
            this.jLabelWarning.setText("This name has been used !");
        }
	
	
    }//GEN-LAST:event_jButtonConfirmActionPerformed

    private void jTextFieldNewPseudoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNewPseudoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String newName = jTextFieldNewPseudo.getText();
            String oldName = this.inter.getUser().getPseudo();
            if (this.checkPseudoUnique(newName) && !newName.equals("") && !newName.equals(oldName)) {
                    try {
                        this.inter.getUser().setPseudo(newName);
                        this.home.setPseudoLabel("Your nickname : " + newName);
                        this.home.writeNotification(oldName + " has changed name to " + newName + System.lineSeparator());
                        this.confirm = true;
                        /* Ok, then sends a broadcast to inform */
                        try {
                            new UDPClientThread().sendRename(inter);
                        } catch (UnknownHostException ex) {
                            java.util.logging.Logger.getLogger(ChangePseudoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                        
                        
                        /* Done and close */
                        this.setVisible(false);
                        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        this.dispose();
                        
                    } catch (BadLocationException ex) {
                        java.util.logging.Logger.getLogger(ChangePseudoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
               
            } else {
                this.jLabelWarning.setText("This name has been used !");
            }
        }
    }//GEN-LAST:event_jTextFieldNewPseudoKeyPressed

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
         int coordinateX = evt.getXOnScreen();
        int coordinateY = evt.getYOnScreen();
        this.setLocation(coordinateX, coordinateY);
    }//GEN-LAST:event_jPanel3MouseDragged

    public String getNewPseudo() {
		if (this.confirm) {
			return this.jTextFieldNewPseudo.getText();
		}
		else
			return this.inter.getUser().getPseudo();
	}
	
public Boolean checkPseudoUnique(String pseudo) {
    Boolean unique = true;
    
         try {
             unique = Database.is_Unique(pseudo);
         } catch (SQLException ex) {
             java.util.logging.Logger.getLogger(ChangePseudoForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }
         return unique;
	}
	

	public void display() {
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonConfirm;
    private javax.swing.JLabel jLabelClose;
    private javax.swing.JLabel jLabelCurrentPseudo;
    private javax.swing.JLabel jLabelMin;
    private javax.swing.JLabel jLabelNewPseudo;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelWarning;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldCurrentPseudo;
    private javax.swing.JTextField jTextFieldNewPseudo;
    // End of variables declaration//GEN-END:variables
}
