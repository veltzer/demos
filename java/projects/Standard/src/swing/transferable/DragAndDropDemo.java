package swing.transferable;

import java.awt.Font;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

@SuppressWarnings("serial")
public class DragAndDropDemo extends JPanel {
	public DragAndDropDemo() {
		JColorChooser chooser = new JColorChooser();
		chooser.setDragEnabled(true);
		JLabel label = new JLabel("Drop Color Here");
		label.setFont(new Font("SansSerif", Font.BOLD, 22));
		label.setText("Drop Color Here");
		label.setTransferHandler(new TransferHandler("foreground"));
		add(chooser);
		add(label);
	}

	public static void main(String[] argv) {
		JFrame dlg = new JFrame();
		dlg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dlg.getContentPane().add("Center", new DragAndDropDemo());
		dlg.pack();
		dlg.setVisible(true);
	}
}
