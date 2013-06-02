package programming.samples.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class Test {

	private static class StringCompareReverse implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			String s1 = o1;
			String s2 = o2;
			return (-1) * s1.compareTo(s2);
		}
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("mark");
		list.add("doron");
		list.add("avi");
		Collections.sort(list, new StringCompareReverse());
		for (String s : list) {
			System.out.println(s);
		}
		/* This is pre 1.5 code */
		/*
		 * List list=new ArrayList(); list.add("mark"); list.add("doron");
		 * list.add("avi"); Collections.sort(list); Iterator it=list.iterator();
		 * while(it.hasNext()) { String s=(String)it.next();
		 * System.out.println(s); }
		 */
	}

}
