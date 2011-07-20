package client;

import java.io.*;
import java.net.*;
import java.awt.event.*;

public class TestClient implements ActionListener{
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private ChatClientFrame frame;
	
	public TestClient(){
		try {
			socket=new Socket("localhost",2525);
			in=new DataInputStream(socket.getInputStream());
			out=new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame=new ChatClientFrame();
		frame.init(this); 
	}
	
	public void read(){
		try {
			while(true){
				frame.addMessage(in.readUTF());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public void actionPerformed(ActionEvent ae){
		try {
			out.writeUTF(frame.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalize(){
		try {
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	

	public static void main(String[] args) {
		TestClient test=new TestClient();
		test.read();
	}
}