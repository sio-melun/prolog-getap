package org.ldv.sio.getap.app;

public class LoginInfo {
	private String lastlog;
	private String countlog;

	public LoginInfo() {
	}

	public LoginInfo(String lastlog, String countap) {
		super();
		this.lastlog = lastlog;
		this.countlog = countap;
	}

	public void setLastlog(String lastlog) {
		this.lastlog = lastlog;
	}

	public void setCountlog(String countlog) {
		this.countlog = countlog;
	}

	public String getLastlog() {
		return lastlog;
	}

	public String getCountlog() {
		return countlog;
	}

}
