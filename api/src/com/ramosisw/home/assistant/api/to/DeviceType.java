package com.ramosisw.home.assistant.api.to;

import com.ramosisw.home.assistant.api.enm.DeviceAction;

public class DeviceType {

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeviceType [name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", port=");
		builder.append(port);
		builder.append("]");
		return builder.toString();
	}

	
}
