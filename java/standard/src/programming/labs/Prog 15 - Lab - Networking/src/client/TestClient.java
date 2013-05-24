package client;

import java.awt.event.*;

public class TestClient implements ActionListener{
	private ChatClientFrame frame;
	
	public TestClient(){
		frame=new ChatClientFrame();
		frame.init(this); 
	}
	
	

	public void actionPerformed(ActionEvent ae){
		
	}
	
		

	public static void main(String[] args) {
		TestClient test=new TestClient();
		
	}
}
