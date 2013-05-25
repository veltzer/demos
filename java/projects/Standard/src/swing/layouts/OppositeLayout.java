package swing.layouts;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

public class OppositeLayout implements LayoutManager {

	/**
	 * This is a stateless layout.
	 */
	public OppositeLayout() {
	}

	public void addLayoutComponent(String name, Component comp) {
	}

	public void layoutContainer(Container parent) {
		boolean isRtl = ComponentOrientation.RIGHT_TO_LEFT.equals(parent
				.getComponentOrientation());
		Insets insets = parent.getInsets();
		Dimension size = parent.getSize();

		Component leftComponent = parent.getComponent(0);
		Component rightComponent = parent.getComponent(1);
		if (isRtl) {
			Component swap = leftComponent;
			leftComponent = rightComponent;
			rightComponent = swap;
		}
		leftComponent.setBounds(insets.left, insets.top,
				leftComponent.getPreferredSize().width,
				leftComponent.getPreferredSize().height);
		rightComponent.setBounds(size.width
				- rightComponent.getPreferredSize().width - insets.right,
				insets.top, rightComponent.getPreferredSize().width,
				rightComponent.getPreferredSize().height);
	}

	public Dimension minimumLayoutSize(Container parent) {
		Component leftComponent = parent.getComponent(0);
		Component rightComponent = parent.getComponent(1);
		Dimension result = new Dimension();
		result.width = leftComponent.getMinimumSize().width
				+ rightComponent.getMinimumSize().width;
		result.height = Math.max(leftComponent.getMinimumSize().height,
				rightComponent.getMinimumSize().height);
		return result;
	}

	public Dimension preferredLayoutSize(Container parent) {
		Component leftComponent = parent.getComponent(0);
		Component rightComponent = parent.getComponent(1);
		Dimension result = new Dimension();
		result.width = leftComponent.getPreferredSize().width
				+ rightComponent.getPreferredSize().width;
		result.height = Math.max(leftComponent.getPreferredSize().height,
				rightComponent.getPreferredSize().height);
		return result;
	}

	public void removeLayoutComponent(Component comp) {
	}

}
