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
public class DbConnectionTag extends TagSupport {
    
    /** Holds value of property driver. */
    private String driver;
    
    /** Holds value of property url. */
    private String url;
    
    /** Holds value of property user. */
    private String user=null;
    
    /** Holds value of property password. */
    private String password=null;
    
    
    public int doStartTag() throws JspException {
        try{
            Class.forName(driver);
        
            Connection con = DriverManager.getConnection(url, user, password);
            pageContext.setAttribute("connection",con);
        } catch (Exception e) {
            pageContext.removeAttribute("connection");
            throw new JspException(e);
        }
        return SKIP_BODY;
        
    }

    /** Getter for property driver.
     * @return Value of property driver.
     */
    public String getDriver() {
        return driver;
    }
    
    /** Setter for property driver.
     * @param driver New value of property driver.
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }
    
    /** Getter for property url.
     * @return Value of property url.
     */
    public String getUrl() {
        return url;
    }
    
    /** Setter for property url.
     * @param url New value of property url.
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /** Getter for property user.
     * @return Value of property user.
     */
    public String getUser() {
        return user;
    }
    
    /** Setter for property user.
     * @param user New value of property user.
     */
    public void setUser(String user) {
        this.user = user;
    }
    
    /** Getter for property password.
     * @return Value of property password.
     */
    public String getPassword() {
        return password;
    }
    
    /** Setter for property password.
     * @param password New value of property password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
