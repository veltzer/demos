package programming.labs.lab1501networking_sol;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TestClient implements ActionListener {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private ChatClientFrame frame;

	public TestClient() {
		try {
			socket = new Socket("localhost", 2525);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		frame = new ChatClientFrame();
		frame.init(this);
	}

	public void read() {
		try {
			while (true) {
				frame.addMessage(in.readUTF());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		try {
			out.writeUTF(frame.getMessage());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void finalize() {
		try {
			socket.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		TestClient test = new TestClient();
		test.read();
	}
}
