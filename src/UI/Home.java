package UI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import network.*;
import data.*;
import javax.swing.DefaultListModel;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import java.awt.event.*;
import java.io.IOException;
import java.net.UnknownHostException;

@SuppressWarnings("serial")
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
		scrollPane2 = new JScrollPane();
		logoutButton = new JButton();
		renameButton = new JButton();
		pseudoLabel = new JLabel();
		titleLabel = new JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
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
		notifications.setEditable(false);
		notifications.setColumns(15);
		notifications.setRows(5);
		notifications.setWrapStyleWord(true);
		notifications.setLineWrap(true);

		scrollPane2.setViewportView(notifications);

		label.setText("Notifications");
		renameButton.setText("Rename");
		renameButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				renameButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(titleLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup()
														.addComponent(pseudoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(label))
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addGroup(layout.createSequentialGroup()
																		.addComponent(logoutButton)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(renameButton))
																.addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(0, 4, Short.MAX_VALUE)))
						.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(pseudoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addGroup(layout.createSequentialGroup()
										.addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(logoutButton)
												.addComponent(renameButton)))
								.addComponent(scrollPane2))
						.addGap(18, 18, 18))
				);

		pack();


	}

	private void onlineListMouseClicked(MouseEvent evt) {
		if (!onlineList.isSelectionEmpty()) {
            String friend = onlineList.getSelectedValue();
            String seg[] = friend.split(":");

            /* Find an user when we know his nickname
            ** Then, open the chatwindow with him
             */
            try {
                Interface client = new Interface(new User(seg[0], seg[1]));
                if (seg[0].charAt(0) == "[".charAt(0)) {
                    String split[] = seg[0].split("] ");
                    client.getUser().setPseudo(split[1]);
                }

                this.inter.updateOnlineList(client.getUser());

                this.inter.updateHome();
                this.inter.getChatWindowForUser(client.getUser().getHost()).setTitle(client.getUser().getPseudo() + ": Chat");
                this.inter.getChatWindowForUser(client.getUser().getHost()).display();
            } catch (IOException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	}

	private void logoutButton(ActionEvent evt) {
		// Send broadcast to inform a disconnection
		try {
			new UDPClientThread().sendDisconnect(this.inter);
		}
		catch(UnknownHostException ex){
			Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
		}
		// Close Home Window and back to Login Window
		this.inter.closeAllChatWindow();
		this.setVisible(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.dispose();
		Login loginWindow = new Login(inter);
		loginWindow.setTitle("You are disconnected");
		loginWindow.display();
	}

	private void renameButtonActionPerformed(ActionEvent evt) {
		ChangePseudo changePseudoWindow = new ChangePseudo(this.inter, this);
		changePseudoWindow.display();
	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		try {
            new UDPClientThread().sendDisconnect(inter);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
		this.inter.closeAllChatWindow();
		this.setVisible(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//this.dispose();
		Login loginWindow = new Login(inter);
		loginWindow.setTitle("You are disconnected");
		loginWindow.display();
	}

	//Methods
	
	public void setPseudoLabel(String label) {
		pseudoLabel.setText(label);
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

	public DefaultListModel<String> getOnlineList() {
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
	private JScrollPane scrollPane2;
	private JButton logoutButton;
	private JButton renameButton;
	private JLabel pseudoLabel;
	private JLabel titleLabel;


}
