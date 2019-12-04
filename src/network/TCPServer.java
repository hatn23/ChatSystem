package network;

import java.net.*;
import java.util.*;

import data.Message;
import data.User;

import java.io.*;


public class TCPServer extends Thread {
	private Socket socket;
	private User user;
	private Message message;
	private int port;
	private String serverIP;
	private PrintWriter output;
	private BufferedReader input;
	
	public TCPServer() {
		// TODO Auto-generated constructor stub
	}
  
}
  