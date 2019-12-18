package data;
import java.net.*;
import java.util.*;
import views.*;

public class Interface {
	private User user;
	private ArrayList<User> onlineList; 
	private Message message;
	private Home home;
	private final HashMap<String, ChatWindow> chatWindowForUser;//String -> ipAddress

	/* Constructors*/

	public Interface(User user) {
		this.user = user;
		this.onlineList = new ArrayList();
		this.message = null;
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
		return this.message.getMessage();
	}
	public void setMessage(String msg) {
		this.message.setMessage(msg);
	}

	public void addOnlineUser (User newUser) {
		this.onlineList.add(newUser);
	}

	public void removeOfflineUser(User user) {
		if (this.onlineList != null) {
			for (User u : this.onlineList) {
				if (u.getUserIP().equals(user.getUserIP()) && u.getPort() == user.getPort() && u.getPseudo().equals(user.getPseudo())) {
					this.onlineList.remove(u);
				}
			}
		}
	}


	public void updateOnlineList(User u) {
		if (u.getUserIP().equals(this.user.getUserIP())) {
			return;
		}

		for (User userInList : onlineList) {
			if (userInList.getUserIP().equals(u.getUserIP())) {
				System.out.println("[user] This user is already on the list!");
				if (userInList.getPseudo().equals(u.getPseudo())) {
				} else {
					System.out.println(" Name change " + userInList.getPseudo() + " > " + u.getPseudo());
					userInList.setPseudo(u.getPseudo());
					this.message.setMessage(" Name change " + userInList.getPseudo() + " > " + u.getPseudo());
				}
				if (u.isActive() == true) {
					System.out.println(" Status : connected ");
					userInList.setActive(true);
				} else {
					System.out.println(" Status : disconnected ");
					userInList.setActive(false);
				}
				return;
			}
		}
		System.out.println("[user] New User: UserIP>" + u.getUserIP() + " Pseudo>" + u.getPseudo());
		this.addOnlineUser(u);

	}
	public Object getHome() {
		return this.home;
	}

	public void updateHome() {
		// TODO Auto-generated method stub

	}

	public InetAddress getBroadcast() throws UnknownHostException {

		InetAddress myIpAddress = InetAddress.getByName(this.user.getUserIP());
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
			if (u.getUserIP().equals(IP)) {
				res = u.getPseudo();
			}
		}
		return res;
	}

	public void setChatWindowForPeer(User user, ChatWindow chatWindow) {
		this.chatWindowForUser.put(user.getUserIP(), chatWindow);
	}

	public ChatWindow getChatWindowForPeer(String ipAddress) {
		return this.chatWindowForUser.get(ipAddress);
	}

	public boolean existChatWindow(User user) {
		return this.chatWindowForUser.containsKey(user.getUserIP());
	}

	public void closeAllChatWindow() {
		for (ChatWindow c : chatWindowForUser.values()) {
			c.closeWindow();
		}
	}









}
