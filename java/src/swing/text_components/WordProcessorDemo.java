package swing.text_components;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.ListCellRenderer;
import javax.swing.text.AttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

public class WordProcessorDemo extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextPane pane = new TextPane();
    private JToolBar toolbar = new JToolBar();
    private StyledDocument doc;
    public WordProcessorDemo() {
        setLayout(new BorderLayout());
        add("North", toolbar);
        add("Center", new JScrollPane(pane));
        doc = pane.getStyledDocument();
        toolbar.add(new InsertImage());

        Action action = new StyledEditorKit.BoldAction();
        action.putValue(Action.NAME, "Bold");
        toolbar.add(action);

        action = new StyledEditorKit.ItalicAction();
        action.putValue(Action.NAME, "Italic");
        toolbar.add(action);

        action = new StyledEditorKit.UnderlineAction();
        action.putValue(Action.NAME, "Underline");
        toolbar.add(action);
        
        toolbar.addSeparator();
        toolbar.add(new JComboBox(new FontSizeCombo()));
        toolbar.addSeparator();
        JComboBox combo = new JComboBox(new FontCombo());
        combo.setRenderer(new FamilyRenderer());
        toolbar.add(combo);
    }
    
    
    public static void main(String[] argv) {
        WordProcessorDemo layout = new WordProcessorDemo();
        JFrame frm = new JFrame();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.getContentPane().add("Center", layout);
        frm.pack();
        frm.setVisible(true);
    }
    
    class InsertImage extends AbstractAction {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public InsertImage() {
            putValue(NAME, "Image");
        }
        
        public void actionPerformed(ActionEvent actionEvent) {
            JFileChooser chooser = new JFileChooser();
            if(chooser.showOpenDialog((Component)actionEvent.getSource()) ==
              JOptionPane.OK_OPTION) {
                File file = chooser.getSelectedFile();
                pane.insertIcon(new ImageIcon(file.getAbsolutePath()));
            }
        }
        
    }
    
    public void setCharacterAttributes(AttributeSet attr, boolean replace) {
        pane.setCharacterAttributes(attr, replace);
    }

    public class FontSizeCombo implements ComboBoxModel {
        private Integer[] fontSizes = new Integer[] { 
            new Integer(8), new Integer(9), new Integer(10), new Integer(11), 
            new Integer(12), new Integer(13), new Integer(14), new Integer(16), 
            new Integer(18), new Integer(20), new Integer(22), new Integer(24) };
        private Integer selection;

        public void addListDataListener(javax.swing.event.ListDataListener listDataListener) {
        }
        
        public Object getElementAt(int param) {
            return fontSizes[param];
        }
        
        public Object getSelectedItem() {
            return selection;
        }
        
        public int getSize() {
            return fontSizes.length;
        }
        
        public void removeListDataListener(javax.swing.event.ListDataListener listDataListener) {
        }
        
        public void setSelectedItem(Object obj) {
            selection = (Integer)obj;
            MutableAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setFontSize(attr, selection.intValue());
            setCharacterAttributes(attr, false);
        }
    }

    public class FontCombo implements ComboBoxModel {
        private String[] familyNames;
        private String selection;

        public FontCombo() {
            familyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        }
        
        public void addListDataListener(javax.swing.event.ListDataListener listDataListener) {
        }
        
        public Object getElementAt(int param) {
            return familyNames[param];
        }
        
        public Object getSelectedItem() {
            return selection;
        }
        
        public int getSize() {
            return familyNames.length;
        }
        
        public void removeListDataListener(javax.swing.event.ListDataListener listDataListener) {
        }
        
        public void setSelectedItem(Object obj) {
            selection = (String)obj;
            MutableAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setFontFamily(attr, selection);
            setCharacterAttributes(attr, false);
        }
    }
    
    class FamilyRenderer extends DefaultListCellRenderer implements ListCellRenderer {
        
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Component getListCellRendererComponent(JList jList, Object obj, int param, boolean param3, boolean param4) {
            Component c = super.getListCellRendererComponent(jList, obj, param, param3, param4);
            c.setFont(new Font((String)obj, Font.PLAIN, 10));
            return c;
        }
        
    }
    
    class TextPane extends JTextPane {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void setCharacterAttributes(AttributeSet attr, boolean replace) {
	    int p0 = pane.getSelectionStart();
	    int p1 = pane.getSelectionEnd();
	    if (p0 != p1) {
            doc.setCharacterAttributes(p0, p1 - p0, attr, replace);
	    }
	    MutableAttributeSet inputAttributes = ((StyledEditorKit)getEditorKit()).getInputAttributes();
	    if (replace) {
            inputAttributes.removeAttributes(inputAttributes);
	    }
	    inputAttributes.addAttributes(attr);
	}

    }
}
