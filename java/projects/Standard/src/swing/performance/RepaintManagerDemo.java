package swing.performance;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.RepaintManager;

public abstract class RepaintManagerDemo {
	public static void main(String[] argv) {
		RepaintManager.setCurrentManager(new RepaintManager() {
			public void paintDirtyRegions() {
				long time = System.currentTimeMillis();
				super.paintDirtyRegions();
				System.out.println("Paint took: "
						+ (System.currentTimeMillis() - time));
			}
		});
		JFrame frm = new JFrame("Hello World");
		JLabel hi = new JLabel("Hi World");
		frm.getContentPane().add("Center", hi);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.pack();
		frm.setVisible(true);
	}
}
