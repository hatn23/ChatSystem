package data;
import java.util.*;
public class Message {
	private String from = null;
	private Date date = null;
	private String message = "";
	private String to = null;

	public Message() {
	}
	
	public Message(String from, Date date, String message, String to) {
		this.from = from;
		this.date = date;
		this.message = message;
		this.to = to;
	}
	
	/*public boolean isReceivedMessage() {
		return (this.to == null);
	}
	public boolean isSentMessage(){
		return (this.from == null);
	}
	*/
	
	public String getSender() {
		return this.from;
	}
	public void setSender(String from) {
		this.from = from;
	}
	public String getReceiver() {
		return this.to;
	}
	public void Receiver(String to) {
		this.to = to;
	}
	public Date getDate() {
		return this.date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
