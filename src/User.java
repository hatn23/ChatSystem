import java.net.*;
import java.util.*;

public class User {
	public String UserID;
	private String UserIP;
	private String Pseudonyme;
	private boolean Active;
	private int Port;
	
	/*vérifier l'unicité!!!!!!!!!!!!!*/
	public void SetPseudo(String NewPseudo) {
		this.Pseudonyme = NewPseudo;
	}
	
	public String GetPseudo() {
		return this.Pseudonyme;
	}
	
	public String GetUserIP() {
		return this.UserIP;
	}
	private String GetUserID() {
		return this.UserID;
	}
	
	private Boolean IsActive() {
		return this.Active;
	}
	
	private void SetActive() {
		this.Active = true;
	}
	
	private void SetUserIP() {
		this.UserIP = getHostAddress();
	}
	
	
	
}
