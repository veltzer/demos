package swing.desktop_integration;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ToolTipManager;

import org.jdesktop.jdic.browser.WebBrowser;
import org.jdesktop.jdic.browser.WebBrowserEvent;
import org.jdesktop.jdic.browser.WebBrowserListener;

public class Browser extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	class ForwardAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ForwardAction() {
			putValue(NAME, "Forward");
			putValue(SMALL_ICON,
					new ImageIcon(getClass().getResource("/Forward.png")));
			setEnabled(false);
		}

		public void actionPerformed(ActionEvent ev) {
			webBrowser.forward();
		}
	}

	class BackAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public BackAction() {
			putValue(NAME, "Back");
			putValue(SMALL_ICON,
					new ImageIcon(getClass().getResource("/Back.png")));
			setEnabled(false);
		}

		public void actionPerformed(ActionEvent ev) {
			webBrowser.back();
		}
	}

	class BrowseAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public BrowseAction() {
			putValue(SHORT_DESCRIPTION, "Load the given URL");
			putValue(NAME, "GO");
			putValue(SMALL_ICON, BROWSE_ICON);
		}

		public void actionPerformed(ActionEvent ev) {
			loadURL();
		}
	}

	private static final ImageIcon BROWSE_ICON = new ImageIcon(
			Browser.class.getResource("/Right.gif"));
	private final Action forward = new ForwardAction();
	private final Action back = new BackAction();

	private JToolBar toolBar = new JToolBar();
	private JTextField addressField = new JTextField();
	private WebBrowser webBrowser;
	private WebBrowser.Status status;

	public Browser() {
		this.setLayout(new BorderLayout());
		ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);

		addressField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				loadURL();
			}
		});

		JButton refreshButton = new JButton("Refresh", new ImageIcon(getClass()
				.getResource("/Reload.png")));
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				webBrowser.refresh();
			}
		});

		JButton stopButton = new JButton("Stop", new ImageIcon(getClass()
				.getResource("/Stop.png")));
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				webBrowser.stop();
			}
		});

		toolBar.add(back);
		toolBar.add(forward);
		toolBar.addSeparator();
		toolBar.add(refreshButton, null);
		toolBar.add(stopButton);
		toolBar.add(new JLabel("URL: "));
		toolBar.add(addressField);
		toolBar.add(new BrowseAction());
		add("North", toolBar);

		try {
			webBrowser = new WebBrowser(new URL("http://www.vprise.com/"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}
		add("Center", webBrowser);

		status = webBrowser.getStatus();
		webBrowser.addWebBrowserListener(new WebBrowserListener() {
			public void downloadStarted(WebBrowserEvent event) {
			}

			public void downloadCompleted(WebBrowserEvent event) {
				back.setEnabled(status.isBackEnabled());
				forward.setEnabled(status.isForwardEnabled());

				URL currentUrl = webBrowser.getURL();

				if (currentUrl != null) {
					addressField.setText(currentUrl.toString());
				}
			}

			public void downloadProgress(WebBrowserEvent event) {
			}

			public void downloadError(WebBrowserEvent event) {
			}

			public void titleChange(WebBrowserEvent event) {
			}

			public void statusTextChange(WebBrowserEvent event) {
			}
		});
	}

	public static void main(String[] args) {
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", new Browser());
		frm.setSize(400, 400);
		frm.invalidate();
		frm.validate();
		frm.setVisible(true);
	}

	void loadURL() {
		String inputValue = addressField.getText();

		if (inputValue == null) {
			JOptionPane.showMessageDialog(this, "The given URL is NULL:",
					"Warning", JOptionPane.WARNING_MESSAGE);
		} else {
			// Check if the text value is a URL string.
			URL curUrl = null;

			try {
				// Check if the input string is a local path by checking if it
				// starts
				// with a driver name(on Windows) or root path(on Unix).
				File[] roots = File.listRoots();

				for (int i = 0; i < roots.length; i++) {
					if (inputValue.toLowerCase().startsWith(
							roots[i].toString().toLowerCase())) {
						File curLocalFile = new File(inputValue);

						curUrl = curLocalFile.toURI().toURL();
						break;
					}
				}

				if (curUrl == null) {
					// Check if the text value starts with known protocols.
					if (inputValue.toLowerCase().startsWith("http://")
							|| inputValue.toLowerCase().startsWith("https://")
							|| inputValue.toLowerCase().startsWith("ftp://")
							|| inputValue.toLowerCase().startsWith("gopher://")) {
						curUrl = new URL(inputValue);
					} else {
						if (inputValue.toLowerCase().startsWith("ftp.")) {
							curUrl = new URL("ftp://" + inputValue);
						} else if (inputValue.toLowerCase().startsWith(
								"gopher.")) {
							curUrl = new URL("gopher://" + inputValue);
						} else {
							curUrl = new URL("http://" + inputValue);
						}
					}
				}

				webBrowser.setURL(curUrl);
			} catch (MalformedURLException mue) {
				JOptionPane.showMessageDialog(this,
						"The given URL is not valid:" + inputValue, "Warning",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
