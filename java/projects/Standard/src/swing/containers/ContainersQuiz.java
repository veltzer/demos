package swing.containers;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import swing.graphics2d.RollingPolygon;
import swing.layouts.StretchLayout;

@SuppressWarnings("serial")
public class ContainersQuiz extends JFrame {
	public ContainersQuiz() {
		super("ContainersQuiz");
	}

	private void init() {
		Container c = getContentPane();

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("General", createTab1());
		tabbedPane.addTab("Polygon", createTab2());
		tabbedPane.addTab("Edit", createTab3());
		tabbedPane.addTab("labels", new LayeredLabels());

		c.add(tabbedPane, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		buttonsPanel.add(new JButton("OK"));
		buttonsPanel.add(new JButton("Cancel"));

		c.add(buttonsPanel, BorderLayout.SOUTH);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 400);
	}

	private JComponent createTab1() {
		Box box = new Box(BoxLayout.PAGE_AXIS);
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		topPanel.add(new JCheckBox("Enable"));
		box.add(topPanel);

		Box innerBox = new Box(BoxLayout.PAGE_AXIS);
		for (int i = 0; i < 8; ++i) {
			innerBox.add(createPair("value " + i));
		}
		box.add(innerBox);
		return box;
	}

	private JComponent createTab2() {
		JPanel panel = new JPanel(new BorderLayout());

		//
		// Box buttonsBox = new Box(BoxLayout.PAGE_AXIS);
		// buttonsBox.add(new JButton("Play"));
		// buttonsBox.add(new JButton("Stop"));
		// panel.add(buttonsBox, BorderLayout.WEST);

		JPanel buttonsPanel = new JPanel(new StretchLayout());
		buttonsPanel.add(new JButton("Play"));
		buttonsPanel.add(new JButton("Stop"));
		panel.add(buttonsPanel, BorderLayout.WEST);

		RollingPolygon rollingPolygon = new RollingPolygon();
		rollingPolygon.init();
		rollingPolygon.setPreferredSize(new Dimension(400, 300));
		rollingPolygon.setRunning(true);

		panel.add(new JScrollPane(rollingPolygon), BorderLayout.CENTER);
		return panel;
	}

	private JComponent createPair(String labelText) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		leftPanel.add(new JLabel(labelText));
		panel.add(leftPanel);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		JTextField textField = new JTextField(10);
		textField.setText("" + (int) (Math.random() * 1000));
		rightPanel.add(textField);
		panel.add(rightPanel);

		return panel;
	}

	private JComponent createTab3() {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		RollingPolygon rollingPolygon = new RollingPolygon();
		rollingPolygon.init();
		rollingPolygon.setRunning(true);
		splitPane.add(rollingPolygon);

		splitPane.add(new JScrollPane(new JTextArea()));
		splitPane.setDividerLocation(200);
		return splitPane;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		ContainersQuiz app = new ContainersQuiz();
		app.init();
		app.setVisible(true);
	}

}
