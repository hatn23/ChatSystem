package network;
import java.net.*;
import java.util.*;
import java.io.*;
//import data.Message;
//import data.User;

public class TCPServer {
	
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	//private static User user;
	//private static Message message;
	private static String serverIP;
	private static PrintWriter output;
	private static BufferedReader input;
	private static Scanner scanner;
	private static String msg;
	

	public static void main(String[] args) throws IOException {
		//serverSocket = new ServerSocket(user.getPort());
		serverSocket = new ServerSocket(4000);
		clientSocket = serverSocket.accept();
		output = new PrintWriter(clientSocket.getOutputStream());
		input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		scanner = new Scanner(System.in);
		
		Thread send = new Thread(new Runnable() {
			public void run() {
				while(true) {
					/*message.setSender(user.getPseudo());*/
					//message.setMessage(scanner.nextLine());
					//output.println(message.getMessage());
					msg = scanner.nextLine();
	                output.println(msg);
					output.flush();
					}
				}
		});
		send.start();
		
		Thread receive = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						//message.setMessage(input.readLine());
						msg = input.readLine();
						//while user is still connected
						/*while (message.getMessage()!= null){
							System.out.println(message.getSender() + " : " + message.getMessage());
							message.setMessage(input.readLine());
						}*/
						while(msg!=null){
			                   System.out.println("Client : "+msg);
			                   msg = input.readLine();
			                }
						System.out.println("Client is disconnected");
						output.close();
						clientSocket.close();
						serverSocket.close();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		receive.start();
		
	}
	

}
