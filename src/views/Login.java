package views;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Login {
	private static JFrame frame = new JFrame ("Login");
	private static JTextField textField = new JTextField("Identifiant");
	private static JButton button = new JButton ("Login");
	
	private static void initLogin() {
	frame.setSize(500,700);
	button.setBounds(150,300,200,20);
	textField.setBounds(150,250,200,40);
	frame.add(button);
	frame.add(textField);
	frame.setLayout(null);
	frame.setVisible(true);
	
	}
	
	public static void main(String[] args) {
	initLogin();
	}
}


