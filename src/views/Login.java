package views;

import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Login {
	private static JFrame frame = new JFrame ("Login");
	private static JTextField textField = new JTextField("Identifiant");
	private static JButton button = new JButton ("Login");
	private static JTextArea textArea = new JTextArea();
	
	private static void initLogin() {
	frame.setSize(500,700);                                                                         
	button.setBounds(150,300,200,20);
	textField.setBounds(150,250,200,40);
	textArea.setEditable(false);
	textArea.setBounds(110,220,280,25);
	textArea.append("Entrez votre identifiant pour vous connecter!");
	textArea.setBackground(Color.LIGHT_GRAY);
	frame.add(textArea);
	frame.add(button);
	frame.add(textField);
	frame.setLayout(null);
	frame.setVisible(true);
	
	
	}
	
	public static void main(String[] args) {
	initLogin();
	}
}


