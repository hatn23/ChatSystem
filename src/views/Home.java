package views;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;

import network.*;
import data.*;
import javax.swing.DefaultListModel;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;

public class Home extends javax.swing.JFrame  {
	
	private Interface inter;
    DefaultListModel<String>  onlineList;
    static Thread listenTCP = null;
    static TCPServer runnableTCP = null;
    //public static History history;
    
    public Home(Interface inter/*,History history*/) {
        this.inter = inter;
        this.onlineList = new DefaultListModel<>();
        for(User u : inter.getOnlineList()){
        	onlineList.addElement(u.getPseudo()+ ":"+ u.getHost()+":"+u.getPort());
        }
        initWindows();
    }
  
    
    private void initWindows() {
    
    	JFrame frame = new JFrame ("Login");
    	JTextField textField = new JTextField("TextField");
    	JTextArea messageArea = new JTextArea("TextArea");
    	JButton button = new JButton ("Send");

    }
    
    private void onlineListClick() {
    	// TODO Auto-generated method stub
    }
    
    private void logOutButton() {
    	// TODO Auto-generated method stub
    }
    
    private void renameButton() {
    	// TODO Auto-generated method stub
    }
    
    private void closeWindow() {
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
        this.onlineList.removeElement(user.getPseudo() + ":" + user.getHost() + ":" + user.getPort());
    }
    
    public void display() {
    	this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public DefaultListModel getOnlineList() {
        return onlineList;
    }

    public void writeNotification(String s) throws BadLocationException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm, dd/MM/yyyy] - ");
        notifications.getDocument().insertString(0, sdf.format(cal.getTime()) + s + System.lineSeparator(), null);
        notifications.setCaretPosition(0);
    }
    
    //Variables
    private javax.swing.JTextArea notifications;
    
    
   
	
	
	

}
