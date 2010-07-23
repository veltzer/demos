import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.metal.MetalButtonUI;


public class FileTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final JFrame frm = new JFrame("Files");
		final FileTableModel model = new FileTableModel();
		TotalRowDecorator dec = new TotalRowDecorator(model, 1);
		model.setDirectory(new File("/"));
		JTable table = new MyTable(dec);
		
		
		Enumeration enumer = UIManager.getDefaults().keys();
		while (enumer.hasMoreElements()) {
			Object key = enumer.nextElement();
			System.out.println(key +  ":" + UIManager.getDefaults().get(key));
		}
		
		
		
		frm.add(new JScrollPane(table));
		
		final JTextField  fld = new JTextField(10);
		
		JPanel pnl = new JPanel();
		JButton btn = new JButton("Change Dir");
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setDirectory(new File(fld.getText()));
			}
		});
		
		final JComboBox combo = new JComboBox(UIManager.getInstalledLookAndFeels());
		combo.addActionListener(new ActionListener() {
		
			MetalButtonUI a;
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(((LookAndFeelInfo)combo.getSelectedItem()).getClassName());
					UIManager.getLookAndFeel().getDefaults().put("button.border", Color.red);
					SwingUtilities.updateComponentTreeUI(frm);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JPanel uiPnl = new JPanel();
		uiPnl.add(combo);
		
		pnl.add(fld);
		pnl.add(btn);
		
		frm.add(pnl, BorderLayout.SOUTH);
		frm.add(uiPnl, BorderLayout.NORTH);
		
		frm.pack();
		frm.setVisible(true);

	}

}
