package data;
import java.net.*;
import java.util.*;

public class User {
	private String userIP;
	private String pseudonyme;
	private boolean active;
	private int port;
	
	public final static int portTCP = 1345;
	public final static int portUDP = 1234;
	
	/*InetAddress ia = InetAddress.getLocalHost();
	String addressIP = ia.getHostAddress();*/

	
	/* Constructors*/
	public User(String IP) throws UnknownHostException {
		this.userIP = IP;
		this.active = false;
		this.port = portTCP;
	}
	
	public User(String IP, String Pseudo) throws UnknownHostException {
		this.pseudonyme = Pseudo;
		this.userIP = IP;
		this.active = false;
		this.port = portTCP;
	}
	
	public User(String IP,String Pseudo, int Port) throws UnknownHostException {
		this.pseudonyme = Pseudo;
		this.userIP = IP;
		this.active = false;
		this.port = Port;
	}
	
	/*Methods*/
	
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
	public void setActive(boolean active) {
		this.active = active;
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
