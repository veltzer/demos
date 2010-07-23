package extreme.gc;

/**
 * A very simple class which will be used for pooling.
 * 
 * @author Mark Veltzer
 * 
 */
public class PooledObject {
	private int id;

	private double[] data;

	public PooledObject() {
		super();
		data = new double[] { 1.41, 3.14, 2.71 };
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double[] getData() {
		return data;
	}

	public void setData(double[] data) {
		this.data = data;
	}
}
