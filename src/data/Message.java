package data;
import java.util.*;
public class Message {
	private User sender = null;
	private User receiver = null;
	private String senderHost;
	private String receiverHost;
	private int receiverPort;
	private ArrayList<String> message;
	private int ind = 0;
	
	
	public Message(User from, User to) {
		this.sender = from;
		this.receiver = to;
		this.senderHost = from.getHost();
		this.receiverHost= to.getHost();
		this.message = new ArrayList<>(1000);
		
	}
	
	public User getSender() {
		return this.sender;
	}

	public User getReceiver() {
		return this.receiver;
	}
	
	public String getSenderHost() {
		return this.senderHost;
	}

	public String getReceiverHost() {
		return this.receiverHost;
	}
	
	public void addMessage(String message) {
        this.message.add(message);
        ind++;
    }
	
	public ArrayList<String> getMessage() {
        return this.message;
    }
	
	public int getReceiverPort() {
        return this.receiverPort;
    }
	
	@Override
    public String toString() {
        String res = "";
        for (int i = 0; i < ind; i++) {
            res = res + this.message.get(i) + System.lineSeparator();
        }
        return res;
    }
	
	
	
	

}
