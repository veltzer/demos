package swing.layouts;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Mark Veltzer <mark@veltzer.net>
 */
public final class LayoutDemo {
	private JFrame mainFrame;

	private LayoutDemo() {
		mainFrame = new JFrame("Layout demo");
	}

	private void init() {

		JButton button0 = new JButton("button 0");
		JButton button1 = new JButton("button 1");
		JButton button2 = new JButton("button 2");
		JButton button3 = new JButton("button 3");
		JButton button4 = new JButton("button 444444444");

		button0.setPreferredSize(new Dimension(40, 40));

		Container c = mainFrame.getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.TRAILING));

		// c.setLayout(new GridLayout(2,2));
		//
		// c.setLayout(new BorderLayout());

		//
		// JPanel upperPanel = new JPanel();
		// upperPanel.setLayout(new FlowLayout());
		// upperPanel.add(button1);
		// upperPanel.add(button3);
		// c.add(upperPanel, BorderLayout.WEST);
		//
		// c.add(button0, BorderLayout.CENTER);
		// c.add(button3, BorderLayout.CENTER);
		// //
		// c.add(button2, BorderLayout.EAST);
		// c.add(button4, BorderLayout.SOUTH);

		c.add(button0);
		c.add(button1);
		c.add(button2);
		c.add(button3);
		c.add(button4);

		mainFrame.addWindowListener(new WindowAdapter() {
			/*
			 * (non-Javadoc)
			 * @see
			 * java.awt.event.WindowAdapter#windowClosed(java.awt.event.WindowEvent
			 * )
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ) ;
		// mainFrame.setSize(400, 400);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		LayoutDemo demo = new LayoutDemo();
		demo.init();
	}

}
