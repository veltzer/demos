package swing.table_cell_renderer;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyCellRenderer implements TableCellRenderer {
	
	private static class Render extends JComponent {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public Disk disk;

		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			//Graphics2D g2=(Graphics2D)g;
			//Rectangle rec=g2.getClipBounds();
			//g2.drawLine(rec.x, rec.y, rec.width, rec.height);
		}
		
	}
	
	private static Render r=new Render();
	

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		r.disk=(Disk)value;
		return r;
	}

}
