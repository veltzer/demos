package swing.java_beans;

public class BeanDemo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return (username);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return (password);
	}
}