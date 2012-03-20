package org.ldv.sio.getap.app;

/**
 * Form backing bean to authenticate user
 */
public class UserLoginCriteria {

	/**
	 * The login search query
	 */
	private String login;

	/**
	 * The password search query
	 */
	private String password;

	/**
	 * Construct an empty criteria
	 */
	public UserLoginCriteria() {
		this("", "");
	}

	/**
	 * Construct criteria with the provided query
	 * 
	 * @param query
	 */
	public UserLoginCriteria(String login, String pw) {
		this.login = login;
		this.password = pw;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}