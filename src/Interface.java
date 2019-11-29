import java.net.*;
import java.util.*;

public class Interface {
	private User user;
	private ArrayList<User> onlineList; 
	private String message;
	
	/* Constructors*/
	
	public Interface(User user) {
		this.user = user;
		this.onlineList = new ArrayList();
		this.message = null;
	}
	
	/*Methods*/
	public User getUser() {
		return this.user;
	}
	public ArrayList<User> getOnlineList(){
		return this.onlineList;
	}
	public String getMessage() {
		return this.message;
	}
	public void setMessage(String msg) {
		this.message = msg;
	}

	public void addOnlineUser (User newUser) {
		this.onlineList.add(newUser);
	}

	public void removeOfflineUser(User user) {
		this.onlineList.remove(user);
	}
	
	/*update user >>>> Broadcast UDP*/
	public void updateOnlineList() {
	}
	
	
	
	
	
	
	

}
