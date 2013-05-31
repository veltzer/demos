package core.gc;

/**
 * A very simple class which will be used for pooling.
 * @author Mark Veltzer <mark@veltzer.net>
 */
public class PooledObject {
	private int id;

	private double[] data;

	public PooledObject() {
		super();
		data = new double[] {
				1.41, 3.14, 2.71
		};
	}

	public int getId() {
		return id;
	}

	public void setId(int iid) {
		id = iid;
	}

	public double[] getData() {
		return data;
	}

	public void setData(double[] idata) {
		data = idata;
	}
}
