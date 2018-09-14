package com.ramosisw.home.assistant.api.to;

/**
 * 
 * @author jcramos
 *
 */
public class InvocationDeviceType {
	String name;
	Object value;

	/**
	 * Default constructor
	 */
	public InvocationDeviceType() {
	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public InvocationDeviceType(String name, Object value) {
		this.name = name;
		this.value = value;
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
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

}
