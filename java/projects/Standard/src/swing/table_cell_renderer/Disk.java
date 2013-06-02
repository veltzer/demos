package swing.table_cell_renderer;

import java.util.ArrayList;

public class Disk {
	public Disk(String iname, long isize, long iused) {
		super();
		name = iname;
		size = isize;
		used = iused;
	}

	private String name;
	private long size;
	private long used;

	public String getName() {
		return name;
	}

	public void setName(String iname) {
		name = iname;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long isize) {
		size = isize;
	}

	public long getUsed() {
		return used;
	}

	public void setUsed(long iused) {
		used = iused;
	}

	private static ArrayList<Disk> instance;

	static synchronized ArrayList<Disk> getDisks() {
		if (instance == null) {
			instance = new ArrayList<Disk>();
			instance.add(new Disk("one", 10000, 5000));
		}
		return instance;
	}
}
