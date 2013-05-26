import org.apache.tools.ant.util.GlobPatternMapper;

public class CustomMapper extends GlobPatternMapper {

	protected static final String FROM_POST_PREFIX_ABC = ".abc";
	protected static final String TO_POST_PREFIX_XYZ = ".xyz";

	public void setFrom(String from) {
		super.setFrom(from);
		if (from != null) {
			if (fromPrefix == null) {
				super.fromPrefix = from;
			}
			super.fromPostfix = FROM_POST_PREFIX_ABC;
		}
		prefixLength = fromPrefix.length();
		postfixLength = fromPostfix.length();
	}

	public void setTo(String to) {
		super.setTo(to);
		if (to != null) {
			if (toPrefix == null) {
				super.toPrefix = to;
			}
			super.toPostfix = TO_POST_PREFIX_XYZ;
		}
	}

	public void execute() {
	}
}
