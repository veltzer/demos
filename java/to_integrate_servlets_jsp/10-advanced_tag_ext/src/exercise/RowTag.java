package exercise;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import java.io.*;
import java.sql.*;
/**
 *
 * @author  rank
 * @version
 */
public class RowTag extends TagSupport {
    
    /** Holds value of property colName. */
    private String colName;
    
    public int doStartTag() throws JspException {
        try{
            TableTag table = (TableTag) getParent();
            if (table==null) {
                throw new JspException("No parent table tag.");
            }
            ResultSet rs = table.rs;
            pageContext.getOut().print(rs.getString(colName));
        } catch (Exception e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    /** Getter for property colName.
     * @return Value of property colName.
     */
    public String getColName() {
        return colName;
    }
    
    /** Setter for property colName.
     * @param colName New value of property colName.
     */
    public void setColName(String colName) {
        this.colName = colName;
    }
    
}
