package views;
import javax.swing.*;

import network.*;
import data.*;
import javax.swing.DefaultListModel;
import javax.swing.WindowConstants;

public class Home extends javax.swing.JFrame  {
	
	private Interface inter;
    DefaultListModel<String> listFriendsOnlineModel;
    static Thread listenTCP = null;
    static TCPServer runnableTCP = null;
    //public static History history;
    
    public Home(Interface inter/*,History history*/) {
        this.inter = inter;
        this.listFriendsOnlineModel = new DefaultListModel<>();
        for(User u : inter.getOnlineList()){
            listFriendsOnlineModel.addElement(u.getPseudo()+ ":"+ u.getUserIP()+":"+u.getPort());
        }
        initWindows();
    }
  
    
    private void initWindows() {
    	
    	JFrame frame = new JFrame ("Login");
    	JTextField textField = new JTextField("TextField");
    	JTextArea messageArea = new JTextArea("TextArea");
    	JButton button = new JButton ("Send");

    }
    
	
	
	

}
