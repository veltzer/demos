package swing.eye_candy;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class IconPositionDemo extends JPanel {
	public IconPositionDemo() {
		super(new java.awt.GridLayout(3, 6));
		add(createButton(SwingConstants.RIGHT, SwingConstants.CENTER));
		add(createButton(SwingConstants.LEFT, SwingConstants.CENTER));
		add(createButton(SwingConstants.CENTER, SwingConstants.CENTER));
		add(createButton(SwingConstants.LEADING, SwingConstants.CENTER));
		add(createButton(SwingConstants.TRAILING, SwingConstants.CENTER));
		add(createButton(SwingConstants.RIGHT, SwingConstants.TOP));
		add(createButton(SwingConstants.RIGHT, SwingConstants.BOTTOM));
		add(createTextButton(SwingConstants.RIGHT, SwingConstants.CENTER));
		add(createTextButton(SwingConstants.LEFT, SwingConstants.CENTER));
		add(createTextButton(SwingConstants.CENTER, SwingConstants.CENTER));
		add(createTextButton(SwingConstants.LEADING, SwingConstants.CENTER));
		add(createTextButton(SwingConstants.TRAILING, SwingConstants.CENTER));
		add(createTextButton(SwingConstants.RIGHT, SwingConstants.TOP));
		add(createTextButton(SwingConstants.RIGHT, SwingConstants.BOTTOM));
	}

	private AbstractButton createButton(int hAlign, int vAlign) {
		AbstractButton b = new JButton("Text", IconManager.getIcon("/duke.gif"));
		b.setHorizontalAlignment(hAlign);
		b.setVerticalAlignment(vAlign);
		return (b);
	}

	private AbstractButton createTextButton(int hAlign, int vAlign) {
		AbstractButton b = new JButton("Text", IconManager.getIcon("/duke.gif"));
		b.setHorizontalTextPosition(hAlign);
		b.setVerticalTextPosition(vAlign);
		return (b);
	}

	public static void main(String[] args) {
		IconPositionDemo demo = new IconPositionDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.pack();
		frm.setVisible(true);
	}
}
