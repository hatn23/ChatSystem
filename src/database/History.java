package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.Message;
import data.User;

	
public class History {
	
	//Create message Table
	public static void Create_message_Table (Message message) throws SQLException {
		Connection conn=null;
		try {
			conn = Database.establish_Connection();
			Statement stmt = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Message" + 
					"	(Sender_IP VARCHAR(15)," + 
					"	Receiver_IP VARCHAR(15)," + 
					"	Text TEXT," + 
					"	Time DATETIME);" ; 
			
			stmt.executeUpdate(sql);
			stmt.close();
		}finally {
			if (conn!= null) 
				conn.close();
		}
	}
		
	//Insert a new Message
	public static void Insert_new_Message (Message msg) throws SQLException {
		Connection conn = null;
		try {
			conn=Database.establish_Connection();
			String sql = ("INSERT into Message (Sender_IP , Receiver_IP, Text, Time) ") 
					+ (" VALUES (" + msg.getSender() + "," + msg.getReceiver() 
					+ msg.getMessage() + "," + "msg.getDate()" +");");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		
			stmt.close();
		} finally {
			if (conn !=null)
				conn.close();
		}
	} 
	
	//Get Message History
	public static ArrayList<String> get_History(User user1 , User user2) throws SQLException {
		Connection conn=null;
		String IP1 = user1.getHost();
		String IP2 = user2.getHost();
		
		ArrayList<String> results= new ArrayList<String>();
		try {
			conn = Database.establish_Connection();
			String sql = "SELECT Text " + "FROM Message "
					+ "WHERE (Sender_IP = " + IP1 + " AND Receiver_IP = " + IP2 + ")" 
					+"OR (Sender_IP = " + IP2 + " AND Receiver_IP = " + IP1 +" ) "
							+ "ORDER BY Time DESC;";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				results.add(rs.getString("IP_address"));
			} 
		}finally {
			if (conn!= null)
				conn.close();
		}

		
		return results;
	}
	
}
