package swing.text;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

public class SelectionDemo extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JTextArea textArea;

	private Action selectLineAction;

	public SelectionDemo()
	{

	}

	@SuppressWarnings("serial")
	private void init()
	{
		setLayout(new BorderLayout());

		textArea = new JTextArea();
		textArea.setText("abcd\nefg\nhij\nklmn");
		textArea.select(4, 12);

		add(textArea, BorderLayout.CENTER);

		selectLineAction = new AbstractAction() {

			public void actionPerformed(ActionEvent e)
			{
				int position = textArea.getCaretPosition();
				try
				{
					int lineNumber = textArea.getLineOfOffset(position);
					int selectStart = textArea.getLineStartOffset(lineNumber);
					int selectEnd = textArea.getLineEndOffset(lineNumber);
					if (textArea.getLineOfOffset(selectEnd) > lineNumber)
						--selectEnd;
					textArea.select(selectStart, selectEnd);
				}
				catch (BadLocationException e1)
				{
					e1.printStackTrace();
				}

			}
		};
		selectLineAction.putValue(Action.NAME, "Select line");
		KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0);
		textArea.getInputMap().put(keyStroke, "selectLine");
		textArea.getActionMap().put("selectLine", selectLineAction);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SelectionDemo demo = new SelectionDemo();
		demo.init();

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Selection demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new JScrollPane(demo), BorderLayout.CENTER);
		frame.setSize(400, 200);
		frame.setVisible(true);
	}

}
