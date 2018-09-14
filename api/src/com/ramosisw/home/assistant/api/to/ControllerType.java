package com.ramosisw.home.assistant.api.to;

import java.util.List;

/**
 * 
 * @author jcramos
 *
 */
public class ControllerType {

	private String id;
	private String location_id;
	private String description;
	private String ip;
	private List<DeviceType> devices;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the location_id
	 */
	public String getLocation_id() {
		return location_id;
	}

	/**
	 * @param location_id
	 *            the location_id to set
	 */
	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the devices
	 */
	public List<DeviceType> getDevices() {
		return devices;
	}

	/**
	 * @param devices
	 *            the devices to set
	 */
	public void setDevices(List<DeviceType> devices) {
		this.devices = devices;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ControllerType [id=");
		builder.append(id);
		builder.append(", location_id=");
		builder.append(location_id);
		builder.append(", description=");
		builder.append(description);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", devices=");
		builder.append(devices);
		builder.append("]");
		return builder.toString();
	}

}
