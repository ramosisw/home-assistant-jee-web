package com.ramosisw.home.assistant.ws.to;

import java.util.ArrayList;
import java.util.List;

import com.ramosisw.home.assistant.api.to.ControllerType;
import com.ramosisw.home.assistant.api.to.DeviceType;
import com.ramosisw.home.assistant.ws.msgs.DeviceInfoMessage;
import com.ramosisw.home.assistant.ws.msgs.DeviceMessage;

/**
 * 
 * @author jcramos
 *
 */
public class DeviceTO {
	/**
	 * 
	 * @param from
	 * @return
	 */
	public static DeviceInfoMessage getTO(ControllerType from) {
		DeviceInfoMessage to = new DeviceInfoMessage();
		if (from != null) {
			to.setDescription(from.getDescription());
			to.setId(from.getId());
			to.setIp(from.getIp());
			to.setLocation_id(from.getLocation_id());
			to.setDevices(getTO(from.getDevices()));
		}
		return to;
	}

	/**
	 * 
	 * @param items
	 * @return
	 */
	private static List<DeviceMessage> getTO(List<DeviceType> items) {
		List<DeviceMessage> to = new ArrayList<>();

		if (items != null)
			for (DeviceType item : items) {
				to.add(getTO(item));
			}
		return to;
	}

	/**
	 * 
	 * @param from
	 * @return
	 */
	public static DeviceMessage getTO(DeviceType from) {
		DeviceMessage to = new DeviceMessage();
		if (from != null) {
			to.setName(from.getName());
			to.setPort(from.getPort());
			to.setType(from.getType());
		}
		return to;
	}
}
