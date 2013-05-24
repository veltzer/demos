import java.util.Collection;
import java.util.Iterator;

import org.apache.tools.ant.Task;
/*
 * Created on 03/03/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author arik
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProjectDetails extends Task {


	
	public void execute() {
		this.log("name: " + this.getProject().getName());
		this.log("description: " + this.getProject().getDescription());
		this.log("default target: " + this.getProject().getDefaultTarget());
		Collection targets = this.getProject().getTargets().values();
		for (Iterator iterator = targets.iterator(); iterator.hasNext();) {
			String task = iterator.next().toString();
			if (task!=null && !task.trim().equals("")) {
				this.log( " - " + task + " task");
			}
		}		
		this.log("base dir: " + this.getProject().getBaseDir());
	}	
}
