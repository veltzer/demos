package swing.look_and_feel;

import javax.swing.UIDefaults;
import javax.swing.plaf.metal.MetalLookAndFeel;

@SuppressWarnings("serial")
public class TriangleLookAndFeel extends MetalLookAndFeel {
	public String getDescription() {
		return ("A wacky demo of what Swing can really do");
	}

	public String getName() {
		return ("Triangle");
	}

	public boolean isNativeLookAndFeel() {
		return (false);
	}

	public boolean isSupportedLookAndFeel() {
		return (true);
	}

	protected void initClassDefaults(UIDefaults table) {
		super.initClassDefaults(table);

		Object[] uiDefaults = {
				"ButtonUI", "TriangleButtonUI",
		};

		table.putDefaults(uiDefaults);
	}
}
