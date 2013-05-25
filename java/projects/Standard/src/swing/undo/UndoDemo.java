package swing.undo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.undo.AbstractUndoableEdit;

@SuppressWarnings("serial")
public class UndoDemo extends JPanel {
	private static UndoDemo instance;
	private List<Drawable> drawing = new ArrayList<Drawable>();
	private boolean fillMode = false;
	private Color color = Color.BLACK;
	private boolean rectMode = true;
	private Point startingPoint;
	private Point currentPoint;

	public static class PersistentEllipse extends Ellipse2D.Float {
		public PersistentEllipse(float x, float y, float width, float height) {
			super(x, y, width, height);
		}

		public PersistentEllipse() {
		}

		public void setX(double x) {
			super.x = (float) x;
		}

		public void setY(double y) {
			super.y = (float) y;
		}

		public void setWidth(double width) {
			super.width = (float) width;
		}

		public void setHeight(double height) {
			super.height = (float) height;
		}
	}

	private Shape createCurrentShape() {
		if (rectMode) {
			return new Rectangle(startingPoint.x, startingPoint.y,
					Math.abs(startingPoint.x - currentPoint.x),
					Math.abs(startingPoint.y - currentPoint.y));
		} else {
			return new PersistentEllipse(startingPoint.x, startingPoint.y,
					Math.abs(startingPoint.x - currentPoint.x),
					Math.abs(startingPoint.y - currentPoint.y));
		}
	}

	Drawable createDrawable() {
		Drawable d = new Drawable();
		d.shape = createCurrentShape();
		d.color = color;
		d.filled = fillMode;
		return d;
	}

	public static class Drawable extends AbstractUndoableEdit implements
			Serializable {
		private Color color;
		private Shape shape;
		private boolean filled;

		public Color getColor() {
			return color;
		}

		public void setColor(Color icolor) {
			color = icolor;
		}

		public Shape getShape() {
			return shape;
		}

		public void setShape(Shape ishape) {
			shape = ishape;
		}

		public boolean isFilled() {
			return filled;
		}

		public void setFilled(boolean ifilled) {
			filled = ifilled;
		}

		public String getPresentationName() {
			if (shape instanceof Rectangle) {
				return "Draw rectangle";
			} else {
				return "Draw circle";
			}
		}

		public boolean canRedo() {
			return true;
		}

		public void undo() {
			instance.drawing.remove(this);
			instance.repaint();
		}

		public void redo() {
			instance.drawing.add(this);
			instance.repaint();
		}

		public String toString() {
			return getPresentationName() + ": [" + color + "], [" + shape
					+ "], [" + filled + "]";
		}
	}

	class ToggleFillModeAction extends AbstractAction {
		ToggleFillModeAction() {
			super("Fill");
		}

		public void actionPerformed(ActionEvent ev) {
			fillMode = (!fillMode);
		}
	}

	class SelectColorAction extends AbstractAction {
		SelectColorAction() {
			super("Color");
		}

		public void actionPerformed(ActionEvent ev) {
			color = JColorChooser.showDialog(UndoDemo.this, "Select Color",
					color);
			((JComponent) ev.getSource()).setBackground(color);
		}
	}

	class DrawRectModeAction extends AbstractAction {
		DrawRectModeAction() {
			super("Rectangle");
		}

		public void actionPerformed(ActionEvent ev) {
			rectMode = true;
		}
	}

	class DrawCircleModeAction extends AbstractAction {
		DrawCircleModeAction() {
			super("Circle");
		}

		public void actionPerformed(ActionEvent ev) {
			rectMode = false;
		}
	}

	public UndoDemo() {
		instance = this;
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				currentPoint = e.getPoint();
				if (startingPoint == null) {
					startingPoint = e.getPoint();
					Drawable d = createDrawable();
					d.redo();
					UndoAction.add(d);
				} else {
					Drawable d = drawing.get(drawing.size() - 1);
					d.shape = createCurrentShape();
					repaint();
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				startingPoint = null;
				repaint();
			}
		});
	}

	private JToolBar createToolBar() {
		JToolBar toolbar = new JToolBar();
		toolbar.add(UndoAction.getInstance());
		toolbar.add(RedoAction.getInstance());
		toolbar.add(new JToggleButton(new ToggleFillModeAction()));
		toolbar.add(new SelectColorAction());
		JToggleButton rectModeB = new JToggleButton(new DrawRectModeAction());
		rectModeB.setSelected(true);
		toolbar.add(rectModeB);
		JToggleButton circleMode = new JToggleButton(new DrawCircleModeAction());
		toolbar.add(circleMode);
		ButtonGroup group = new ButtonGroup();
		group.add(rectModeB);
		group.add(circleMode);

		toolbar.addSeparator();
		toolbar.add(UndoAction.getInstance().getRecordAction());
		toolbar.add(UndoAction.getInstance().getStopAction());
		toolbar.add(UndoAction.getInstance().getPlayAction());
		toolbar.add(UndoAction.getInstance().getStoreAction());
		toolbar.add(UndoAction.getInstance().getLoadAction());

		return toolbar;
	}

	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		Iterator<Drawable> iter = drawing.iterator();
		while (iter.hasNext()) {
			Drawable d = iter.next();
			g.setColor(d.color);
			if (d.filled) {
				g.fill(d.shape);
			} else {
				g.draw(d.shape);
			}
		}
	}

	public static void main(String[] argv) {
		JFrame dlg = new JFrame();
		dlg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UndoDemo demo = new UndoDemo();
		dlg.getContentPane().add("Center", demo);
		dlg.getContentPane().add("North", demo.createToolBar());
		dlg.pack();
		dlg.setVisible(true);
	}
}
