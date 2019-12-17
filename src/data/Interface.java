package data;
import java.net.*;
import java.util.*;
import views.*;

public class Interface {
	private User user;
	private ArrayList<User> onlineList; 
	private Message message;
	private Home home;
	
	/* Constructors*/
	
	public Interface(User user) {
		this.user = user;
		this.onlineList = new ArrayList();
		this.message = null;
		this.home = new Home(this);
	}
	
	/*Methods*/
	public User getUser() {
		return this.user;
	}
	public ArrayList<User> getOnlineList(){
		return this.onlineList;
	}
	public String getMessage() {
		return this.message.getMessage();
	}
	public void setMessage(String msg) {
		this.message.setMessage(msg);
	}

	public void addOnlineUser (User newUser) {
		this.onlineList.add(newUser);
	}

	public void removeOfflineUser(User user) {
		this.onlineList.remove(user);
	}
	
	/*update user >>>> Broadcast UDP*/


	public void updateOnlineList(User user2) {
		// TODO Auto-generated method stub
		
	}
	
	

	public void updateHome() {
		// TODO Auto-generated method stub
		
	}

	public String findPseudobyIP(String host) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getChatWindowForUser(String host) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getHome() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	

}
