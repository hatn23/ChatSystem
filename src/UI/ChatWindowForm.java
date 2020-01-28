/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import data.Interface;
import data.Message;
import data.User;
import database.Database;
import database.History;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import network.TCPClientThread;

/**
 *
 * @author HaHa
 */
@SuppressWarnings("serial")
public class ChatWindowForm extends javax.swing.JFrame {
    
    private Interface inter;
	private Interface client;
	private Message msg;
	int sourceport;
    /**
     * Creates new form ChatWindowForm
     */
    
    public ChatWindowForm(Interface inter, Interface client, Message msg) {
        initComponents();
        this.msg = msg;
        if (this.msg != null) {
            jTextAreaChatBox.setText(this.msg.toString());
        }
        this.inter = inter;
        this.client = client;
        sourceport = inter.getUser().getPort();
        this.jLabelTitle.setText("Chat : " + client.getUser().getPseudo());
        this.jLabelHost.setText("My Host : " + inter.getUser().getHost());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTextFieldMessage = new javax.swing.JTextField();
        jTextAreaChatBox = new javax.swing.JTextArea();
        jLabelAttach = new javax.swing.JLabel();
        jLabelClose = new javax.swing.JLabel();
        jLabelMin = new javax.swing.JLabel();
        jLabelTitle = new javax.swing.JLabel();
        jLabelSend = new javax.swing.JLabel();
        jLabelHost = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(52, 73, 94));
        jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });

        jTextFieldMessage.setForeground(new java.awt.Color(0, 0, 0));
        jTextFieldMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldMessageKeyPressed(evt);
            }
        });

        jTextAreaChatBox.setEditable(false);
        jTextAreaChatBox.setColumns(20);
        jTextAreaChatBox.setForeground(new java.awt.Color(0, 0, 0));
        jTextAreaChatBox.setLineWrap(true);
        jTextAreaChatBox.setRows(5);
        jTextAreaChatBox.setWrapStyleWord(true);
        jTextAreaChatBox.setBorder(null);
        jTextAreaChatBox.setCaretColor(new java.awt.Color(255, 255, 255));

        jLabelAttach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtonIcon/attach32px.png"))); // NOI18N
        jLabelAttach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAttachMouseClicked(evt);
            }
        });

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

        jLabelTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitle.setText("Chat");

        jLabelSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtonIcon/sendBlue32px.png"))); // NOI18N
        jLabelSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelSendMouseClicked(evt);
            }
        });

        jLabelHost.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHost.setText("MyHost");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextAreaChatBox, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jTextFieldMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelAttach)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelSend)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelHost))
                        .addGap(18, 18, 18)
                        .addComponent(jLabelMin)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelClose)))
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelClose, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelHost)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextAreaChatBox, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldMessage)
                    .addComponent(jLabelAttach, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jLabelSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelAttachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAttachMouseClicked
        JFileChooser fileChooser = new JFileChooser();

        FileFilter filter = new FileNameExtensionFilter("Images (*.jpg , *.png)", "jpg", "png");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                @SuppressWarnings("unused")
				BufferedImage bimg = ImageIO.read(selectedFile);
                new TCPClientThread().sendImageTo(this.inter, this.client.getUser().getHost(), User.portTCP, selectedFile);
                this.inter.getChatWindowForUser(client.getUser().getHost()).write("[" + this.inter.getUser().getPseudo() + "] " + selectedFile.getName());
            } catch (IOException ex) {
                System.out.println("Error with BufferedImage");
            } catch (Exception ex) {
                System.out.println("Error with TCP sender");
            }

        }
    }//GEN-LAST:event_jLabelAttachMouseClicked

    private void jLabelSendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSendMouseClicked
        try {
            String msg = "[" + inter.getUser().getHost() + "] : " + jTextFieldMessage.getText();
            jTextFieldMessage.setText("");
            new TCPClientThread().sendMessageTo(this.inter, this.client.getUser().getHost(), User.portTCP, msg);
            this.inter.getChatWindowForUser(client.getUser().getHost()).write(msg);
        } catch (Exception ex) {
            Logger.getLogger(ChatWindowForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabelSendMouseClicked

    private void jLabelMinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabelMinMouseClicked

    private void jLabelCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseClicked
       this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(false);
    }//GEN-LAST:event_jLabelCloseMouseClicked

    private void jTextFieldMessageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldMessageKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String msg = "[" + inter.getUser().getHost() + "] : " + jTextFieldMessage.getText();
                System.out.println("jTextFieldMessageKeyPressed"+ msg);
                jTextFieldMessage.setText("");
                new TCPClientThread().sendMessageTo(this.inter, this.client.getUser().getHost(), User.portTCP, msg);
                this.inter.getChatWindowForUser(client.getUser().getPseudo()).write(msg);
            } catch (Exception ex) {
                Logger.getLogger(ChatWindowForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTextFieldMessageKeyPressed

    private void jPanel2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseDragged
        int coordinateX = evt.getXOnScreen();
        int coordinateY = evt.getYOnScreen();
        this.setLocation(coordinateX, coordinateY);
    }//GEN-LAST:event_jPanel2MouseDragged

    
    public String getPseudo() {
		return this.inter.getUser().getPseudo();
	}

	public void setPseudoLabel(String s) {
		this.jLabelTitle.setText("Chat - " + s);
	}

	public void display() {
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void closeWindow() {
		this.setVisible(false);

	}
	
	private String timeStamp() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm, dd/MM/yyyy] - ");
        return sdf.format(cal.getTime());
	}
	
	public void display_history(ArrayList<String> History) throws SQLException {
        java.util.Iterator<String> iter = History.iterator(); 
        
        while(iter.hasNext()) {
        	String this_msg =iter.next();
        	String reciever = Database.get_Username(Database.get_Receiver(this_msg)) + " \n";
        	String message= this_msg + " \n";
        	String Time= Database.get_Time(this_msg) + " \n";
        	jTextAreaChatBox.append(reciever);
        	jTextAreaChatBox.append(message);
        	jTextAreaChatBox.append(Time);
        	jTextAreaChatBox.append("\n");
        }
        
	}/**
     * @param args the command line arguments
     */
    

    public void write(String string) {
        String message = timeStamp()+ string;
        jTextAreaChatBox.append(message + System.lineSeparator());
        this.msg.addMessage(message);
        History.getInstance().updateHistory(msg);

	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAttach;
    private javax.swing.JLabel jLabelClose;
    private javax.swing.JLabel jLabelHost;
    private javax.swing.JLabel jLabelMin;
    private javax.swing.JLabel jLabelSend;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextArea jTextAreaChatBox;
    private javax.swing.JTextField jTextFieldMessage;
    // End of variables declaration//GEN-END:variables
}
