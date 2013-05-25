package swing.table_rendering;

public interface TableAlignmentModel {
	/**
	 * Returns true if a user specified alignment exists, if false the default
	 * alignment is chosen.
	 */
	public boolean isAligned(int column);

	/**
	 * Returns true if the column should be aligned to the right. This method is
	 * only meaningful if isAligned returned true for this column.
	 */
	public boolean isRightAligned(int column);

	/**
	 * Returns true if the column should be aligned to the left. This method is
	 * only meaningful if isAligned returned true for this column.
	 */
	public boolean isLeftAligned(int column);
}
