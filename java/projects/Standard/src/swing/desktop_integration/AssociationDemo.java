package swing.desktop_integration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jdesktop.jdic.filetypes.Association;
import org.jdesktop.jdic.filetypes.AssociationException;
import org.jdesktop.jdic.filetypes.AssociationService;

@SuppressWarnings("serial")
public class AssociationDemo extends JPanel {
	private JFileChooser chooser = new JFileChooser();
	private JTextField associationName = new JTextField(15);
	private JTextField parameters = new JTextField(15);
	private JTextField fileExtension = new JTextField(15);
	private JTextField mimeType = new JTextField(15);
	private AssociationService svc = new AssociationService();

	abstract class DocumentProxy implements DocumentListener {
		public void changedUpdate(DocumentEvent e) {
			update();
		}

		public void insertUpdate(DocumentEvent e) {
			update();
		}

		public void removeUpdate(DocumentEvent e) {
			update();
		}

		protected abstract void update();
	}

	public AssociationDemo() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		chooser.setMultiSelectionEnabled(false);
		chooser.setControlButtonsAreShown(false);
		add(createPair("Name", associationName));
		add(createPair("Parameters", parameters));
		add(createPair("File Extension", fileExtension));
		add(createPair("Mime Type", mimeType));
		add(chooser);
		JButton bind = new JButton("Bind");
		bind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					org.jdesktop.jdic.filetypes.Action action = new org.jdesktop.jdic.filetypes.Action(
							associationName.getText(), chooser
									.getSelectedFile().getAbsolutePath()
									+ " "
									+ parameters.getText());

					Association assoc = new Association();
					assoc.setName(associationName.getText());
					assoc.addFileExtension(fileExtension.getText());
					assoc.setMimeType(mimeType.getText());
					assoc.addAction(action);

					svc.registerUserAssociation(assoc);
				} catch (AssociationException e) {
					throw new RuntimeException(e);
				}
			}
		});
		add(bind);
		fileExtension.getDocument().addDocumentListener(new DocumentProxy() {
			protected void update() {
				if (fileExtension.getText().length() > 0) {
					Association asso = svc
							.getFileExtensionAssociation(fileExtension
									.getText());
					if (asso != null) {
						mimeType.setText(asso.getMimeType());
					}
				}
			}
		});
	}

	private Box createPair(String label, JComponent cmp) {
		Box pair = new Box(BoxLayout.X_AXIS);
		pair.add(new JLabel(label));
		pair.add(Box.createHorizontalStrut(4));
		pair.add(cmp);
		return pair;
	}

	public static void main(String[] args) {
		AssociationDemo demo = new AssociationDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.pack();
		frm.setVisible(true);
	}
}
