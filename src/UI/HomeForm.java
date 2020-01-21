/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.JFrame;
import network.*;
import data.*;
import database.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.WindowConstants;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.text.BadLocationException;


/**
 *
 * @author nhtran
 */
@SuppressWarnings("serial")
public class HomeForm extends javax.swing.JFrame {
    
    private Interface inter;
	static Thread listenTCP = null;
	static TCPServer runnableTCP = null;
    public static History history;
    DefaultListModel<String>  onlineListModel;
        

    /**
     * Creates new form HomeForm
     */
    @SuppressWarnings("static-access")
	public HomeForm(Interface inter, History history) {
    	this.inter = inter;
	this.history = history.getInstance();
	this.onlineListModel = new DefaultListModel<>();
	for(User u : inter.getOnlineList()){
		onlineListModel.addElement(u.getPseudo()+ ":"+ u.getHost()+":"+u.getPort());
		}
        initComponents();
        this.jLabelPseudo.setText( " - "+inter.getUser().getPseudo());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelClose = new javax.swing.JLabel();
        jLabelHome = new javax.swing.JLabel();
        jLabelMin = new javax.swing.JLabel();
        jLabelRename = new javax.swing.JLabel();
        jLabelLogout = new javax.swing.JLabel();
        jLabelPseudo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPaneNoti = new javax.swing.JScrollPane();
        jTextAreaNoti = new javax.swing.JTextArea();
        jScrollPaneOnlineList = new javax.swing.JScrollPane();
        jListOnline = new javax.swing.JList<>();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(226, 106, 106));

        jLabelClose.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelClose.setForeground(new java.awt.Color(255, 255, 255));
        jLabelClose.setText("X");
        jLabelClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseMouseClicked(evt);
            }
        });

        jLabelHome.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelHome.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome.setText("Home");

        jLabelMin.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelMin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMin.setText("-");
        jLabelMin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinMouseClicked(evt);
            }
        });

        jLabelRename.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelRename.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRename.setText("Rename");
        jLabelRename.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelRenameMouseClicked(evt);
            }
        });

        jLabelLogout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelLogout.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLogout.setText("Log Out");
        jLabelLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLogoutMouseClicked(evt);
            }
        });

        jLabelPseudo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPseudo.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelHome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelPseudo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelMin)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelClose)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelRename)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelLogout)
                        .addContainerGap(726, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelHome)
                    .addComponent(jLabelPseudo)
                    .addComponent(jLabelMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelClose))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRename)
                    .addComponent(jLabelLogout))
                .addGap(6, 6, 6))
        );

        jPanel2.setBackground(new java.awt.Color(52, 73, 94));

        jScrollPaneNoti.setBackground(new java.awt.Color(52, 73, 94));
        jScrollPaneNoti.setBorder(null);
        jScrollPaneNoti.setHorizontalScrollBar(null);

        jTextAreaNoti.setEditable(false);
        jTextAreaNoti.setBackground(new java.awt.Color(52, 73, 94));
        jTextAreaNoti.setColumns(20);
        jTextAreaNoti.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaNoti.setLineWrap(true);
        jTextAreaNoti.setRows(5);
        jTextAreaNoti.setWrapStyleWord(true);
        jTextAreaNoti.setBorder(null);
        jTextAreaNoti.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPaneNoti.setViewportView(jTextAreaNoti);

        jScrollPaneOnlineList.setBackground(new java.awt.Color(52, 73, 94));
        jScrollPaneOnlineList.setBorder(null);
        jScrollPaneOnlineList.setHorizontalScrollBar(null);

        jListOnline.setBackground(new java.awt.Color(52, 73, 94));
        jListOnline.setModel(onlineListModel
        );
        jListOnline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListOnlineMouseClicked(evt);
            }
        });
        jScrollPaneOnlineList.setViewportView(jListOnline);

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneOnlineList, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneNoti, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPaneNoti)
                    .addComponent(jScrollPaneOnlineList, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseClicked
        try {
            new UDPClientThread().sendDisconnect(inter);
        } catch (UnknownHostException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
		this.inter.closeAllChatWindow();
		this.setVisible(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//this.dispose();
		LoginForm loginWindow = new LoginForm(inter);
		loginWindow.setTitle("You are disconnected");
		loginWindow.display();
                
        System.exit(0);
    }//GEN-LAST:event_jLabelCloseMouseClicked

    private void jLabelMinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabelMinMouseClicked

    private void jLabelRenameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelRenameMouseClicked
        ChangePseudoForm changePseudoWindow = new ChangePseudoForm(this.inter, this);
	changePseudoWindow.display();
    }//GEN-LAST:event_jLabelRenameMouseClicked

    private void jLabelLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogoutMouseClicked
        try {
			new UDPClientThread().sendDisconnect(this.inter);
		}
		catch(UnknownHostException ex){
			Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
		}
		// Close Home Window and back to Login Window
		this.inter.closeAllChatWindow();
		this.setVisible(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.dispose();
		LoginForm loginWindow = new LoginForm(inter);
		loginWindow.setTitle("You are disconnected");
		loginWindow.display();
    }//GEN-LAST:event_jLabelLogoutMouseClicked

    private void jListOnlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListOnlineMouseClicked
        if (!jListOnline.isSelectionEmpty()) {
            String friend = jListOnline.getSelectedValue();
            String seg[] = friend.split(":");

            /* Find an user when we know his nickname
            ** Then, open the chatwindow with him
             */
            try {
                Interface client = new Interface(new User(seg[0], seg[1]));
                if (seg[0].charAt(0) == "[".charAt(0)) {
                    String split[] = seg[0].split("] ");
                    client.getUser().setPseudo(split[1]);
                }

                this.inter.updateOnlineList(client.getUser());

                try {
                    this.inter.updateHome();
                } catch (SQLException ex) {
                    Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.inter.getChatWindowForUser(client.getUser().getHost()).setTitle(client.getUser().getPseudo() + ": Chat");
                this.inter.getChatWindowForUser(client.getUser().getHost()).display();
            } catch (IOException ex) {
                Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jListOnlineMouseClicked

   public void setPseudoLabel(String label) {
		jLabelPseudo.setText(" - "+ label);
	}
	public Interface getInterface() {
		return this.inter;
	}

	public void removeFromList(User user) {
		this.onlineListModel.removeElement(user.getPseudo() + ":" + user.getHost() + ":" + user.getPort());
	}
   public void display() {
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
   
   public DefaultListModel<String> getOnlineList() {
		return onlineListModel;
	}

	public void writeNotification(String s) throws BadLocationException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm, dd/MM/yyyy] - ");
		jTextAreaNoti.getDocument().insertString(0, sdf.format(cal.getTime()) + s + System.lineSeparator(), null);
		jTextAreaNoti.setCaretPosition(0);
	}
    
    /**
     * @param args the command line arguments
     */
   
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelClose;
    private javax.swing.JLabel jLabelHome;
    private javax.swing.JLabel jLabelLogout;
    private javax.swing.JLabel jLabelMin;
    private javax.swing.JLabel jLabelPseudo;
    private javax.swing.JLabel jLabelRename;
    private javax.swing.JList<String> jListOnline;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPaneNoti;
    private javax.swing.JScrollPane jScrollPaneOnlineList;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextAreaNoti;
    // End of variables declaration//GEN-END:variables
}
