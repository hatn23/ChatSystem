package data;

import java.util.ArrayList;

public class ChatHistory {
	
	private ArrayList<Message> history;
	private int ind = 0;
	
	public ChatHistory() {
		this.history = new ArrayList<>(200);
	}
	
	 public boolean existHistory(Message message) {
	        boolean res = false;
	        for (int i = 0; i < ind; i++) {
	            if (this.history.get(i).getSenderHost().equals(message.getSenderHost()) && this.history.get(i).getReceiverHost().equals(message.getReceiverHost())) {
	                res = true;
	                break;
	            }
	        }
	        return res;
	    }
	
	public void addHistory(Message message) {
		if (!this.existHistory(message)) {
			this.history.add(message);
			ind++;
		}
	}
	
	 public Message getMessage(String source, String target) {
	        Message res = null;
	        for (int i = 0; i < ind; i++) {
	            if (this.history.get(i).getSenderHost().equals(source) && this.history.get(i).getReceiverHost().equals(target)) {
	                res = this.history.get(i);
	                break;
	            }
	        }
	        return res;
	    }
	 
	 @Override
	    public String toString() {
	        String res = "";
	        for (int i = 0; i < ind; i++) {
	            res = res + "[" + this.history.get(i).getSenderHost() + " | " + this.history.get(i).getReceiverHost() + "]" + System.lineSeparator();
	        }
	        return res;
	    }
	
	
	

}
