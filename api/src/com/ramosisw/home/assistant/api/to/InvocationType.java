package com.ramosisw.home.assistant.api.to;

/**
 * 
 * @author jcramos
 *
 */
public class InvocationType {
	String uuid;
	boolean invocated;
	String message;
	InvocationDeviceType device;

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the invocated
	 */
	public boolean isInvocated() {
		return invocated;
	}

	/**
	 * @param invocated
	 *            the invocated to set
	 */
	public void setInvocated(boolean invocated) {
		this.invocated = invocated;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the device
	 */
	public InvocationDeviceType getDevice() {
		return device;
	}

	/**
	 * @param device
	 *            the device to set
	 */
	public void setDevice(InvocationDeviceType device) {
		this.device = device;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InvocationType [uuid=");
		builder.append(uuid);
		builder.append(", invocated=");
		builder.append(invocated);
		builder.append(", message=");
		builder.append(message);
		builder.append(", device=");
		builder.append(device);
		builder.append("]");
		return builder.toString();
	}

}
