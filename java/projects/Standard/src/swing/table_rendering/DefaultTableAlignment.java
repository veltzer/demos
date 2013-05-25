package swing.table_rendering;

import javax.swing.SwingConstants;

public class DefaultTableAlignment implements TableAlignmentModel {
	private int[] alignments;

	public DefaultTableAlignment(int[] ialignments) {
		alignments = ialignments;
	}

	public boolean isAligned(int column) {
		return alignments[column] > -1;
	}

	public boolean isLeftAligned(int column) {
		return alignments[column] == SwingConstants.LEFT;
	}

	public boolean isRightAligned(int column) {
		return alignments[column] == SwingConstants.RIGHT;
	}

}
