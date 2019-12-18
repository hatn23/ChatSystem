package views;
import java.awt.*;
import javax.swing.*;
public class ChatWindow {
	private static JFrame frame = new JFrame ("Chat Session");
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
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		initChatWindow();
	}


	public void closeWindow() {
		// TODO Auto-generated method stub
		
	}

	

}
