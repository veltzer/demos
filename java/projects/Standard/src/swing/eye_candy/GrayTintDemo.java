package swing.eye_candy;

import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GrayTintDemo extends JPanel {
	public GrayTintDemo() {
		ImageIcon duke = new ImageIcon(getClass().getResource("/duke.gif"));
		Icon disabled = new ImageIcon(GrayFilter.createDisabledImage(duke
				.getImage()));
		add(new JLabel(duke));
		add(new JLabel(disabled));
	}

	public static void main(String[] argv) {
		GrayTintDemo demo = new GrayTintDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.pack();
		frm.setVisible(true);
	}
}
