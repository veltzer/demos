package swing.desktop_integration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.jdesktop.jdic.tray.SystemTray;
import org.jdesktop.jdic.tray.TrayIcon;

public final class TrayDemo {
	private TrayDemo() {
		SystemTray tray = SystemTray.getDefaultSystemTray();
		JPopupMenu menu = new JPopupMenu("Hi Menu");
		JMenuItem menuItem = new JMenuItem("Option", KeyEvent.VK_T);
		menu.add(menuItem);

		menuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});
		menu.add(menuItem);

		ImageIcon i = new ImageIcon(getClass().getResource("/duke.gif"));
		TrayIcon ti = new TrayIcon(i, "Tray Icon Demo", menu);
		ti.setIconAutoSize(true);
		tray.addTrayIcon(ti);
	}

	public static void main(String[] argv) {
		new TrayDemo();
	}
}
