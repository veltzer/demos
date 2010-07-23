package swing.desktop_integration;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jdesktop.jdic.desktop.Desktop;
import org.jdesktop.jdic.desktop.DesktopException;
import org.jdesktop.jdic.desktop.Message;
public class DesktopDemo extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFileChooser chooser = new JFileChooser();

    abstract class DesktopAction extends AbstractAction implements PropertyChangeListener {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DesktopAction() {
            setEnabled(false);
            chooser.addPropertyChangeListener(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY, this);
        }
        
        public final void propertyChange(PropertyChangeEvent evt) {
            setEnabled((chooser.getSelectedFile() != null) && secondCondition());
        }

        protected boolean secondCondition() {
            return true;
        }
        
        protected abstract void perform() throws Exception;
        
        public void actionPerformed(ActionEvent ev) {
            try {
                perform();
            } catch(Exception err) {
                err.printStackTrace();
            } 
        }
    }
    
    class EditAction extends DesktopAction {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public EditAction() {
            putValue(NAME, "Edit");
        }
        protected boolean secondCondition() {
            return Desktop.isEditable(chooser.getSelectedFile());
        }
        protected void perform() throws DesktopException {
            Desktop.edit(chooser.getSelectedFile());
        }
    }    
    
    class PrintAction extends DesktopAction {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public PrintAction() {
            putValue(NAME, "Print");
        }
        protected boolean secondCondition() {
            return Desktop.isPrintable(chooser.getSelectedFile());
        }
        protected void perform() throws DesktopException {
            Desktop.print(chooser.getSelectedFile());
        }
    }    
    
    class BrowseAction extends DesktopAction {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public BrowseAction() {
            putValue(NAME, "Browse");
        }
        protected void perform() throws DesktopException, MalformedURLException {
            Desktop.browse(chooser.getSelectedFile().toURI().toURL());
        }
    }    
    
    class OpenAction extends DesktopAction {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public OpenAction() {
            putValue(NAME, "Open");
        }
        protected void perform() throws DesktopException {
            Desktop.open(chooser.getSelectedFile());
        }
    }    
    
    class MailAction extends DesktopAction {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public MailAction() {
            putValue(NAME, "Mail");
        }
        protected void perform() throws DesktopException, java.io.IOException {
            Message m = new Message();
            m.setSubject("Swing says hello");
            List<String> list = new ArrayList<String>();
            list.add(chooser.getSelectedFile().getAbsolutePath());
            m.setAttachments(list);
            Desktop.mail(m);
        }
    }    
    
    public DesktopDemo() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel buttons = new JPanel(); 
        buttons.add(new JButton(new EditAction()));
        buttons.add(new JButton(new PrintAction()));
        buttons.add(new JButton(new BrowseAction()));
        buttons.add(new JButton(new OpenAction()));
        buttons.add(new JButton(new MailAction()));
        chooser.setMultiSelectionEnabled(false);
        chooser.setControlButtonsAreShown(false);
        add(buttons);
        add(chooser);
    }


    public static void main(String[] args) {
        DesktopDemo demo = new DesktopDemo();
        JFrame frm = new JFrame();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.getContentPane().add("Center", demo);
        frm.pack();
        frm.setVisible(true);
    }
}