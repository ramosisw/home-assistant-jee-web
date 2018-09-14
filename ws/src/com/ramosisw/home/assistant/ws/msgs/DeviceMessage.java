package com.ramosisw.home.assistant.ws.msgs;

import com.ramosisw.home.assistant.api.enm.DeviceAction;

/**
 * 
 * @author jcramos
 *
 */
public class DeviceMessage {
	String name;
	DeviceAction type;
	int port;

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
	 * @return the type
	 */
	public DeviceAction getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(DeviceAction type) {
		this.type = type;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

}
