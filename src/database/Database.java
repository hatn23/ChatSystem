package database;
import java.sql.*;
import java.util.*;

import data.User;

public class Database {
	static Properties props = new Properties();
	private static String url = props.getProperty("jbdc.url");
	//private static String login = props.getProperty("jbdc.login");
	//private static String password = props.getProperty("jbdc.password");
	
	public static void Driver() throws ClassNotFoundException{
		Class.forName(props.getProperty("jbdc.driver.class"));
	}
	
	public static Connection establish_Connection() throws SQLException {
		Connection conn = DriverManager.getConnection(url);
		return conn;
	}
	
	//Create User Table
	public static void Create_User_Table() throws SQLException {
		Connection conn=null;
		try {
			conn = establish_Connection();
			Statement stmt = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS User" + 
					"    (id_utilisateur INTEGER PRIMARY KEY AUTOINCREMENT," + 
					"    Username VARCHAR(20)," + 
					"    IP_address VARCHAR(15));"; 
			stmt.executeUpdate(sql);
			stmt.close();
		}finally {
			if (conn!= null) 
				conn.close();
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
	public static String get_Username(User user) throws SQLException {
		Connection conn = null;
		String Pseudo=null;
		String IP = user.getHost();
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
	
	
	
}

