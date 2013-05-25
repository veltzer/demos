package swing.eye_candy;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class WatermarkImagePanel extends JPanel {
	private Image image;
	private float alpha;

	public WatermarkImagePanel(URL iimage, float ialpha) {
		alpha = ialpha;
		setOpaque(true);
		image = getToolkit().getImage(iimage);
		MediaTracker trak = new MediaTracker(this);
		trak.addImage(image, 1);
		try {
			trak.waitForAll();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void paint(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintChildren(g);
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, alpha));
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}

	public static void main(String[] argv) {
		URL url = WatermarkImagePanel.class.getResource("/duke.gif");
		WatermarkImagePanel demo = new WatermarkImagePanel(url, 0.5f);
		demo.setLayout(new java.awt.FlowLayout());
		demo.add(new JButton("Button"));
		demo.add(new JLabel("Label"));
		demo.add(new JTextField("Field"));
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.pack();
		frm.setVisible(true);
	}
}
