import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.condition.Condition;
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
public class MediaCondition implements Condition  {

	    private String dir;
	    private boolean isExists = false;

	    // The setter for the "value" attribute
	    public void setdir(String dir) {
	        this.dir = dir;
	    }

	    // This method evaluates the condition
	    public boolean eval() {
	        if (dir == null) {
	            throw new BuildException("dir attribute is not set");
	        }

	        DirectoryScanner ds = new DirectoryScanner();
	        ds.setBasedir(dir);
	        String[] includes = new String[4];
	        includes[0] = "*.gif";	        
	        includes[1] = "*.jpg";
	        includes[2] = "*.jpeg";
	        includes[3] = "*.png";
	        ds.setIncludes(includes);
	        
	        try {
	        	ds.scan();
	        	
	        	String[] files = ds.getIncludedFiles();
	        	if (files != null && files.length > 0) {
	        	   	isExists = true;
        	    }

	        } catch (java.lang.IllegalStateException e) {
	        	System.out.print("The directory '" + dir + "' does not exists");
	        }

	        return isExists;
	   }

}
