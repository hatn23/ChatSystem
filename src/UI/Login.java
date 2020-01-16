package UI;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.*;
import javax.swing.*;
import static java.lang.Thread.sleep;

import data.*;
import network.*;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private Interface inter;
	static Thread listenTCP = null;
	static Thread listenUDP = null;
	static TCPServer runnableTCP = null;
	static UDPServer runnableUDP = null;

	public Login() {
		initComponents();
	}

	public Login(Interface inter) {
		this.inter = inter;
		initComponents();
		hostField.setText(inter.getUser().getHost());
	}

	private void initComponents() {
		hostLabel = new JLabel();
		pseudoLabel = new JLabel();
		pseudoField = new JTextField();
		hostField = new JTextField();
		titleLabel = new JLabel();
		logInButton = new JButton();
		
		setSize(300,700);
		setResizable(false);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				closeWindow(evt);
			}
		});
		hostLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hostLabel.setText("Your host :");
		pseudoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pseudoLabel.setText("Your pseudo :");

		pseudoField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				pseudoField(evt);
			}
		});

		hostField.setEditable(false);
		hostField.setText("localhost");
		titleLabel.setForeground(new Color(250, 0, 0));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setText("User Login");
		logInButton.setText("Log in");
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				loginButton(evt);
			}
		});
	
		
		GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(hostField)
                    .addComponent(pseudoField)
                    .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pseudoLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hostLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(110)
                .addComponent(logInButton, GroupLayout.PREFERRED_SIZE,  80, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0)
                .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE , Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pseudoLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pseudoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hostLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hostField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logInButton)
                .addContainerGap())
        );
        

        //pack();

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

	private void closeWindow (WindowEvent evt) {
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


	private JTextField hostField;
	private JLabel hostLabel;
	private JButton logInButton;
	private JTextField pseudoField;
	private JLabel pseudoLabel;
	private JLabel titleLabel;


}





