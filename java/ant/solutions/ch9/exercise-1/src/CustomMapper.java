import org.apache.tools.ant.util.GlobPatternMapper;
/*
 * Created on 03/03/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author arik
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CustomMapper extends GlobPatternMapper {

	protected final static String FROM_POST_PREFIX_ABC = ".abc";
	protected final static String TO_POST_PREFIX_XYZ = ".xyz";

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
