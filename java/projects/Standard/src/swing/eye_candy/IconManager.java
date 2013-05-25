package swing.eye_candy;

import java.util.Map;
import java.util.WeakHashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public final class IconManager {
	private static IconManager instance = new IconManager();
	private Map<String, Icon> icons = new WeakHashMap<String, Icon>();

	private IconManager() {
	}

	private Icon getIconImpl(String icon) {
		Icon current = icons.get(icon);
		if (current == null) {
			try {
				current = new ImageIcon(getClass().getResource(icon));
				icons.put(icon, current);
			} catch (RuntimeException err) {
				System.out.println("Error is icon: " + icon);
				throw (err);
			}
		}
		return (current);
	}

	public static Icon getIcon(String icon) {
		return (instance.getIconImpl(icon));
	}
}
