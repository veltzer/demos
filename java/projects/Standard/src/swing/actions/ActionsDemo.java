package swing.actions;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * A simple demonstration for buttons: action listeners, setEnabled, isEnabled.
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class ActionsDemo extends JFrame {
	private JButton playButton;
	private JButton pauseButton;
	private JButton stopButton;
	private JLabel message;

	public ActionsDemo() {
		super("action demo");
	}

	private void init() {

		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		message = new JLabel("Stopped");

		stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				playButton.setEnabled(true);
				stopButton.setEnabled(false);
				pauseButton.setEnabled(false);
				message.setText("Stopped");
			}
		});
		playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				stopButton.setEnabled(true);
				playButton.setEnabled(false);
				pauseButton.setEnabled(true);
				message.setText("Playing");
			}
		});
		pauseButton = new JButton("Pause");
		pauseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				playButton.setEnabled(!playButton.isEnabled());
				if (playButton.isEnabled()) {
					message.setText("Paused");
				} else {
					message.setText("Playing");
				}
			}
		});

		c.add(stopButton);
		c.add(pauseButton);
		c.add(playButton);

		c.add(message);

		stopButton.setEnabled(false);
		pauseButton.setEnabled(false);

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);

		JMenu mediaMenu = new JMenu("Media");
		JMenuItem stopItem = new JMenuItem("Stop");
		JMenuItem pauseItem = new JMenuItem("Pause");
		JMenuItem playItem = new JMenuItem("Play");
		mediaMenu.add(stopItem);
		mediaMenu.add(pauseItem);
		mediaMenu.add(playItem);

		menuBar.add(mediaMenu);

		setJMenuBar(menuBar);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ActionsDemo app = new ActionsDemo();
		app.init();
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);
		app.setSize(400, 150);
		app.setVisible(true);
	}

}
