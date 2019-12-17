package network;

public class TCPClientThread implements ClientThread {

	@Override
	public void sendMessageTo(String host, int port, String message) throws Exception {
		Thread t = new Thread( new TCPClient(host,port,message));
		t.start();

		
	}
	

}
