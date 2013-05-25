package spring.aop;

public class DefaultUsageTracked implements UsageTracked {
	private int x;

	@Override
	public void inc() {
		x++;
	}

	@Override
	public int getCount() {
		return x;
	}
}
