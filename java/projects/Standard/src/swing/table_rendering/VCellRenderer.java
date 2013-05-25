package swing.table_rendering;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class VCellRenderer extends JLabel implements Border, TableCellRenderer {
	private final JCheckBox booleanRenderer = new JCheckBox() {
		public void paint(Graphics graphics) {
			super.paint(graphics);
			graphics.setColor(gridColor);
			graphics.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
			graphics.drawLine(getWidth() - 1, 0, getWidth() - 1,
					getHeight() - 1);
		}
	};
	private static final Insets BOTH = new Insets(0, 0, 1, 1);
	private Color gridColor;

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (value == null) {
			value = "";
		} else {
			if (value instanceof Date) {
				value = DateFormat.getDateInstance(DateFormat.SHORT).format(
						(Date) value);
			} else {
				if (value instanceof Currency) {
					Currency currency = (Currency) value;
					NumberFormat format = NumberFormat
							.getCurrencyInstance(currency.getType());
					value = format.format(currency.getValue());
				}
			}
		}
		gridColor = table.getGridColor();
		JComponent cmp = this;
		if (value instanceof Boolean) {
			booleanRenderer.setHorizontalAlignment(JLabel.CENTER);
			if (isSelected) {
				booleanRenderer.setForeground(table.getSelectionForeground());
				booleanRenderer.setBackground(table.getSelectionBackground());
			} else {
				booleanRenderer.setForeground(table.getForeground());
				booleanRenderer.setBackground(table.getBackground());
			}
			booleanRenderer.setSelected(((Boolean) value).booleanValue());
			cmp = booleanRenderer;
		} else {
			setText(value.toString());
			setBorder(this);
			cmp = this;
		}
		CTable ctable = (CTable) table;

		cmp.setFont(table.getFont());

		TableColorModel colorModel = ((CTable) table).getTableColorModel();
		if (isSelected) {
			cmp.setBackground(colorModel.getSelectedBackground(row, column));
			cmp.setForeground(colorModel.getSelectedForeground(row, column));
		} else {
			cmp.setBackground(colorModel.getBackground(row, column));
			cmp.setForeground(colorModel.getForeground(row, column));
		}

		cmp.setComponentOrientation(table.getComponentOrientation());

		TableAlignmentModel alignmentModel = ((CTable) table)
				.getAlignmentModel();
		if (alignmentModel != null) {
			if (alignmentModel.isAligned(column)) {
				if (alignmentModel.isRightAligned(column)) {
					((JLabel) cmp).setHorizontalAlignment(SwingConstants.RIGHT);
				} else {
					if (alignmentModel.isLeftAligned(column)) {
						((JLabel) cmp)
								.setHorizontalAlignment(SwingConstants.LEFT);
					} else {
						((JLabel) cmp)
								.setHorizontalAlignment(SwingConstants.CENTER);
					}
				}
			} else {
				if (cmp instanceof JLabel) {
					if (cmp.getComponentOrientation() == ComponentOrientation.LEFT_TO_RIGHT) {
						((JLabel) cmp)
								.setHorizontalAlignment(SwingConstants.LEFT);
					} else {
						((JLabel) cmp)
								.setHorizontalAlignment(SwingConstants.RIGHT);
					}
				}
			}
		}

		cmp = span(row, column, cmp, ctable.getSpan(), table);
		return (cmp);
	}

	public Insets getBorderInsets(Component component) {
		return BOTH;
	}

	public boolean isBorderOpaque() {
		return true;
	}

	public void paintBorder(Component component, Graphics graphics, int param,
			int param3, int param4, int param5) {
		graphics.setColor(gridColor);
		graphics.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
		graphics.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
	}

	/**
	 * We recycle the image for performance
	 */
	private BufferedImage img = new BufferedImage(300, 300,
			BufferedImage.TYPE_INT_RGB);

	private Map<Cell, JLabel> cells = new HashMap<Cell, JLabel>();

	public JComponent span(int row, int column, JComponent cmp,
			SpanModel model, JTable table) {
		if (model.isSpanRoot(row, column)) {
			int spanRows = row;
			int spanColumns = column;
			while (model.spanBottom(spanRows, column)) {
				spanRows++;
			}
			while (model.spanRight(row, spanColumns)) {
				spanColumns++;
			}
			int height = 0;
			for (int iter = row; iter <= spanRows; iter++) {
				height += table.getRowHeight(iter);
			}
			int width = 0;
			for (int counter = column; counter <= spanColumns; counter++) {
				width += table.getColumnModel().getColumn(counter).getWidth();
			}
			if (width > img.getWidth() || height > img.getHeight()) {
				img = new BufferedImage(Math.max(img.getWidth(), width),
						Math.max(img.getHeight(), height),
						BufferedImage.TYPE_INT_RGB);
			}
			cmp.setSize(width, height);
			cmp.setOpaque(true);
			Graphics g = img.getGraphics();
			cmp.paint(g);
			g.dispose();

			// breakup the rest
			int x = 0;
			int y = 0;
			for (int cCell = column; cCell <= spanColumns; cCell++) {
				width = table.getColumnModel().getColumn(cCell).getWidth();
				for (int rCell = row; rCell <= spanRows; rCell++) {
					height = table.getRowHeight(rCell);
					BufferedImage currentImage = img.getSubimage(x, y, width,
							height);
					JLabel label = new JLabel(new ImageIcon(currentImage));
					cells.put(new Cell(rCell, cCell), label);
					y += height;
				}
				x += width;
				y = 0;
			}
		}
		JComponent comp = cells.get(new Cell(row, column));
		if (comp == null) {
			return cmp;
		}
		return comp;
	}

	static class Cell {
		private int row;
		private int column;

		public Cell(int irow, int icolumn) {
			row = irow;
			column = icolumn;
		}

		public boolean equals(Object obj) {
			if (obj.getClass() != getClass()) {
				return false;
			}
			Cell c = (Cell) obj;
			return (c.row == row && c.column == column);
		}

		public int hashCode() {
			return (row + (column >> 10));
		}
	}
}
