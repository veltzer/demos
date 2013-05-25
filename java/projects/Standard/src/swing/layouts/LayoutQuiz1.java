package swing.layouts;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

import swing.graphics2d.RollingPolygon;

@SuppressWarnings("serial")
public class LayoutQuiz1 extends JFrame {
	public LayoutQuiz1() {
		super("LayoutQuiz1");
	}

	private void init() {
		Container c = getContentPane();
		RollingPolygon rollingPolygon = new RollingPolygon();
		rollingPolygon.init();
		rollingPolygon.setRunning(true);
		c.add(rollingPolygon, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());
		buttonsPanel.add(new JButton("Stop"));
		buttonsPanel.add(new JButton("Pause"));
		buttonsPanel.add(new JButton("Play"));
		buttonsPanel.setBorder(BorderFactory.createEtchedBorder());
		c.add(buttonsPanel, BorderLayout.SOUTH);

		setSize(400, 400);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		RepaintManager.setCurrentManager(new RepaintManager() {

			@Override
			public void paintDirtyRegions() {
				long startTime = System.currentTimeMillis();
				super.paintDirtyRegions();
				long endTime = System.currentTimeMillis();
				System.out.println("paint took " + (endTime - startTime)
						+ " millis");
			}

		});
		LayoutQuiz1 app = new LayoutQuiz1();
		app.init();
		app.setVisible(true);
	}

}
