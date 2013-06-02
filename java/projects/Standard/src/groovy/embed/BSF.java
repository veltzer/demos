package groovy.embed;

import java.util.List;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;

public abstract class BSF {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String myScript = "println('Hello World')\nreturn [1, 2, 3]";
		BSFManager manager = new BSFManager();
		List<Integer> answer;
		try {
			answer = (List<Integer>) manager.eval("groovy", "myScript.groovy",
					0, 0, myScript);
		} catch (BSFException e) {
			throw new RuntimeException(e);
		}
		assert (3 == answer.size());
	}
}
