package network;
import java.net.*;
import java.util.*;
import java.io.*;
//import data.Message;
//import data.User;
public class UDPServer {
	private static ServerSocket serverSocket;
	private static DatagramPacket packet;
	private static Socket socket;
	
	public static void main(String[] args) throws IOException {
		packet = new DatagramPacket(null, 0);
		
		Thread sendBroacast = new Thread (new Runnable() {
			public void run() {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	
	
	
	
	
	
}
