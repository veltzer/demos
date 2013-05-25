package exercise;

import java.io.Serializable;

/**
 * 
 * @author rank
 * @version
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
	 * 
	 * @return Value of property fname.
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * Setter for property fname.
	 * 
	 * @param fname
	 *            New value of property fname.
	 */
	public void setFname(String fname) throws Exception {
		this.fname = fname;
	}

	/**
	 * Getter for property lname.
	 * 
	 * @return Value of property lname.
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * Setter for property lname.
	 * 
	 * @param lname
	 *            New value of property lname.
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * Getter for property id.
	 * 
	 * @return Value of property id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter for property id.
	 * 
	 * @param id
	 *            New value of property id.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Getter for property email.
	 * 
	 * @return Value of property email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for property email.
	 * 
	 * @param email
	 *            New value of property email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public void validate() throws Exception {
		// throw new Exception("there is a problem");

		if (fname == null || fname.equals("")) {
			throw new Exception("First name is empty");
		}
		if (lname == null || lname.equals("")) {
			throw new Exception("Last name is empty");
		}

	}
}
