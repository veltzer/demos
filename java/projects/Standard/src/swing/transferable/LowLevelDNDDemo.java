package swing.transferable;

import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LowLevelDNDDemo extends JPanel {
	private static final DataFlavor[] FLAVORS = new DataFlavor[] {
			DataFlavor.stringFlavor,
			new DataFlavor("application/vnd-vPrise-LowLevelDNDDemo",
					"DnD demo flavor")
	};
	private DragSource source = DragSource.getDefaultDragSource();
	private Transferable transferable = new Transferable() {
		public Object getTransferData(DataFlavor flavor) {
			if (flavor.equals(FLAVORS[0])) {
				return LowLevelDNDDemo.this.getName();
			} else {
				return this;
			}
		}

		public DataFlavor[] getTransferDataFlavors() {
			return FLAVORS;
		}

		public boolean isDataFlavorSupported(DataFlavor flavor) {
			for (int iter = 0; iter < FLAVORS.length; iter++) {
				if (FLAVORS[iter].equals(flavor)) {
					return true;
				}
			}
			return false;
		}
	};

	public LowLevelDNDDemo() {
		setName("This is the name of the component");
		source.createDefaultDragGestureRecognizer(this,
				DnDConstants.ACTION_COPY | DnDConstants.ACTION_MOVE,
				new DragGestureListener() {
					public void dragGestureRecognized(DragGestureEvent dge) {
						Cursor cursor;
						if (dge.getDragAction() == DnDConstants.ACTION_MOVE) {
							cursor = DragSource.DefaultMoveDrop;
						} else {
							cursor = DragSource.DefaultCopyDrop;
						}
						dge.startDrag(cursor, transferable);
					}
				});

		new DropTarget(this, DnDConstants.ACTION_COPY
				| DnDConstants.ACTION_MOVE, new DropTargetListener() {
			public void dragEnter(DropTargetDragEvent dtde) {
				int action = dtde.getDropAction();
				if (action == DnDConstants.ACTION_COPY
						|| action == DnDConstants.ACTION_MOVE) {
					dtde.acceptDrag(action);
				}
			}

			public void dragExit(DropTargetEvent dte) {
			}

			public void dragOver(DropTargetDragEvent dtde) {
			}

			public void drop(DropTargetDropEvent dtde) {
				int action = dtde.getDropAction();
				if (action == DnDConstants.ACTION_COPY
						|| action == DnDConstants.ACTION_MOVE) {
					dtde.acceptDrop(action);
				}
				String flavors = dtde.getCurrentDataFlavorsAsList().toString();
				dtde.dropComplete(true);
				System.out.println("Drop flavors: " + flavors);
			}

			public void dropActionChanged(DropTargetDragEvent dtde) {
			}
		}, true);
	}

	public static void main(String[] argv) {
		JFrame dlg = new JFrame();
		dlg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dlg.getContentPane().add("Center", new LowLevelDNDDemo());
		dlg.pack();
		dlg.setVisible(true);
	}
}
