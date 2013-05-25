package swing.i18n;

import java.awt.Color;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.WeakHashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public final class ResourceManager {
	private static ResourceManager instance = new ResourceManager();
	private Map<String, Icon> icons = new WeakHashMap<String, Icon>();
	private ResourceBundle bundle;

	private ResourceManager() {
		bundle = ResourceBundle.getBundle("resources");
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
		return (instance.getIconImpl(instance.bundle.getString(icon)));
	}

	public static String getString(String str) {
		return (instance.bundle.getString(str));
	}

	public static Color getColor(String color) {
		return (Color.decode(getString(color)));
	}
}
