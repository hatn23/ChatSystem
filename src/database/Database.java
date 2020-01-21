package database;
import java.sql.*;
import java.util.*;

import data.Message;
import data.User;

public class Database {
	
	private static Database instance = null;
	
	private Database() throws SQLException {
			Driver();
			Create_User_Table();
			Create_message_Table();
	}
	
	public static Database get_Instance() throws SQLException {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	public static void Driver(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection establish_Connection() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:sqlite:ChatSystem.db");
		return conn;
	}
	
	
	
	//Create User Table
	public static void Create_User_Table() {
		Connection conn=null;
		String sql = "CREATE TABLE IF NOT EXISTS User" + 
				"    (id_utilisateur INTEGER PRIMARY KEY AUTOINCREMENT," + 
				"    Username VARCHAR(20)," + 
				"    IP_address VARCHAR(15));"; 
		try {
			try {
				conn = establish_Connection();
				Statement stmt = conn.createStatement();
				
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}finally {
			if (conn!= null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	//Verify the user name is unique
	public static boolean is_Unique (String pseudo) throws SQLException {
		ArrayList<String> pseudos = get_All_Usernames();
		return !(pseudos.contains(pseudo));
	}
	
	//Update a user name
	public static void Update_Username(User user_update) throws SQLException{
		Connection conn = null;
		String IP = user_update.getHost();
		String new_pseudo= user_update.getPseudo();
		try {
			conn = establish_Connection();
			Statement stmt = conn.createStatement();
			String sql = "UPDATE User" +
						 " set Username = '" + new_pseudo +
						 "' where IP_address = '" + IP + "';" ;
			stmt.executeUpdate(sql);
			
		}finally {
			if (conn!= null)
				conn.close();
		}
	}
	
	
	//Insert a new user
	public static void Insert_new_User (User user) throws SQLException {
		Connection conn = null;
		try {
			conn=establish_Connection();
			String pseudo = user.getPseudo();
			String IP = user.getHost();

			String sql = "INSERT INTO User (Username , IP_address) " + " VALUES ( '" + pseudo + "','" + IP + "');";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			stmt.close();
		} finally {
			if (conn !=null)
				conn.close();
		}
	} 
	
	//Get all the User names
	public static ArrayList<String> get_All_Usernames() throws SQLException{
		Connection conn=null;
		ArrayList<String> results= new ArrayList<String>();
		try {
			conn = establish_Connection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT DISTINCT Username" + " FROM User;");
			while(rs.next()) {
				results.add(rs.getString("Username"));
			} 
			rs.close();
			st.close();
		}finally {
			conn.close();
		}
		return results;
	}	
	
	//Get all the IP address
	public static ArrayList<String> get_All_IPaddress() throws SQLException{
		Connection conn=null;
		ArrayList<String> results= new ArrayList<String>();
		try {
			conn = establish_Connection();
			String sql = "SELECT DISTINCT IP_address" + " FROM User;";
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

	//Get the User name of a known user
	public static String get_Username(String IP) throws SQLException {
		Connection conn = null;
		String Pseudo=null;
		try {
			conn=establish_Connection();
			String sql = "SELECT Username FROM User" + 
					   " WHERE IP_address = '" + IP + "';";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			Pseudo = rs.getString("Username");
			rs.close();
			st.close();
		} finally {
			if (conn!= null)
				conn.close();
		}
		return Pseudo;
	}
	//Get the IP address of a known user
	public static String get_IPaddress(User user) throws SQLException {
		Connection conn = null;
		String IP=null;
		String Username = user.getPseudo();
		try {
			conn=establish_Connection();
			String sql = "SELECT IP_address FROM User" + 
					     " WHERE Username = '" + Username + "';";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			IP = rs.getString("IP_address");
			rs.close();
			st.close();
		} finally {
			if (conn!= null)
				conn.close();
		}
		return IP;
	}
	
	public static Boolean Ip_adress_existant(String IP) throws SQLException {
		ArrayList<String> All_IPs = Database.get_All_IPaddress();
		return All_IPs.contains(IP);
	}
	
	//Create message Table
	public static void Create_message_Table() throws SQLException {
		Connection conn=null;
		try {
			conn = Database.establish_Connection();
			Statement stmt = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS Message" + 
					"	(Sender_IP VARCHAR(15)," + 
					"	Receiver_IP VARCHAR(15)," + 
					"	Text TEXT," + 
					"	Time DATETIME NOT NULL DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime')));" ; 
			
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
			String sql = ("INSERT into Message (Sender_IP , Receiver_IP, Text) ") 
					+ (" VALUES ('" + msg.getSenderHost() + "','" + msg.getReceiverHost() +"','" 
					+ msg.getMessage().toString() + "');");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		
			stmt.close();
		} finally {
			if (conn !=null)
				conn.close();
		}
	} 
	
	//Get Message History
	public static ArrayList<String> get_History(String IP1 , String IP2) throws SQLException {
		Connection conn=null;
		
		ArrayList<String> results= new ArrayList<String>();
		try {
			conn = Database.establish_Connection();
			String sql = "SELECT * " + " FROM Message "
					+ "where (Sender_IP = '" + IP1 + "' AND Receiver_IP = '" + IP2 + "')" 
					+"OR (Sender_IP = '" + IP2 + "' AND Receiver_IP = '" + IP1 +"' ); "
							+ "ORDER BY Time DESC;";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				results.add(rs.getString("Text"));
			} 
		}finally {
			if (conn!= null)
				conn.close();
		}

		
		return results;
	}
	
	//Get the time of the message
	public static String get_Time(String Text) throws SQLException {
		Connection conn = null;
		String Time=null;
		try {
			conn=Database.establish_Connection();
			String sql = "SELECT Time FROM Message" + 
					     " WHERE Text = '" + Text + "';";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			Time = rs.getString("Time");
			rs.close();
			st.close();
		} finally {
			if (conn!= null)
				conn.close();
		}
		return Time;
	}
	
	//Get the Receiver of the message
	public static String get_Receiver(String Text) throws SQLException {
		Connection conn = null;
		String Time=null;
		try {
			conn=Database.establish_Connection();
			String sql = "SELECT Receiver_IP FROM Message" + 
					     " WHERE Text = '" + Text + "';";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			Time = rs.getString("Receiver_IP");
			rs.close();
			st.close();
		} finally {
			if (conn!= null)
				conn.close();
		}
		return Time;
	}
	
	//Get the Sender of the message
	public static String get_Sender(String Text) throws SQLException {
		Connection conn = null;
		String Time=null;
		try {
			conn=Database.establish_Connection();
			String sql = "SELECT Sender_IP FROM Message" + 
					     " WHERE Text = '" + Text + "';";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			Time = rs.getString("Sender_IP");
			rs.close();
			st.close();
		} finally {
			if (conn!= null)
				conn.close();
		}
		return Time;
	}
	
	//An history does exist between 2 users
	public static Boolean History_exist(User user1, User user2) throws SQLException {
		return !(get_History(user1.getHost(),user2.getHost()).isEmpty());
	}
	
	public static void save_message(String Sender, String Reciever, String Msg) throws SQLException {
		Message message= new Message(Sender,Reciever);
		message.addMessage(Msg);
		Database.Insert_new_Message(message);
	}

	
	
	
	
}

