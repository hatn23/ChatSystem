package UI;
import java.awt.event.ActionEvent;
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
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.*;
import database.Database;
//import database.History;
import network.TCPClientThread;

@SuppressWarnings("serial")
public class ChatWindow extends javax.swing.JFrame implements WritableWindows {
	private Interface inter;
	private Interface client;
	private Message msg;
	int sourceport;


	public ChatWindow(Interface inter, Interface client/*, Message msg*/) {
		initComponents();
        /*this.msg = msg;
        if (this.msg != null) {
            chatBox.setText(this.msg.toString());
        }*/
        this.inter = inter;
        this.client = client;
        this.pseudoLabel.setText("Pseudo : " + inter.getUser().getPseudo());
        this.hostLabel.setText("My Host : " + inter.getUser().getHost());
        sourceport = inter.getUser().getPort();
        this.clientLabel.setText("To : " + client.getUser().getPseudo() + " at " + client.getUser().getHost());
    }



	private void initComponents() {
		jScrollPane1 = new javax.swing.JScrollPane();
        chatBox = new javax.swing.JTextArea();
        message = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        sendImageButton = new javax.swing.JButton();
        pseudoLabel = new javax.swing.JLabel();
        hostLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        clientLabel = new javax.swing.JLabel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        chatBox.setEditable(false);
        chatBox.setColumns(20);
        chatBox.setRows(5);
        jScrollPane1.setViewportView(chatBox);

        message.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messageKeyPressed(evt);
            }
        });

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        pseudoLabel.setForeground(new java.awt.Color(238, 238, 238));
        pseudoLabel.setText("Username : ");

        hostLabel.setText("My Host :");

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        clientLabel.setText("To:");

        sendImageButton.setText("Send Image");
        sendImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendImageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hostLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendImageButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clientLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(pseudoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(clientLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pseudoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hostLabel)
                    .addComponent(sendImageButton))
                .addContainerGap())
        );
     


	}
	protected void backButtonActionPerformed(ActionEvent evt) {
		 this.setVisible(false);
		
	}


	protected void sendButtonActionPerformed(ActionEvent evt) {
		try {
            String msg = "[" + inter.getUser().getPseudo() + "] : " + message.getText();
            message.setText("");
            new TCPClientThread().sendMessageTo(this.inter, this.client.getUser().getHost(), User.portTCP, msg);
            this.inter.getChatWindowForUser(client.getUser().getHost()).write(msg);
        } catch (Exception ex) {
            Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
		
	}


	protected void messageKeyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String msg = "[" + inter.getUser().getPseudo() + "] : " + message.getText();
                message.setText("");
                new TCPClientThread().sendMessageTo(this.inter, this.client.getUser().getHost(), User.portTCP, msg);
                this.inter.getChatWindowForUser(client.getUser().getHost()).write(msg);
            } catch (Exception ex) {
                Logger.getLogger(ChatWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
		
	}


	protected void sendImageButtonActionPerformed(ActionEvent evt) {
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

		
	}
	
	private void formWindowClosing(java.awt.event.WindowEvent evt) {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(false);
    }


	public String getPseudo() {
		return this.inter.getUser().getPseudo();
	}

	public void setPseudoLabel(String s) {
		this.pseudoLabel.setText(s);
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
        	chatBox.append(reciever);
        	chatBox.append(message);
        	chatBox.append(Time);
        	chatBox.append("\n");
        }
	}


	@Override
	public void write(String string) {
		String message = timeStamp();
        chatBox.append(message + System.lineSeparator());
        this.msg.addMessage(message);
        //History.getInstance().updateHistory(msg);

	}

	

	private javax.swing.JButton backButton;
	private javax.swing.JTextArea chatBox;
	private javax.swing.JLabel clientLabel;
	private javax.swing.JLabel hostLabel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField message;
	private javax.swing.JLabel pseudoLabel;
	private javax.swing.JButton sendButton;
	private javax.swing.JButton sendImageButton;



}
