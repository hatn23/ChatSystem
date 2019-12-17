package network;
import java.net.*;
import java.io.*;

import data.Interface;
//import data.Message;
import data.User;

public class TCPServer {

	private ServerSocket serverSocket = null;
	private Socket chatSocket;
	private Interface inter;
	private boolean running = true;
	//private History history

	public TCPServer (Interface inter /*,History history*/) throws IOException {	
		this.inter = inter;
		//this.history = history;
		this.serverSocket = new ServerSocket(User.portTCP);
		this.inter.getUser().setPort(this.serverSocket.getLocalPort());
	}

	public void terminate() throws IOException {
		running = false;
		this.serverSocket.close();
		//this.chatSocket.close();
	}


	public void run() {
		try {
			while (running) {
				System.out.println("[TCP] " + inter.getUser().getPseudo() + " is listening by TCP at port " + inter.getUser().getPort() + "...");
				this.chatSocket = this.serverSocket.accept();

				/* Receive the message */
				BufferedReader in = new BufferedReader(new InputStreamReader(chatSocket.getInputStream()));
				String message = in.readLine();

				/* Write the message on the chat window between this node and client */
				//Interface client = new Interface (new User(chatSocket.getInetAddress().getHostAddress()));
				if (message != null) {
					System.out.println(message);

				}
				//chatSocket.close();
			}
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	}
}
