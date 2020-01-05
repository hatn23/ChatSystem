package views;
import java.awt.*;
import javax.swing.*;
import data.*;

public class ChatWindow extends javax.swing.JFrame implements WritableWindows {
	/*private static JFrame frame = new JFrame ("Chat Session");
	private static JTextField textField = new JTextField("TextField");
	private static JTextArea messageArea = new JTextArea("TextArea");
	private static JButton button = new JButton ("Send");

	private static void initChatWindow() {
		frame.setSize(500,700);
		button.setBounds(415,630,70,30);
		textField.setEditable(true);
		messageArea.setEditable(false);
		messageArea.setBounds(30,30,430,520);
		textField.setBounds(30,560,380,100);
		frame.add(button);
		frame.add(messageArea);
		frame.add(textField);	
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);*/
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
