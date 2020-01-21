package network;

import java.net.*;
import java.io.*;
import data.*;


import java.util.logging.Level;
import java.util.logging.Logger;


public class UDPServer implements Runnable {
	
	 /*
     * Attributs
     */
	 private final Interface inter;
	 private final DatagramSocket dgramSocket;
	 private final DatagramPacket inPacket;
	 private volatile boolean running = true;

	 
	 public UDPServer(Interface inter) throws SocketException{
            this.inter = inter;
            this.dgramSocket = new DatagramSocket(User.portUDP);
            byte[] buffer = new byte[256];
            this.inPacket = new DatagramPacket(buffer, buffer.length);
	 }
	 
	
	public void terminate() throws SocketException{
		running = false;
		dgramSocket.close();
		
	}

	@SuppressWarnings("unused")
	@Override
	public void run() {
		try {
			System.out.println("[UDP] " + inter.getUser().getPseudo() + " is listening by UDP at port " + User.portUDP + "...");
			while (running) {
				this.dgramSocket.receive(this.inPacket);
                String msg = new String(inPacket.getData(), 0, inPacket.getLength());
                String seg[] = msg.split(":");
                String pseudo = seg[0];
                int port = Integer.parseInt(seg[1]);
                msg = seg[2];
                String host = inPacket.getAddress().getHostAddress();
                
                if (msg.equals("broadcast") && !host.equals(inter.getUser().getHost())) {
                    System.out.println("[bcst] " + host + " sends a " + msg);
                    new UDPClientThread().sendMessageTo(host, User.portUDP, this.inter.getUser().getPseudo() + ":" + this.inter.getUser().getPort() + ":OK");
                    this.inter.updateOnlineList(new User(pseudo, host));
                    this.inter.updateHome();

                }
                
                if (msg.equals("disconnect") && !host.equals(inter.getUser().getHost())) {
                    System.out.println("[dis] " + host + " sends a " + msg);
                    User usr = new User(pseudo, host);
                    usr.setDisconnect(true);
                    this.inter.updateOnlineList(usr);
                    this.inter.updateHome();
                }
                
                if (msg.equals("rename") && !host.equals(inter.getUser().getHost())) {
                    System.out.println("[rnm] " + host + " sends a " + msg);
                    String oldName = this.inter.findPseudobyIP(host);
                    this.inter.updateOnlineList(new User(pseudo, host));
                    this.inter.updateHome();
                    this.inter.getChatWindowForUser(host).setTitle(pseudo + ": Chat");
                    this.inter.getHome().writeNotification(oldName + " changed name to " + pseudo);
                }
                
                if (msg.equals("OK")) {
                    System.out.println("[vcst] " + host + " responds " + msg);
                    this.inter.updateOnlineList(new User(pseudo, host));
                    this.inter.updateHome();
                }
			}
			
		}
		catch (IOException e) {
			System.out.println("ERROR: Connection failure with: " + inPacket.getAddress().getHostAddress());
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, e);
		}
		catch (Exception ex) {
            System.out.println("ERROR: Connection failure with: " + inPacket.getAddress().getHostAddress());
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}

