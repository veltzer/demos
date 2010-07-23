package swing.eye_candy;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StretchImagePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;


    public StretchImagePanel(URL image) {
        setOpaque(true);
        this.image = getToolkit().getImage(image);
        MediaTracker trak = new MediaTracker(this);
        trak.addImage(this.image, 1);
        try {
            trak.waitForAll();
        } catch (InterruptedException ignor) {}
    }


    public void paint(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        super.paintChildren(g);
    }


    public static void main(String[] argv) {
        URL url = StretchImagePanel.class.getResource("/duke.gif");
        StretchImagePanel demo = new StretchImagePanel(url);
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

