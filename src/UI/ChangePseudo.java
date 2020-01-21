package UI;

import java.awt.event.*;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import data.Interface;
import data.User;
import network.UDPClientThread;

@SuppressWarnings("serial")
public class ChangePseudo extends javax.swing.JFrame {

        private HomeForm home;
	private Interface inter;
	private Boolean confirm = false;
	
        
        public ChangePseudo(Interface inter, HomeForm home) {
		initWindow();
		this.home = home;
		this.inter = inter;
	}

	private void initWindow() {
		titleLabel = new javax.swing.JLabel();
        pseudo = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();
        warningLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rename - ChatSystem");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        titleLabel.setText(" Enter your new nickname : ");

        pseudo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pseudoFieldKeyPressed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        confirmButton.setText("Confirm");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        warningLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(confirmButton))
                    .addComponent(pseudo)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(warningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pseudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(confirmButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warningLabel))
        );

        pack();
		
	}
	
	private void cancelButtonActionPerformed(ActionEvent evt) {
		this.setVisible(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.dispose();
	}
	
	private void confirmButtonActionPerformed(ActionEvent evt) {
		String newName = pseudo.getText();
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
                    Logger.getLogger(ChangePseudo.class.getName()).log(Level.SEVERE, null, ex);
                }

                /* Done and close */
                this.setVisible(false);
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                this.dispose();
            } catch (BadLocationException ex) {
                Logger.getLogger(ChangePseudo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.titleLabel.setText("This name has been used !");
        }
	}
	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(false);
		this.dispose();
	}
	

	private void pseudoFieldKeyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String newName = pseudo.getText();
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
                        Logger.getLogger(ChangePseudo.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    /* Done and close */
                    this.setVisible(false);
                    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    this.dispose();
                } catch (BadLocationException ex) {
                    Logger.getLogger(ChangePseudo.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this.titleLabel.setText("This name has been used !");
            }
        }
	}
	
	public String getNewPseudo() {
		if (this.confirm) {
			return this.pseudo.getText();
		}
		else
			return this.inter.getUser().getPseudo();
	}
	
	public Boolean checkPseudoUnique(String pseudo) {
		Boolean unique= true;
		for (User user : this.home.getInterface().getOnlineList()) {
			if (user.getPseudo().equals(pseudo)) {
				unique = false;
			}
		}
		return unique;
	}
	

	public void display() {
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	JButton confirmButton;
	JButton cancelButton;
	JTextField pseudo;
	JLabel titleLabel;
	JLabel warningLabel;
	
	
	

}
