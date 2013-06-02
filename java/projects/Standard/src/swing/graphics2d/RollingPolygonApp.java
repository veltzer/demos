package swing.graphics2d;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class RollingPolygonApp extends JFrame {
	private RollingPolygon rollingPolygon;
	private AbstractAction playAction;
	private AbstractAction stopAction;

	public RollingPolygonApp() {
		super("Rollin polygon demo");
	}

	private void init() {
		rollingPolygon = new RollingPolygon();
		rollingPolygon.init();
		getContentPane().add(rollingPolygon, BorderLayout.CENTER);

		// The playAction will be used by both the toolbar and the menu
		playAction = new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				this.setEnabled(false);
				stopAction.setEnabled(true);
				rollingPolygon.setRunning(true);
			}
		};
		playAction.putValue(Action.NAME, "Play");
		playAction.putValue(Action.SHORT_DESCRIPTION, "Start rolling");
		playAction.putValue(Action.SMALL_ICON, new ImageIcon("Play24.gif"));

		// The stopAction will be used by both the toolbar and the menu
		stopAction = new AbstractAction() {

			public void actionPerformed(ActionEvent e) {
				this.setEnabled(false);
				playAction.setEnabled(true);
				rollingPolygon.setRunning(false);
			}
		};
		stopAction.putValue(Action.NAME, "Stop");
		stopAction.putValue(Action.SHORT_DESCRIPTION, "Stop rolling");
		stopAction.putValue(Action.SMALL_ICON, new ImageIcon("Stop24.gif"));
		stopAction.setEnabled(false);

		JToolBar toolBar = new JToolBar("Main toolbar");
		toolBar.add(playAction);
		toolBar.add(stopAction);
		toolBar.setFloatable(false);

		// Take a look at how we would create buttons directly:
		// toolBar.add(createToolbarButton("Play24.gif", "Play", "Play"));
		// toolBar.add(createToolbarButton("Stop24.gif", "Stop", "Stop"));

		getContentPane().add(toolBar, BorderLayout.NORTH);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Act");
		menu.add(new JMenuItem(playAction));
		menu.add(new JMenuItem(stopAction));
		menuBar.add(menu);
		setJMenuBar(menuBar);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(500, 500);

	}

	/**
	 * This method is not actuall used. It shows the direct way of creating
	 * toolbar buttons, without utilizing actions.
	 * @param imageName
	 * @param toolTipText
	 * @param altText
	 * @return
	 */
	public JButton createToolbarButton(String imageName, String toolTipText,
			String altText) {
		JButton button = new JButton();

		button.setIcon(new ImageIcon(imageName, altText));
		button.setToolTipText(toolTipText);
		return button;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		RollingPolygonApp app = new RollingPolygonApp();
		app.init();
		app.setVisible(true);

	}

}
