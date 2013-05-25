package swing.introduction;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class HelloWorld {

	/**
	 * @param args
	 */
	public static void main(String[] argv) {
		JFrame frame = new JFrame("Hello World");
		// Enable the next two lines to see that an application can
		// have more than one frame in it
		// JFrame frame2 = new JFrame("Goodbye World");
		// frame2.setVisible(true);
		// use next line to show a different layout
		// frame.getContentPane().setLayout(new FlowLayout(FlowLayout.RIGHT));
		frame.getContentPane().add(new JLabel("Hello"), BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// toggle the next two lines to see what packing does
		frame.pack();
		frame.setSize(200, 200);
		frame.setVisible(true);
		System.out.println("I am here");
		// enable the next code to show threading issues
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("I am there");
	}
}
