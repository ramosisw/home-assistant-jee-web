package com.ramosisw.home.assistant.ws.msgs;

public class LoginMessage {
	private String name;
	private String password;

	public LoginMessage(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public LoginMessage() {
		super();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
