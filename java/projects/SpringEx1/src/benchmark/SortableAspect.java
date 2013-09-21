package benchmark;


import meta.sorter.Sorted;
import meta.sorter.SortedImpl;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class SortableAspect {
	@DeclareParents(value = "meta.sorter.impl.SorterImpl+", defaultImpl = SortedImpl.class)
	private static Sorted mixin;

	@Before("execution(* meta.*.*.sort(..)) &&" + "this(sorted)")
	public void setSorted(Sorted sorted) {
		sorted.setSorted();
	}

}
