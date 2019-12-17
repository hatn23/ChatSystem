package network;

import java.net.*;
import java.io.*;
import data.User;
import data.Message;

public class TCPClient /*extends JFrame*/ implements Runnable {

	private Socket chatSocket;
	//private Message message;
	private String message;
	private String serverIP;
	private PrintWriter output;
	private int port;

	public TCPClient(String IP, int port, String message) {
		this.serverIP = IP;
		this.port = port;
		//this.message.setMessage(message);
		this.message = message;

	}


	public void run() {
		try {
			//Request connection
			System.out.println("connecting to port "+ port +" and host "+ serverIP);
			chatSocket = new Socket(serverIP, User.portTCP);
			//Initialize output
			this.output = new PrintWriter( chatSocket.getOutputStream() );
			//Send message
			//output.println(message.getMessage());
			output.println(message);
			output.flush();
			//Close socket
			chatSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}


