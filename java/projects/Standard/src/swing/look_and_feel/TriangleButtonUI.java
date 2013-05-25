package swing.look_and_feel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.plaf.metal.MetalComboBoxButton;
import javax.swing.text.View;

public class TriangleButtonUI extends MetalButtonUI {
	private static Rectangle iconRect = new Rectangle();
	private static Rectangle textRect = new Rectangle();
	private static Rectangle viewRect = new Rectangle();
	private static final TriangleButtonUI TRIANGLEBUTTONUI = new TriangleButtonUI();

	public static ComponentUI createUI(JComponent c) {
		return TRIANGLEBUTTONUI;
	}

	public Dimension getMaximumSize(JComponent c) {
		Dimension d = getPreferredSize(c);
		View v = (View) c.getClientProperty(BasicHTML.propertyKey);
		if (v != null) {
			d.width += v.getMaximumSpan(View.X_AXIS)
					- v.getPreferredSpan(View.X_AXIS);
		}
		return d;
	}

	public Dimension getMinimumSize(JComponent c) {
		Dimension d = getPreferredSize(c);
		View v = (View) c.getClientProperty(BasicHTML.propertyKey);
		if (v != null) {
			d.width -= v.getPreferredSpan(View.X_AXIS)
					- v.getMinimumSpan(View.X_AXIS);
		}
		return d;
	}

	public Dimension getPreferredSize(JComponent c) {
		AbstractButton b = (AbstractButton) c;
		return BasicGraphicsUtils.getPreferredButtonSize(b, b.getIconTextGap());
	}

	public void installDefaults(AbstractButton b) {
		super.installDefaults(b);
		if (!(b instanceof MetalComboBoxButton)) {
			b.setBorder(new TriangleEtchedBorder());
		}
	}

	public void paint(Graphics g, JComponent c) {
		AbstractButton b = (AbstractButton) c;
		ButtonModel model = b.getModel();

		FontMetrics fm = g.getFontMetrics();

		Insets i = c.getInsets();

		viewRect.x = i.left;
		viewRect.y = i.top;
		viewRect.width = b.getWidth() - (i.right + viewRect.x);
		viewRect.height = b.getHeight() - (i.bottom + viewRect.y);

		textRect.x = 0;
		textRect.y = 0;
		textRect.width = 0;
		textRect.height = 0;
		iconRect.x = 0;
		iconRect.y = 0;
		iconRect.width = 0;
		iconRect.height = 0;

		Font f = c.getFont();
		g.setFont(f);

		int gap;
		if (b.getText() == null) {
			gap = 0;
		} else {
			gap = b.getIconTextGap();
		}
		String text = SwingUtilities.layoutCompoundLabel(c, fm, b.getText(),
				b.getIcon(), b.getVerticalAlignment(),
				b.getHorizontalAlignment(), b.getVerticalTextPosition(),
				b.getHorizontalTextPosition(), viewRect, iconRect, textRect,
				gap);

		clearTextShiftOffset();

		if (model.isArmed() && model.isPressed()) {
			paintButtonPressed(g, b);
		}

		if (b.getIcon() != null) {
			paintIcon(g, c, iconRect);
		}

		if (text != null && !text.equals("")) {
			View v = (View) c.getClientProperty(BasicHTML.propertyKey);
			if (v != null) {
				v.paint(g, textRect);
			} else {
				paintText(g, b, textRect, text);
			}
		}

		if (b.isFocusPainted() && b.hasFocus()) {
			// paint UI specific focus
			paintFocus(g, b, viewRect, textRect, iconRect);
		}
	}

	public void uninstallDefaults(AbstractButton b) {
		super.uninstallDefaults(b);
		b.setBorder(null);
	}

	protected void paintButtonPressed(Graphics g, AbstractButton b) {
		if (b.isContentAreaFilled()) {
			Dimension size = b.getSize();
			g.setColor(getSelectColor());
			g.fillPolygon(new int[] {
					0, size.width - 1, size.width / 2 - 1
			}, new int[] {
					size.height - 2, size.height - 2, 0
			}, 3);
		}
	}

	protected void paintText(Graphics g, AbstractButton b, Rectangle itextRect,
			String text) {
		paintText(g, (JComponent) b, itextRect, text);
	}
}
