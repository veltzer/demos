package server;

import java.net.*;
import java.io.*;
import java.util.*;

public class TestServer {
	private ServerSocket serverSocket;	
	private List sockets;
	
	public TestServer(){
		try {
			serverSocket=new ServerSocket(2525);
			sockets=new LinkedList();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void accept(){
		Socket tempSocket;
		while(true){		
			try {
				tempSocket=serverSocket.accept();
				sockets.add(tempSocket);
				new Thread(new ClientManager(tempSocket)).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void finalize(){
		try {
			for (int i = 0; i <sockets.size(); i++) {
				((Socket)sockets.get(i)).close();
			}
			serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/******** Inner Class **************************/
	private class ClientManager implements Runnable{
		private DataInputStream in;
		
		public ClientManager(Socket socket){
			try {
				in=new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void run(){
			String message=null;
			DataOutputStream out=null;
			
			while(true){
				try {
					message=in.readUTF();
					for (int i = 0; i <sockets.size(); i++) {
						out=new DataOutputStream(((Socket)sockets.get(i)).getOutputStream());
						out.writeUTF(message);
					}
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	/***********************************************/

	public static void main(String[] args) {
		TestServer test=new TestServer();
		test.accept();
	}
}
