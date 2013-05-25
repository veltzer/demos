package swing.eye_candy;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class GradientBorderDemo extends JPanel {
	public GradientBorderDemo() {
		setBorder(BorderFactory.createCompoundBorder(new ShadowBorder(),
				new GradientBorder("Title", SystemColor.activeCaption,
						SystemColor.window, Color.WHITE)));
		JButton button = new JButton("Button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (getComponentOrientation() == ComponentOrientation.RIGHT_TO_LEFT) {
					setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				} else {
					setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				}
				revalidate();
				repaint();
			}
		});
		button.setBorder(null);
		add(button);
		JLabel label = new JLabel("Label");
		label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add(label);
	}

	public static void main(String[] args) {
		GradientBorderDemo demo = new GradientBorderDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.pack();
		frm.setVisible(true);
	}
}
