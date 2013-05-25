package swing.keyboard;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class HangMan extends JFrame {
	private WordCanvas wordCanvas;
	private HangedManCanvas hangedManCanvas;
	private HangManLogic hangManLogic;

	public HangMan() {
		super("Hangman");
		hangManLogic = new HangManLogic();
	}

	private void init() {

		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		wordCanvas = new WordCanvas(hangManLogic);
		wordCanvas.setPreferredSize(new Dimension(760, 200));
		splitPane.add(new JScrollPane(wordCanvas));

		hangedManCanvas = new HangedManCanvas();
		splitPane.add(new JScrollPane(hangedManCanvas));
		hangedManCanvas.setErrorLevel(0);

		c.add(splitPane, BorderLayout.CENTER);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getModifiers() != 0) {
					return;
				}
				handleKeyTyped(e.getKeyChar());
			}
		});

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('f');
		JMenuItem newGameItem = new JMenuItem("New game");
		newGameItem.setMnemonic('n');
		newGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		newGameItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				startNewGame();
			}
		});
		fileMenu.add(newGameItem);
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('x');
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_MASK));
		exitItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);

		menuBar.add(fileMenu);

		setJMenuBar(menuBar);

		startNewGame();
	}

	private void handleKeyTyped(char c) {
		c = Character.toLowerCase(c);
		if (c < 'a' || c > 'z') {
			return;
		}
		if (hangManLogic.characterIsGuessed(c)) {
			return;
		}
		if (hangManLogic.guessCharacter(c)) {
			// success!
			wordCanvas.repaint();
			if (hangManLogic.guessComplete()) {
				JOptionPane.showMessageDialog(this, "You win");
				startNewGame();
			}
		} else {
			// Wrong character
			int errorLevel = hangedManCanvas.getErrorLevel() + 1;
			hangedManCanvas.setErrorLevel(errorLevel);
			if (errorLevel > 8) {
				JOptionPane.showMessageDialog(this, "You lose");
				startNewGame();
			}
		}
	}

	private void startNewGame() {
		hangManLogic.setWord("underground");
		hangedManCanvas.setErrorLevel(0);
		repaint();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		HangMan app = new HangMan();
		app.init();
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);
		app.setSize(800, 500);
		app.setVisible(true);
	}

}
