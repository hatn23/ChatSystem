package UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import static java.lang.Thread.sleep;

import data.*;
import network.*;

public class Login extends javax.swing.JFrame {

	private Interface inter;
	static Thread listenTCP = null;
	static Thread listenUDP = null;
	static TCPServer runnableTCP = null;
	static UDPServer runnableUDP = null;

	public Login() {
		initWindows();
	}

	public Login(Interface inter) {
		this.inter = inter;
		initWindows();
		hostField.setText(inter.getUser().getHost());
	}

	private void initWindows() {
		hostLabel = new javax.swing.JLabel();
		pseudoLabel = new javax.swing.JLabel();
		pseudoField = new javax.swing.JTextField();
		hostField = new javax.swing.JTextField();
		titleLabel = new javax.swing.JLabel();
		logInButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				closeWindow(evt);
			}
		});
		hostLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		hostLabel.setText("Your host :");

		pseudoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		pseudoLabel.setText("Your pseudo :");

		pseudoField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				pseudoField(evt);
			}
		});

		hostField.setEditable(false);
		hostField.setText("localhost");
		titleLabel.setForeground(new java.awt.Color(250, 0, 0));
		titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		titleLabel.setText("User Login");
		logInButton.setText("Log in");
		logInButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loginButton(evt);
			}
		});
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hostField)
                    .addComponent(pseudoField)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pseudoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hostLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pseudoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pseudoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hostLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hostField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logInButton)
                .addContainerGap())
        );

        pack();

	}
	
	protected void pseudoField(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (this.pseudoField.getText().equals("")) {
                this.titleLabel.setText("User nickname cannot be empty.");
            } else {
                try {
                    this.titleLabel.setText("Connecting... Please Wait");
                    /* Create a node with the nickname and the host address */
                    User user = new User(pseudoField.getText(), hostField.getText());
                    this.inter = new Interface(user);
                    

                    /* Start a server thread TCP to listen */
                    if (listenTCP != null && runnableTCP != null) {
                        runnableTCP.terminate();
                        listenTCP.join();
                    }
                    runnableTCP = new TCPServer(this.inter);
                    listenTCP = new Thread(runnableTCP);
                    listenTCP.start();

                    /* This thread is used to receive le broadcast by UDP*/
                    if (listenUDP != null && runnableUDP != null) {
                        runnableUDP.terminate();
                        listenUDP.join();
                    }
                    runnableUDP = new UDPServer(this.inter);
                    listenUDP = new Thread(runnableUDP);
                    listenUDP.start();

                    /* Send a broadcast when log in*/
                    new UDPClientThread().sendBroadcast(this.inter);

                    /* Open home if the nickname is unique*/
                    sleep(10);
                    if (this.inter.checkPseudo()) {
                        new UDPClientThread().sendBroadcast(this.inter);
                        this.inter.getHome().display();
                        this.setVisible(false);
                        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        this.dispose();
                    } else {
                        new UDPClientThread().sendDisconnect(this.inter);
                        this.titleLabel.setText("WARNING : This name has been used !");
                    }
                    System.out.println(this.inter);
                    System.out.println(this.inter.checkPseudo());

                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
		
	}

	protected void loginButton(ActionEvent evt) {
		if (this.pseudoField.getText().equals("")) {
            this.titleLabel.setText("User nickname cannot be empty.");
        } else {
            /* Check the password if it is valid*/
            try {
                this.titleLabel.setText("Connecting... Please Wait");
                /* Create a node with the nickname and the host address */
                User user = new User(pseudoField.getText(), hostField.getText());
                this.inter = new Interface(user);

                /* Start a server thread TCP to listen */
                if (listenTCP != null && runnableTCP != null) {
                    runnableTCP.terminate();
                    listenTCP.join();
                }
                runnableTCP = new TCPServer(this.inter);
                listenTCP = new Thread(runnableTCP);
                listenTCP.start();

                /* This thread is used to receive  broadcast by UDP*/
                if (listenUDP != null && runnableUDP != null) {
                    runnableUDP.terminate();
                    listenUDP.join();
                }
                runnableUDP = new UDPServer(this.inter);
                listenUDP = new Thread(runnableUDP);
                listenUDP.start();

                /* Send a broadcast when log in*/
                new UDPClientThread().sendBroadcast(this.inter);

                /* Open homepage if the nickname is unique*/
                sleep(10);
                if (this.inter.checkPseudo()) {
                    new UDPClientThread().sendBroadcast(this.inter);
                    this.inter.getHome().display();
                    this.setVisible(false);
                    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    this.dispose();
                } else {
                    new UDPClientThread().sendDisconnect(this.inter);
                    this.titleLabel.setText("WARNING : This name has been used !");
                }
                System.out.println(this.inter);
                System.out.println(this.inter.checkPseudo());

            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
		
	}

	private void closeWindow (java.awt.event.WindowEvent evt) {
        try {
            new UDPClientThread().sendDisconnect(inter);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	public void display(){
        this.setLocationRelativeTo(null); 
        this.setVisible(true);
    }

	public void setTitle(String title){
		titleLabel.setText(title);
	}


	private javax.swing.JTextField hostField;
	private javax.swing.JLabel hostLabel;
	private javax.swing.JButton logInButton;
	private javax.swing.JTextField pseudoField;
	private javax.swing.JLabel pseudoLabel;
	private javax.swing.JLabel titleLabel;


}





