package network;

import java.net.*;
import java.util.*;
import java.io.*;
//import data.User;
//import data.Message;
public class TCPClient {
	
	private static Socket clientSocket;
	//private static User user;
	//private static Message message;
	private static String serverIP;
	private static PrintWriter output;
	private static BufferedReader input;
	private static Scanner scanner;
	private static String msg;
	
	public static void main(String[] args) throws IOException {
		//clientSocket = new Socket(InetAddress.getByName(serverIP), user.getPort());
		clientSocket = new Socket("127.0.0.1",4000);
		output = new PrintWriter(clientSocket.getOutputStream());
		input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		scanner = new Scanner(System.in);
		Thread send = new Thread(new Runnable() {
			public void run() {
				while(true) {
					/*message.setSender(user.getPseudo());
					message.setMessage(scanner.nextLine());
					output.println(message.getMessage());*/
					msg = scanner.nextLine();
	                output.println(msg);
					output.flush();
				}
			}
		});
		send.start();
		
		Thread receive = new Thread(new Runnable() {
			public void run() {
				while(true) {
					try {
						/*message.setMessage(input.readLine());
						while (message.getMessage()!= null){
							System.out.println(message.getSender() + " : " + message.getMessage());
							message.setMessage(input.readLine());*/
						msg = input.readLine();
		                while(msg!=null){
		                	System.out.println("Serveur : "+msg);
		                	msg = input.readLine();
		                 }
						
						System.out.println("Server disconnected");
						output.close();
						clientSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		receive.start();
	}

}
