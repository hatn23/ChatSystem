package data;
import java.net.*;

public class User {
	private String host;
	private String pseudonyme;
	private boolean active;
	private int port;

	private boolean newMessage;
	
	public final static int portTCP = 1345;
	public final static int portUDP = 1234;
	
	/*InetAddress ia = InetAddress.getLocalHost();
	String addressIP = ia.getHostAddress();*/

	
	/* Constructors*/
	public User(String host) throws UnknownHostException {
		this.host = host;
		this.active = false;
		this.port = portTCP;
		this.newMessage = false;
	}
	
	public User(String host, String Pseudo) throws UnknownHostException {
		this.pseudonyme = Pseudo;
		this.host = host;
		this.active = false;
		this.port = portTCP;
		this.newMessage = false;
	}
	
	public User(String host,String Pseudo, int Port) throws UnknownHostException {
		this.pseudonyme = Pseudo;
		this.host = host;
		this.active = false;
		this.port = Port;
		this.newMessage = false;
	}
	
	public User(String host,String Pseudo, Boolean newMessage) throws UnknownHostException {
		this.pseudonyme = Pseudo;
		this.host = host;
		this.active = false;
		this.port = portTCP;
		this.newMessage = newMessage;
	}
	
	/*Methods*/
	
	public void setUserIP(String host) {
		this.host = host;
	}
	public String getHost() {
		return this.host;
	}
	/*verifier l'unicité!!!!!!!!!!!!!*/
	public void setPseudo(String NewPseudo) {
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
	public Boolean getStatusNewMessage() {
		return this.newMessage;
	}

	public void setNewMessage(boolean newMsg) {
		this.newMessage = newMsg;
		
	}
	@Override
    public String toString() {
        return this.pseudonyme + " (" + this.host + " : " + this.port + ")";
    }
}
