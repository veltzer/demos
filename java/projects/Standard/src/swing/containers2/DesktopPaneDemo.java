package swing.containers2;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public final class DesktopPaneDemo {
	private JDesktopPane pane = new JDesktopPane();

	private DesktopPaneDemo() {
		JInternalFrame frame1 = new JInternalFrame("Frame1");
		JInternalFrame frame2 = new JInternalFrame("Frame2", true, true, true,
				true);
		JInternalFrame frame3 = new JInternalFrame("Frame3", true, true, true,
				true);
		frame1.getContentPane().add("Center", new JLabel("Frame 1"));
		frame2.getContentPane().add("Center", new JButton("Frame 2"));
		frame3.getContentPane().add("Center", new JLabel("Frame 3"));
		pane.add(frame1);
		pane.add(frame2);
		pane.add(frame3);
		frame3.setLayer(50);
		frame1.setVisible(true);
		frame2.setVisible(true);
		frame3.setVisible(true);
		frame1.pack();
		frame2.pack();
		frame3.pack();
	}

	public static void main(String[] argv) {
		DesktopPaneDemo tabs = new DesktopPaneDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", tabs.pane);
		frm.pack();
		frm.setVisible(true);
	}
}
