package core.collections;

public class Tree<D> {
	private D data;
	private SingleLinkedListCorrectIterOrderGeneric<Tree<D>> children;
	
	public Tree(D idata) {
		data=idata;
        children=new SingleLinkedListCorrectIterOrderGeneric<Tree<D>>();
	}
	public void addSubtree(Tree<D> data) {
		children.add(data);
	}
    public void addLeaf(D data) {
    	children.add(new Tree<D>(data));
    }
    public void print(int depth) {
        for(int i=0;i<depth;i++) {
        	System.out.print("\t");
        }
        System.out.println(data);
        SingleLinkedListCorrectIterOrderGeneric.Iterator<Tree<D>> it=children.getIterator();
        while(it.hasNext()) {
        	Tree<D> curtree=it.next();
            curtree.print(depth+1);
        }
    }
	
	static enum Color {
	    red,
	    green,
	    blue,
	    white,
	};

	static class DirOrFile {
		@Override
		public String toString() {
			return name+","+id+','+color;
		}
		protected String name;
		protected int id;
		protected Color color;
        
	};
	static class Dir extends DirOrFile {
		public Dir(String iname,int iid, Color icolor) {
			name=iname;
			id=iid;
			color=icolor;
			// Check that the color is right
		}
	}
	static class File extends DirOrFile {
		public File(String iname,int iid, Color icolor) {
			name=iname;
			id=iid;
			color=icolor;
			// Check that the color is right
		}
	}
    
	public static void main(String[] args) {
		Tree<DirOrFile> t=new Tree<DirOrFile>(new Dir("/",5656,Color.red));
        Tree<DirOrFile> td1=new Tree<DirOrFile>(new Dir("tmp",54656,Color.white));
		t.addSubtree(td1);
        td1.addLeaf(new File("soffice",3454,Color.red));
        Tree<DirOrFile> td2=new Tree<DirOrFile>(new Dir("etc",4656,Color.blue));
		t.addSubtree(td2);
        td2.addLeaf(new File("passwd",3454,Color.red));
        td2.addLeaf(new File("hosts",3454,Color.red));
        t.print(0);
	}
}
