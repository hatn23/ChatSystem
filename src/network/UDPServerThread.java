package network;

import java.net.UnknownHostException;
import data.*;

public class UDPServerThread implements ServerThread {
	
	public void sendBroadcast(Interface inter) throws UnknownHostException{
        Thread t = new Thread (new UDPServer(inter, "broadcast"));
        t.start();
    }
    
    public void sendDisconnect(Interface inter) throws UnknownHostException{
        Thread t = new Thread (new UDPServer(inter, "disconnect"));
        t.start();
    }
    
    public void sendRename(Interface inter) throws UnknownHostException{
        Thread t = new Thread (new UDPServer(inter, "rename"));
        t.start();
    }

	@Override
	public void sendMessageTo(String host, int port, String message) throws Exception {
		Thread t = new Thread (new UDPServer(host,port,message));
        t.start();
	}

}
