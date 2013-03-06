package benchmark;

import interbit.sorter.Sorted;
import interbit.sorter.SortedImpl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class SortableAspect {
	@DeclareParents(value = "interbit.sorter.impl.SorterImpl+", 
			defaultImpl = SortedImpl.class)
	public static Sorted mixin;

	@Before("execution(* interbit.*.*.sort(..)) &&" + "this(sorted)")
	public void setSorted(Sorted sorted) {
		sorted.setSorted();
	}

}
