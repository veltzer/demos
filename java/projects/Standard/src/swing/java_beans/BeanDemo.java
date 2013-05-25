package swing.java_beans;

@SuppressWarnings("serial")
public class BeanDemo implements java.io.Serializable {
	private String username;
	private String password;

	public void setUsername(String iusername) {
		username = iusername;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String ipassword) {
		password = ipassword;
	}

	public String getPassword() {
		return password;
	}
}
