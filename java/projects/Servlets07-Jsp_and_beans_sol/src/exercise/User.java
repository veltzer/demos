package exercise;

import java.io.Serializable;

/**
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class User implements Serializable {

	/** Holds value of property fname. */
	private String fname;

	/** Holds value of property lname. */
	private String lname;

	/** Holds value of property id. */
	private String id;

	/** Holds value of property email. */
	private String email;

	/** Creates new User */
	public User() {
	}

	/**
	 * Getter for property fname.
	 * @return Value of property fname.
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * Setter for property fname.
	 * @param fname New value of property fname.
	 */
	public void setFname(String ifname) {
		fname = ifname;
	}

	/**
	 * Getter for property lname.
	 * @return Value of property lname.
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * Setter for property lname.
	 * @param lname New value of property lname.
	 */
	public void setLname(String ilname) {
		lname = ilname;
	}

	/**
	 * Getter for property id.
	 * @return Value of property id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter for property id.
	 * @param id New value of property id.
	 */
	public void setId(String iid) {
		id = iid;
	}

	/**
	 * Getter for property email.
	 * @return Value of property email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for property email.
	 * @param email New value of property email.
	 */
	public void setEmail(String iemail) {
		email = iemail;
	}

	private static final String ERR_STRING1 = "First name is empty";
	private static final String ERR_STRING2 = "Last name is empty";

	public void validate() {
		if (fname == null || fname.equals("")) {
			throw new RuntimeException(ERR_STRING1);
		}
		if (lname == null || lname.equals("")) {
			throw new RuntimeException(ERR_STRING2);
		}

	}
}
