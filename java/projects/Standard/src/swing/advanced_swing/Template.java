package swing.advanced_swing;
import javax.swing.JComponent;
public interface Template {
    /**
     *  Returns the template object with the appropriate elements in place
     *
     *@return    The view value
     */
    public JComponent getView();


    /**
     *  Places the given components in the appropriate container location
     *
     *@param  location    The new components value
     *@param  components  The new components value
     */
    public void setComponents(String location, JComponent[] components);
}

