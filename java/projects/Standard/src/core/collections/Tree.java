package core.collections;

public class Tree<D> {
	private D data;
	private SingleLinkedListCorrectIterOrderGeneric<Tree<D>> children;

	public Tree(D idata) {
		data = idata;
		children = new SingleLinkedListCorrectIterOrderGeneric<Tree<D>>();
	}

	public void add(Tree<D> idata) {
		children.add(idata);
	}

	public void add(D idata) {
		children.add(new Tree<D>(idata));
	}

	public void print(int depth) {
		for (int i = 0; i < depth; i++) {
			System.out.print("\t");
		}
		System.out.println(data);
		SingleLinkedListCorrectIterOrderGeneric.Iterator<Tree<D>> it = children
				.getIterator();
		while (it.hasNext()) {
			Tree<D> curtree = it.next();
			curtree.print(depth + 1);
		}
	}

	public void print() {
		print(0);
	}

	static enum Color {
		red, green, blue, white,
	};

	static class DirOrFile {
		private String name;
		private int id;
		private Color color;

		@Override
		public String toString() {
			return getName() + "," + getId() + ',' + getColor();
		}

		public String getName() {
			return name;
		}

		public void setName(String iname) {
			name = iname;
		}

		public int getId() {
			return id;
		}

		public void setId(int iid) {
			id = iid;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color icolor) {
			color = icolor;
		}
	};

	static class Dir extends DirOrFile {
		public Dir(String iname, int iid, Color icolor) {
			setName(iname);
			setId(iid);
			setColor(icolor);
			// Check that the color is right
		}
	}

	static class File extends DirOrFile {
		public File(String iname, int iid, Color icolor) {
			setName(iname);
			setId(iid);
			setColor(icolor);
			// Check that the color is right
		}
	}

	public static void main(String[] args) {
		Tree<DirOrFile> t = new Tree<DirOrFile>(new Dir("/", 5656, Color.red));
		Tree<DirOrFile> td1 = new Tree<DirOrFile>(new Dir("tmp", 54656,
				Color.white));
		t.add(td1);
		td1.add(new File("soffice", 3454, Color.red));
		Tree<DirOrFile> td2 = new Tree<DirOrFile>(new Dir("etc", 4656,
				Color.blue));
		t.add(td2);
		td2.add(new File("passwd", 3454, Color.red));
		td2.add(new File("hosts", 3454, Color.red));
		t.print();
	}
}
