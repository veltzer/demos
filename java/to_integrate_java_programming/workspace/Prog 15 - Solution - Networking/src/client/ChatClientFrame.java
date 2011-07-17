package client;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ChatClientFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextArea txtArea;
	private JTextField txtField;
	private JPanel panel;
	private JButton btnSend;
	
	public ChatClientFrame(){
		txtArea=new JTextArea();
		txtField=new JTextField();
		panel=new JPanel();
		btnSend=new JButton("send");		
	}
	
	public void init(TestClient t){
		getContentPane().add(txtArea,BorderLayout.CENTER);
		getContentPane().add(txtField,BorderLayout.SOUTH);
		getContentPane().add(panel,BorderLayout.EAST);
		panel.add(btnSend);
		btnSend.addActionListener(t);
		txtField.addActionListener(t);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
		setSize(400,300);
		setVisible(true);
	}
	
	void addMessage(String message){
		txtArea.append(message+"\n");
	}
	
	String getMessage(){
		String str=txtField.getText();
		txtField.setText("");
		return str;
	}
}
