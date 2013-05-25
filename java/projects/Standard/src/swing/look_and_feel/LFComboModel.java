package swing.look_and_feel;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListDataListener;

public class LFComboModel implements ComboBoxModel<UIManager.LookAndFeelInfo> {
	private UIManager.LookAndFeelInfo[] lfData = UIManager
			.getInstalledLookAndFeels();

	public void setSelectedItem(Object anItem) {
		setPlaf(((UIManager.LookAndFeelInfo) anItem).getClassName());
	}

	public UIManager.LookAndFeelInfo getElementAt(int index) {
		return (lfData[index]);
	}

	public Object getSelectedItem() {
		return (UIManager.getLookAndFeel());
	}

	public int getSize() {
		return (lfData.length);
	}

	public void addListDataListener(ListDataListener l) {
	}

	public void removeListDataListener(ListDataListener l) {
	}

	private void setPlaf(String className) {
		try {
			UIManager.setLookAndFeel(className);
			SwingUtilities.updateComponentTreeUI(JFrame.getFrames()[0]);
			JFrame.getFrames()[0].invalidate();
			JFrame.getFrames()[0].validate();
			JFrame.getFrames()[0].repaint();
		} catch (Exception err) {
			JOptionPane
					.showMessageDialog(
							JFrame.getFrames()[0],
							"This Look and Feel isn't\nsupported by the current platform",
							"Look And Feel Not Supported",
							JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] argv) {
		LFComboModel comboModel = new LFComboModel();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center",
				new JComboBox<UIManager.LookAndFeelInfo>(comboModel));
		frm.pack();
		frm.setVisible(true);
	}
}
