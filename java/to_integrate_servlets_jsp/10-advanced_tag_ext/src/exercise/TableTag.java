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
public class TableTag extends TagSupport {
    
    /** Holds value of property tableName. */
    private String tableName;
    
    ResultSet rs;
    
    public int doStartTag() throws JspException {
        try{
            Connection con = (Connection) pageContext.getAttribute("connection");
            if (con==null) {
                throw new JspException("Connection not found.");
            }
            String sql = "SELECT * from "+tableName;
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                return EVAL_BODY_INCLUDE;
            }
        } catch (SQLException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
    
    public int doAfterBody() throws JspException {
        try {
            if (rs.next()) {
                return EVAL_BODY_AGAIN;
            }
        } catch (SQLException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
        
    }
    
    public int doEndTag() throws JspException {
        rs = null;
        return EVAL_PAGE;
    }
    
    /** Getter for property tableName.
     * @return Value of property tableName.
     */
    public String getTableName() {
        return tableName;
    }
    
    /** Setter for property tableName.
     * @param tableName New value of property tableName.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
}
