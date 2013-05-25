import java.util.Collection;
import java.util.Iterator;

import org.apache.tools.ant.Task;

public class ProjectDetails extends Task {
	public void execute() {
		this.log("name: " + this.getProject().getName());
		this.log("description: " + this.getProject().getDescription());
		this.log("default target: " + this.getProject().getDefaultTarget());
		Collection targets = this.getProject().getTargets().values();
		for (Iterator iterator = targets.iterator(); iterator.hasNext();) {
			String task = iterator.next().toString();
			if (task != null && !task.trim().equals("")) {
				this.log(" - " + task + " task");
			}
		}
		this.log("base dir: " + this.getProject().getBaseDir());
	}
}
