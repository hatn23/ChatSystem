package views;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;

import network.*;
import data.*;
import javax.swing.DefaultListModel;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import java.awt.event.*;

public class Home extends javax.swing.JFrame  {

	private Interface inter;
	DefaultListModel<String>  onlineListModel;
	static Thread listenTCP = null;
	static TCPServer runnableTCP = null;
	//public static History history;

	public Home(Interface inter/*,History history*/) {
		this.inter = inter;
		this.onlineListModel = new DefaultListModel<>();
		for(User u : inter.getOnlineList()){
			onlineListModel.addElement(u.getPseudo()+ ":"+ u.getHost()+":"+u.getPort());
		}
		initWindows();
	}


	private void initWindows() {

		notifications = new JTextArea();
		onlineList = new  JList<>();
		label = new JLabel();
		scrollPane = new JScrollPane();
		logoutButton = new JButton();
		renameButton = new JButton();
		pseudoLabel = new JLabel();
		titleLabel = new JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				closeWindow(evt);
			}
		});

		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setText("HOME");

		onlineList.setModel(onlineListModel);
		onlineList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		onlineList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				onlineListMouseClicked(evt);
			}
		});
		
		scrollPane.setViewportView(onlineList);
		pseudoLabel.setText("Nickname : ");
		logoutButton.setText("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			logoutButton(evt);
			}
		});
		


	}

	private void onlineListMouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
	}

	private void logoutButton(ActionEvent evt) {
		// TODO Auto-generated method stub
	}

	private void renameButton() {
		// TODO Auto-generated method stub
	}

	private void closeWindow(java.awt.event.WindowEvent evt) {
		// TODO Auto-generated method stub
	}

	//Methods
	public void setPseudoLabel() {
		// TODO Auto-generated method stub
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

	public DefaultListModel getOnlineList() {
		return onlineListModel;
	}

	public void writeNotification(String s) throws BadLocationException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm, dd/MM/yyyy] - ");
		notifications.getDocument().insertString(0, sdf.format(cal.getTime()) + s + System.lineSeparator(), null);
		notifications.setCaretPosition(0);
	}

	//Variables
	private JList<String> onlineList;
	private JTextArea notifications;
	private JLabel label;
	private JScrollPane scrollPane;
	private JButton logoutButton;
	private JButton renameButton;
	private JLabel pseudoLabel;
	private JLabel titleLabel;


}
