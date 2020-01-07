package UI;
import java.awt.*;
import javax.swing.*;
import data.*;

@SuppressWarnings("serial")
public class ChatWindow extends javax.swing.JFrame implements WritableWindows {
	private Interface inter;
	private Interface client;
	int sourceport;


	public ChatWindow(Interface inter, Interface client) {
		initComponents();
		this.inter = inter;
		this.client = client;
		this.pseudoLabel.setText("Pseudo : " + inter.getUser().getPseudo());
		this.hostLabel.setText("My Host : " + inter.getUser().getHost());
		sourceport = inter.getUser().getPort();
		this.clientLabel.setText("To : " + client.getUser().getPseudo() + " at " + client.getUser().getHost());
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				closeWindow();
			}
		});
		
	}


	private void initComponents() {
		// TODO Auto-generated method stub

	}
	private void sendButton() {
		// TODO Auto-generated method stub
	}
	private void message() {
		// TODO Auto-generated method stub
	}
	private void backButton() {
		// TODO Auto-generated method stub
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


	@Override
	public void write(String string) {
		// TODO Auto-generated method stub

	}

	private javax.swing.JButton backButton;
	private javax.swing.JTextArea chatBox;
	private javax.swing.JLabel clientLabel;
	private javax.swing.JLabel hostLabel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField message;
	private javax.swing.JLabel pseudoLabel;
	private javax.swing.JButton sendButton;



}
