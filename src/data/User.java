package data;
import java.net.*;
import java.util.*;

public class User {
	private String userID;
	private String userIP;
	private String pseudonyme;
	private boolean active;
	private int port;
	
	private final static int portTCP = 1345;
	private final static int portUDP = 1234;
	
	/*InetAddress ia = InetAddress.getLocalHost();
	String addressIP = ia.getHostAddress();*/

	
	/* Constructors*/
	public User(String ID) throws UnknownUserIDException {
		this.userID = ID;
		this.active = false;
		this.port = portTCP;
	}
	
	public User(String ID, String Pseudo) throws UnknownUserIDException {
		this.userID = ID;
		this.pseudonyme = Pseudo;
		this.active = false;
		this.port = portTCP;
	}
	
	public User(String ID,String Pseudo, int Port) throws UnknownUserIDException {
		this.userID = ID;
		this.pseudonyme = Pseudo;
		this.active = false;
		this.port = Port;
	}
	
	/*Methods*/
	
	public void setUserID(String ID) {
		this.userID = ID;
	}
	public String getUserID() {
		return this.userID;
	}
	public void setUserIP(String IP) {
		this.userIP = IP;
	}
	public String getUserIP() {
		return this.userIP;
	}
	/*verifier l'unicité!!!!!!!!!!!!!*/
	public void SetPseudo(String NewPseudo) {
		this.pseudonyme = NewPseudo;
	}
	public String getPseudo() {
		return this.pseudonyme;
	}
	public void setActive() {
		this.active = true;
	}
	public Boolean isActive() {
		return this.active;
	}
	public int getPort() {
		 return this.port;
	}
	public void setPort(int Port) {
		 this.port = Port;
	}
	
}
