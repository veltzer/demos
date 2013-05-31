package core.collections;

import java.util.Comparator;

/**
 * A commarator for two strings. The lengths of the strings is the primary
 * comparison parameter, their natural comparison is the secondary parameter.
 * @author Mark Veltzer <mark@veltzer.net>
 */
public class StringsLengthComparator implements Comparator<String> {

	public int compare(String s0, String s1) {
		int lengthDiff = s0.length() - s1.length();
		if (lengthDiff != 0) {
			return lengthDiff;
		}
		return s0.compareTo(s1);
	}

}
