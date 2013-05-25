package swing.table_rendering;

public interface SpanModel {
	boolean spanRight(int row, int column);

	boolean spanBottom(int row, int column);

	boolean isSpanRoot(int row, int column);
}
