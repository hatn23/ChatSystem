package network;

import java.net.*;
import data.Interface;
import data.User;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPClient implements Runnable {
	private DatagramSocket dgramSocket;
    private final DatagramPacket outPacket;
    @SuppressWarnings("unused")
	private final String message;
	
	public UDPClient (String host, int port, String message) throws UnknownHostException {
		this.message = message;
        outPacket = new DatagramPacket(message.getBytes(), message.length());
        outPacket.setAddress(InetAddress.getByName(host));
        outPacket.setPort(port);
    }
	
	public UDPClient (Interface inter, String message) throws UnknownHostException {
		this.message = inter.getUser().getPseudo() + ":" + inter.getUser().getPort()+":"+ message;
        outPacket = new DatagramPacket(message.getBytes(), message.length());
        outPacket.setAddress(InetAddress.getByName("255.255.255.255"));
        outPacket.setPort(User.portUDP);
    }

	
	  
	public void run() {
		 try {
			 this.dgramSocket = new DatagramSocket();
	            dgramSocket.send(outPacket);
	            dgramSocket.close();
		 }
		 
		 catch (SocketException e){
			 Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, e);
		 }
		 
		 catch (IOException e){
			 Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, e);
		 }
	 }

}
