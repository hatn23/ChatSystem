package network;

import java.net.*;
import java.util.*;
import java.io.*;
import data.User;
import data.Message;
public class TCPClient {
	
	private static Socket socket;
	private static User user;
	private static Message message;
	private int port;
	private static String serverIP;
	private static PrintWriter output;
	private static BufferedReader input;
	private static Scanner scanner;

	
	public static void main(String[] args) throws IOException {
		socket = new Socket(InetAddress.getByName(serverIP), user.getPort());
		output = new PrintWriter(socket.getOutputStream());
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		scanner = new Scanner(System.in);
		Thread send = new Thread(new Runnable() {
			public void run() {
				while(true) {
					message.setSender(user.getPseudo());
					message.setMessage(scanner.nextLine());
					output.println(message.getMessage());
					output.flush();
				}
			}
		});
		send.start();
		
		Thread receive = new Thread(new Runnable() {
			public void run() {
				while(true) {
					try {
						message.setMessage(input.readLine());
						while (message.getMessage()!= null){
							System.out.println(message.getSender() + " : " + message.getMessage());
							message.setMessage(input.readLine());
						}
						System.out.println("Server disconnected");
						output.close();
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		receive.start();
	}

}
