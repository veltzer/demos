package extreme.instrument.agent;

import java.lang.instrument.Instrumentation;

public class SimplePremain {
	/**
	 * @param args
	 */
	public static void premain(String agentArguments,
			Instrumentation instrumentation) {
		instrumentation.addTransformer(new SimpleTransformer());
	}
}
