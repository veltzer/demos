package swing.table_rendering;

public interface SpanModel {
	public boolean spanRight(int row, int column);

	public boolean spanBottom(int row, int column);

	public boolean isSpanRoot(int row, int column);
}
