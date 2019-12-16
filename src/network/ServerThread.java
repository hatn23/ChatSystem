package network;


public interface ServerThread {
	
	void sendMessageTo (String host, int port, String message) throws Exception;

}
