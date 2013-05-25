package programming.labs.lab1501networking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestClient implements ActionListener {
	private ChatClientFrame frame;

	public TestClient() {
		frame = new ChatClientFrame();
		frame.init(this);
	}

	public void actionPerformed(ActionEvent ae) {

	}

	public static void main(String[] args) {
		/* TestClient test=new TestClient(); */

	}
}
