package com.ramosisw.home.assistant.ws.msgs;

/**
 * 
 * @author jcramos
 *
 */
public class AuthMessage {
	private String token;

	public AuthMessage(String token) {
		super();
		this.token = token;
	}

	public AuthMessage() {
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
}