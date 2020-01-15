package data;
import java.net.*;
import java.util.*;

import UI.*;

public class Interface {
	private User user;
	private ArrayList<User> onlineList; 
	private String message = " ";
	private Home home;
	private final HashMap<String, ChatWindow> chatWindowForUser;

	/* Constructors*/

	public Interface(User user) {
		this.user = user;
		this.onlineList = new ArrayList<User>();
		this.home = new Home(this);
		this.chatWindowForUser = new HashMap<>();

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
		if (this.onlineList != null) {
			for (User u : this.onlineList) {
				if (u.getHost().equals(user.getHost()) && u.getPort() == user.getPort() && u.getPseudo().equals(user.getPseudo())) {
					this.onlineList.remove(u);
				}
			}
		}
	}


	public void updateOnlineList(User u) {
		if (u.getHost().equals(this.user.getHost())) {
			return;
		}

		for (User userInList : onlineList) {
			if (userInList.getHost().equals(u.getHost())) {
				System.out.println("[user] This user is already on the list!");
				if (userInList.getPseudo().equals(u.getPseudo())) {
				} else {
					System.out.println(" Name change " + userInList.getPseudo() + " > " + u.getPseudo());
					userInList.setPseudo(u.getPseudo());
					this.message = " Name change " + userInList.getPseudo() + " > " + u.getPseudo();
				}
				if (u.isActive() == true) {
					System.out.println(" Status : connected ");
					userInList.setActive(true);
				} else {
					System.out.println(" Status : disconnected ");
					userInList.setActive(false);
				}
				if (u.getStatusNewMessage() == false) {
                    userInList.setNewMessage(false);
                } else {
                    System.out.println(" New Message Status : new message ");
                    userInList.setNewMessage(true);
                }
				return;
			}
		}
		System.out.println("[user] New User: UserIP>" + u.getHost() + " Pseudo>" + u.getPseudo());
		this.addOnlineUser(u);

	}
	public Home getHome() {
		return this.home;
	}

	public void updateHome() {
		this.home.getOnlineList().removeAllElements();
        for (User u : this.getOnlineList()) {
            if (u.isActive() == true) {
                if (u.getStatusNewMessage()) {
                    this.home.getOnlineList().addElement("[!] " + u.getPseudo() + ":" + u.getHost());
                } else {
                    this.home.getOnlineList().addElement(u.getPseudo() + ":" + u.getHost());
                }
                if (!this.existChatWindow(u)) { 
                    ChatWindow chatWindow = new ChatWindow(this, new Interface(u));
                    this.setChatWindowForUser(u, chatWindow);
                }
            }
        }

	}

	public InetAddress getBroadcast() throws UnknownHostException {

		InetAddress myIpAddress = InetAddress.getByName(this.user.getHost());
		NetworkInterface temp;
		InetAddress iAddr = null;
		try {
			temp = NetworkInterface.getByInetAddress(myIpAddress);
			List<InterfaceAddress> addresses = temp.getInterfaceAddresses();

			for (InterfaceAddress inetAddress : addresses) {
				iAddr = inetAddress.getBroadcast();
			}
			System.out.println("Call in User.getBroadcast : " + iAddr);
			return iAddr;

		} catch (SocketException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String findPseudobyIP(String IP) {
		String res = "";
		for (User u : this.getOnlineList()) {
			if (u.getHost().equals(IP)) {
				res = u.getPseudo();
			}
		}
		return res;
	}

	public void setChatWindowForUser(User user, ChatWindow chatWindow) {
		this.chatWindowForUser.put(user.getHost(), chatWindow);
	}

	public ChatWindow getChatWindowForUser(String ipAddress) {
		return this.chatWindowForUser.get(ipAddress);
	}

	public boolean existChatWindow(User user) {
		return this.chatWindowForUser.containsKey(user.getHost());
	}

	public void closeAllChatWindow() {
		for (ChatWindow c : chatWindowForUser.values()) {
			c.closeWindow();
		}
	}

	public boolean checkPseudo() {
		 Boolean res = true;
	        for (User user : this.getOnlineList()) {
	            System.out.println(user.getPseudo());
	            if (this.getUser().getPseudo().equals(user.getPseudo())) {
	                res = false;
	            }
	        }
	        return res;
	}
	









}
