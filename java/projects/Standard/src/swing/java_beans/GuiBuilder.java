package swing.java_beans;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.BeanInfo;
import java.beans.ExceptionListener;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.border.Border;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

@SuppressWarnings("serial")
public class GuiBuilder extends JPanel implements ExceptionListener {
	private static final Class<?>[] SUPPORTED_WIDGETS = new Class[] {
			JButton.class, JRadioButton.class, JToggleButton.class,
			JTextField.class, JTextArea.class
	};
	private static final File DATA_FILE = new File("gui_layout.xml");
	private ComponentTreeModel treeModel = new ComponentTreeModel();
	private JTree tree = new JTree(treeModel);
	private JPanel content = new JPanel(null);
	private JComponent oldEditComponent = null;
	private Border oldEditComponentBorder = null;
	private JTable propertySheet = null;
	private PropertyModel propertyModel = new PropertyModel();
	private ComponentPositioning positioner = null;

	public GuiBuilder() {
		super(new java.awt.BorderLayout());
		add("Center", content);
		tree.setRootVisible(true);
		tree.addTreeSelectionListener(treeModel);
		tree.setCellRenderer(new DefaultTreeCellRenderer() {
			public Component getTreeCellRendererComponent(JTree itree,
					Object value, boolean selected, boolean expanded,
					boolean leaf, int row, boolean hasFocus) {
				Component cmp = super.getTreeCellRendererComponent(itree,
						value, selected, expanded, leaf, row, hasFocus);
				try {
					BeanInfo info = Introspector.getBeanInfo(value.getClass());
					setText(info.getBeanDescriptor().getDisplayName());
					Image img = info.getIcon(BeanInfo.ICON_COLOR_16x16);
					if (img != null) {
						setIcon(new ImageIcon(img));
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				return cmp;
			}
		});
	}

	private void editComponent(JComponent cmp) {
		if (oldEditComponent != null) {
			oldEditComponent.setBorder(oldEditComponentBorder);
		}

		oldEditComponent = cmp;
		oldEditComponentBorder = cmp.getBorder();
		cmp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		positioner = new ComponentPositioning(cmp);
		cmp.addMouseMotionListener(positioner);
		cmp.addMouseListener(positioner);
		propertyModel.setComponent(cmp);
		if (propertySheet == null) {
			propertySheet = new JTable(propertyModel);

			propertySheet.setDefaultEditor(Object.class, new DefaultCellEditor(
					new JTextField()) {
				private PropertyEditor editor;
				private boolean asText = false;
				private DefaultCellEditor alternative;

				public Component getTableCellEditorComponent(JTable table,
						Object value, boolean isSelected, int row, int column) {
					asText = false;
					alternative = null;
					editor = PropertyEditorManager.findEditor(value.getClass());
					if (editor != null) {
						if (editor.supportsCustomEditor()) {
							return editor.getCustomEditor();
						}
						asText = true;
						editor.setValue(value);
						value = editor.getAsText();
					} else {
						if (value instanceof Boolean) {
							alternative = new DefaultCellEditor(new JCheckBox());
							return alternative.getTableCellEditorComponent(
									table, value, isSelected, row, column);
						}
						if (value instanceof Number) {
							alternative = new DefaultCellEditor(
									new JFormattedTextField(value));
							return alternative.getTableCellEditorComponent(
									table, value, isSelected, row, column);
						}
					}
					return super.getTableCellEditorComponent(table, value,
							isSelected, row, column);
				}

				public Object getCellEditorValue() {
					if (alternative != null) {
						return alternative.getCellEditorValue();
					}

					if (editor == null) {
						return super.getCellEditorValue();
					}

					if (asText) {
						editor.setAsText((String) super.getCellEditorValue());
					}

					Object value = editor.getValue();
					return value;
				}
			});

			JFrame frm = (JFrame) SwingUtilities.windowForComponent(this);
			JDialog dlg = new JDialog();
			dlg.getContentPane().add("Center", new JScrollPane(propertySheet));
			dlg.pack();
			dlg.setLocation(frm.getX() + frm.getWidth(), frm.getY());
			dlg.setVisible(true);
		}
	}

	private JToolBar createWidgetBar() {
		JToolBar toolbar = new JToolBar() {
			public JButton createActionComponent(Action a) {
				JButton b = super.createActionComponent(a);
				b.setTransferHandler(new ActionTransferHandler());
				return (b);
			}
		};
		for (int iter = 0; iter < SUPPORTED_WIDGETS.length; iter++) {
			toolbar.add(new SelectBeanAction(SUPPORTED_WIDGETS[iter]));
		}
		return toolbar;
	}

	private void store() {
		try {
			if (oldEditComponent != null) {
				oldEditComponent.setBorder(oldEditComponentBorder);
				oldEditComponent.removeMouseMotionListener(positioner);
				oldEditComponent.removeMouseListener(positioner);
			}

			XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream(DATA_FILE)));
			enc.setExceptionListener(this);
			enc.writeObject(content);
			enc.close();

			if (oldEditComponent != null) {
				oldEditComponent.setBorder(BorderFactory.createLineBorder(
						Color.BLACK, 4));
				oldEditComponent.addMouseMotionListener(positioner);
				oldEditComponent.addMouseListener(positioner);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] argv) throws IOException {
		GuiBuilder layout = new GuiBuilder();
		if (DATA_FILE.exists()) {
			XMLDecoder d = new XMLDecoder(new BufferedInputStream(
					new FileInputStream(DATA_FILE)));
			d.setExceptionListener(new GuiBuilder());
			layout.remove(layout.content);
			layout.content = (JPanel) d.readObject();
			layout.add("Center", layout.content);
			d.close();
		}
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("North", layout.createWidgetBar());
		frm.getContentPane().add("Center", layout);
		frm.setSize(500, 500);
		frm.setLocation(100, 100);
		frm.validate();
		frm.setVisible(true);
		JDialog treeDialog = new JDialog(frm);
		layout.treeModel.fireTreeStructureChanged();
		treeDialog.getContentPane().add("Center", new JScrollPane(layout.tree));
		treeDialog.pack();
		treeDialog.setVisible(true);
	}

	public void exceptionThrown(Exception e) {
		throw new RuntimeException(e);
	}

	class SelectBeanAction extends AbstractAction {
		private BeanInfo info;
		private Class<?> beanClass;

		public SelectBeanAction(Class<?> ibeanClass) {
			try {
				beanClass = ibeanClass;
				info = Introspector.getBeanInfo(beanClass);
				putValue(NAME, info.getBeanDescriptor().getDisplayName());
				Image img = info.getIcon(BeanInfo.ICON_COLOR_32x32);
				if (img != null) {
					putValue(SMALL_ICON, new ImageIcon(img));
				}
			} catch (IntrospectionException e) {
				throw new RuntimeException(e);
			}
		}

		public void actionPerformed(ActionEvent ev) {
			try {
				Container parent;
				if (tree.getSelectionPath() == null) {
					parent = content;
				} else {
					Component c = (Component) tree.getSelectionPath()
							.getLastPathComponent();
					if (c instanceof Container) {
						parent = (Container) c;
					} else {
						parent = c.getParent();
					}
				}
				Component cmp = (Component) beanClass.newInstance();
				cmp.setSize(100, 100);
				parent.add(cmp);
				treeModel.fireTreeStructureChanged();
				store();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	static class ActionTransferHandler extends TransferHandler {
		public ActionTransferHandler() {
			super();
		}
	}

	class ComponentTreeModel implements TreeModel, TreeSelectionListener {
		private Collection<TreeModelListener> listeners = new ArrayList<TreeModelListener>();

		public void valueChanged(TreeSelectionEvent e) {
			if (e.getPath() != null) {
				Component cmp = (Component) e.getPath().getLastPathComponent();
				if (cmp instanceof JComponent) {
					editComponent((JComponent) cmp);
				}
			}
		}

		public void fireTreeStructureChanged() {
			TreeModelEvent e = new TreeModelEvent(this, new Object[] {
				getRoot()
			});
			TreeModelListener[] array = new TreeModelListener[listeners.size()];
			listeners.toArray(array);
			for (int iter = 0; iter < array.length; iter++) {
				array[iter].treeStructureChanged(e);
			}
		}

		public void addTreeModelListener(TreeModelListener l) {
			listeners.add(l);
		}

		public Object getChild(Object parent, int index) {
			Component[] cmps = ((Container) parent).getComponents();
			return (cmps[index]);
		}

		public int getChildCount(Object parent) {
			return ((Container) parent).getComponentCount();
		}

		public int getIndexOfChild(Object parent, Object child) {
			Component[] cmps = ((Container) parent).getComponents();
			for (int iter = 0; iter < cmps.length; iter++) {
				if (child.equals(cmps[iter])) {
					return iter;
				}
			}
			return -1;
		}

		public Object getRoot() {
			return content;
		}

		public boolean isLeaf(Object node) {
			if (node instanceof Container) {
				return ((Container) node).getComponentCount() == 0;
			}
			return true;
		}

		public void removeTreeModelListener(TreeModelListener l) {
			listeners.remove(l);
		}

		public void valueForPathChanged(TreePath path, Object newValue) {
		}
	}

	private static final Class<?>[] COLUMNS = new Class[] {
			String.class, Object.class
	};
	private static final String[] NAMES = new String[] {
			"Property", "Value"
	};

	class PropertyModel extends AbstractTableModel {

		private JComponent current;
		private BeanInfo info;
		private PropertyDescriptor[] descriptors;

		public void setComponent(JComponent icurrent) {
			try {
				current = icurrent;
				info = Introspector.getBeanInfo(current.getClass());
				descriptors = info.getPropertyDescriptors();
				fireTableDataChanged();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		public Class<?> getColumnClass(int columnIndex) {
			return COLUMNS[columnIndex];
		}

		public int getColumnCount() {
			return COLUMNS.length;
		}

		public String getColumnName(int columnIndex) {
			return NAMES[columnIndex];
		}

		public int getRowCount() {
			return descriptors.length;
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			try {
				if (columnIndex == 0) {
					return (descriptors[rowIndex].getDisplayName());
				}
				Method m = descriptors[rowIndex].getReadMethod();
				if (m != null) {
					return (m.invoke(current, (Object[]) null));
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return null;
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return (columnIndex == 1)
					&& (descriptors[rowIndex].getWriteMethod() != null);
		}

		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			try {
				Method w = descriptors[rowIndex].getWriteMethod();
				if (w != null) {
					w.invoke(current, new Object[] {
						aValue
					});
				} else {
					info.getBeanDescriptor().setValue(
							descriptors[rowIndex].getName(), aValue);
				}
				store();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	class ComponentPositioning extends MouseAdapter implements
			MouseMotionListener {
		private boolean resizeNorth = false;
		private boolean resizeSouth = false;
		private boolean resizeEast = false;
		private boolean resizeWest = false;
		private boolean moveMode = false;
		private Point lastPoint;

		ComponentPositioning(JComponent parent) {
		}

		public void mouseMoved(MouseEvent ev) {
			updateCursor(ev);
		}

		private void updateCursor(MouseEvent ev) {
			reset();
			lastPoint = ev.getPoint();
			JComponent c = (JComponent) ev.getSource();
			if (isNorthComponentBorder(ev)) {
				c.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
				resizeNorth = true;
				return;
			}
			if (isSouthComponentBorder(ev)) {
				c.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
				resizeSouth = true;
				return;
			}
			if (isEastComponentBorder(ev)) {
				c.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
				resizeEast = true;
				return;
			}
			if (isWestComponentBorder(ev)) {
				c.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
				resizeWest = true;
				return;
			}
			if (isInComponent(ev)) {
				c.setCursor(new Cursor(Cursor.MOVE_CURSOR));
				moveMode = true;
				return;
			}
		}

		private void reset() {
			resizeNorth = false;
			resizeSouth = false;
			resizeEast = false;
			resizeWest = false;
			moveMode = false;
			lastPoint = null;
		}

		public void mouseReleased(MouseEvent e) {
			reset();
			store();
		}

		public void mouseDragged(MouseEvent ev) {
			JComponent c = (JComponent) ev.getSource();
			if (moveMode) {
				c.setLocation(ev.getPoint());
				return;
			}

			int x;
			int y;
			if (lastPoint == null) {
				lastPoint = ev.getPoint();
				return;
			} else {
				x = ev.getPoint().x - lastPoint.x;
				y = ev.getPoint().y - lastPoint.y;
				lastPoint = ev.getPoint();
			}

			if (resizeNorth) {
				c.setLocation(c.getX(), Math.max(2, c.getY() + y));
				c.setSize(c.getWidth(), Math.max(2, c.getHeight() - y));
				return;
			}
			if (resizeSouth) {
				c.setSize(c.getWidth(), Math.max(2, c.getHeight() + y));
				return;
			}
			if (resizeEast) {
				c.setLocation(Math.max(2, c.getX() + x), c.getY());
				c.setSize(Math.max(2, c.getWidth() - x), c.getHeight());
				return;
			}
			if (resizeWest) {
				c.setSize(Math.max(2, c.getWidth() + x), c.getHeight());
				return;
			}
		}

		private boolean isInComponent(MouseEvent ev) {
			JComponent c = (JComponent) ev.getSource();
			Rectangle inside = new Rectangle(2, 2, c.getWidth() - 4,
					c.getHeight() - 4);
			return (inside.contains(ev.getPoint()));
		}

		private boolean isNorthComponentBorder(MouseEvent ev) {
			JComponent c = (JComponent) ev.getSource();
			Rectangle border = new Rectangle(0, -2, c.getWidth() + 4, 4);
			return (border.contains(ev.getPoint()));
		}

		private boolean isSouthComponentBorder(MouseEvent ev) {
			JComponent c = (JComponent) ev.getSource();
			Rectangle border = new Rectangle(0, c.getHeight() - 2,
					c.getWidth() + 4, 4);
			return (border.contains(ev.getPoint()));
		}

		private boolean isEastComponentBorder(MouseEvent ev) {
			JComponent c = (JComponent) ev.getSource();
			Rectangle border = new Rectangle(-2, 0, 4, c.getHeight());
			return (border.contains(ev.getPoint()));
		}

		private boolean isWestComponentBorder(MouseEvent ev) {
			JComponent c = (JComponent) ev.getSource();
			Rectangle border = new Rectangle(c.getWidth() - 2, 0, 4,
					c.getHeight());
			return (border.contains(ev.getPoint()));
		}
	}
}
