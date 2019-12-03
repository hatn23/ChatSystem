package network;
import java.io.*;
import java.net.*;
import data.User;
import data.Message;
public class InterfaceReseaux {
	private User user;
	private int port;
	private ServerSocket server;
	private Socket client;
	private String message;
	
	
	/* Constructors */
	
	public InterfaceReseaux(User user) throws IOException {
		this.user = user;
		this.port = user.getPort();
		this.server = new ServerSocket(user.getPort());
		this.client = new Socket(user.getUserIP(),user.getPort());
	}
	
	public void Envoi() throws IOException {
		OutputStream output = this.client.getOutputStream();
		BufferedReader input = new BufferedReader (new InputStreamReader (this.client.getInputStream()));
	
	}
}
