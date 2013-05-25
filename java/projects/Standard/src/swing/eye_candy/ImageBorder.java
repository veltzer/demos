package swing.eye_candy;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public class ImageBorder implements Border {
	private Icon topLeftCorner;
	private Icon topRightCorner;
	private Icon bottomLeftCorner;
	private Icon bottomRightCorner;
	private Icon top;
	private Icon bottom;
	private Icon left;
	private Icon right;

	public ImageBorder(Icon itopLeftCorner, Icon itopRightCorner,
			Icon ibottomLeftCorner, Icon ibottomRightCorner, Icon itop,
			Icon ibottom, Icon ileft, Icon iright) {
		if (itopLeftCorner == null) {
			itopLeftCorner = new NullIcon();
		}

		if (itopRightCorner == null) {
			itopRightCorner = new NullIcon();
		}

		if (ibottomLeftCorner == null) {
			ibottomLeftCorner = new NullIcon();
		}

		if (ibottomRightCorner == null) {
			ibottomRightCorner = new NullIcon();
		}

		if (ileft == null) {
			ileft = new NullIcon();
		}

		if (iright == null) {
			iright = new NullIcon();
		}

		if (itop == null) {
			itop = new NullIcon();
		}

		if (ibottom == null) {
			ibottom = new NullIcon();
		}
		topLeftCorner = itopLeftCorner;
		topRightCorner = itopRightCorner;
		bottomLeftCorner = ibottomLeftCorner;
		bottomRightCorner = ibottomRightCorner;
		left = ileft;
		right = iright;
		top = itop;
		bottom = ibottom;
	}

	public ImageBorder(URL itopLeftCorner, URL itopRightCorner,
			URL ibottomLeftCorner, URL ibottomRightCorner, URL itop,
			URL ibottom, URL ileft, URL iright) {
		this(createIcon(itopLeftCorner), createIcon(itopRightCorner),
				createIcon(ibottomLeftCorner), createIcon(ibottomRightCorner),
				createIcon(itop), createIcon(ibottom), createIcon(ileft),
				createIcon(iright));
	}

	public ImageBorder(String itopLeftCorner, String itopRightCorner,
			String ibottomLeftCorner, String ibottomRightCorner, String itop,
			String ibottom, String ileft, String iright) {
		this(createIcon(itopLeftCorner), createIcon(itopRightCorner),
				createIcon(ibottomLeftCorner), createIcon(ibottomRightCorner),
				createIcon(itop), createIcon(ibottom), createIcon(ileft),
				createIcon(iright));
	}

	private static Icon createIcon(String icon) {
		if (icon == null) {
			return (null);
		}
		return (new ImageIcon(ImageBorder.class.getResource(icon)));
	}

	private static Icon createIcon(URL icon) {
		if (icon == null) {
			return (null);
		}
		return (new ImageIcon(icon));
	}

	public Insets getBorderInsets(Component c) {
		return (new Insets(top.getIconHeight(), left.getIconWidth(),
				bottom.getIconHeight(), right.getIconWidth()));
	}

	public boolean isBorderOpaque() {
		return (false);
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		topLeftCorner.paintIcon(c, g, x, y);
		topRightCorner.paintIcon(c, g,
				x + width - topRightCorner.getIconWidth(), y);
		bottomLeftCorner.paintIcon(c, g, x,
				y + height - bottomLeftCorner.getIconHeight());
		bottomRightCorner.paintIcon(c, g,
				x + width - bottomRightCorner.getIconWidth(), y + height
						- bottomRightCorner.getIconHeight());

		if (!(top instanceof NullIcon)) {
			Graphics clipped = g.create(
					x + topLeftCorner.getIconWidth(),
					y,
					width - topLeftCorner.getIconWidth()
							- topRightCorner.getIconWidth(),
					top.getIconHeight());
			int widthInc = top.getIconWidth();
			for (int itop = 0; itop < width; itop += widthInc) {
				top.paintIcon(c, clipped, itop, 0);
			}
		}

		if (!(bottom instanceof NullIcon)) {
			Graphics clipped = g.create(
					x + bottomLeftCorner.getIconWidth(),
					y + height - bottom.getIconHeight(),
					width - bottomLeftCorner.getIconWidth()
							- bottomRightCorner.getIconWidth(),
					bottom.getIconHeight());
			int widthInc = bottom.getIconWidth();
			for (int ibottom = 0; ibottom < width; ibottom += widthInc) {
				bottom.paintIcon(c, clipped, ibottom, 0);
			}
		}

		if (!(left instanceof NullIcon)) {
			Graphics clipped = g.create(x, y + topLeftCorner.getIconHeight(),
					left.getIconWidth(),
					y + height - bottomLeftCorner.getIconHeight()
							- topLeftCorner.getIconHeight());
			int heightInc = left.getIconHeight();
			for (int ileft = 0; ileft < height; ileft += heightInc) {
				left.paintIcon(c, clipped, 0, ileft);
			}
		}

		if (!(right instanceof NullIcon)) {
			Graphics clipped = g.create(x + width - right.getIconWidth(), y
					+ topRightCorner.getIconHeight(), right.getIconWidth(), y
					+ height - bottomRightCorner.getIconHeight()
					- topRightCorner.getIconHeight());
			int heightInc = right.getIconHeight();
			for (int iright = 0; iright < height; iright += heightInc) {
				right.paintIcon(c, clipped, 0, iright);
			}
		}
	}

	static class NullIcon implements Icon {

		public int getIconHeight() {
			return (0);
		}

		public int getIconWidth() {
			return (0);
		}

		public void paintIcon(Component c, Graphics g, int x, int y) {
		}

	}
}
