package swing.table_cell_renderer;

import java.util.ArrayList;

public class Disk {
	public Disk(String name, long size, long used) {
		super();
		this.name = name;
		this.size = size;
		this.used = used;
	}
	protected String name;
	protected long size;
	protected long used;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public long getUsed() {
		return used;
	}
	public void setUsed(long used) {
		this.used = used;
	}
	private static ArrayList<Disk> instance;
	static synchronized ArrayList<Disk> getDisks() {
		if(instance==null) {
			instance=new ArrayList<Disk>();
			instance.add(new Disk("one", 10000, 5000));
		}
		return instance;
	}
}
