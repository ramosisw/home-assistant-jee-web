package com.ramosisw.home.assistant.ws.msgs;

import java.util.List;

/**
 * 
 * @author jcramos
 *
 */
public class DevicesMessage {
	List<DeviceInfoMessage> devices;

	/**
	 * @return the devices
	 */
	public List<DeviceInfoMessage> getDevices() {
		return devices;
	}

	/**
	 * @param devices
	 *            the devices to set
	 */
	public void setDevices(List<DeviceInfoMessage> devices) {
		this.devices = devices;
	}

}
