package com.ramosisw.home.assistant.ws.to;

import java.util.ArrayList;
import java.util.List;

import com.ramosisw.home.assistant.api.to.ControllerType;
import com.ramosisw.home.assistant.ws.msgs.DevicesMessage;

public class DevicesTO {

	/**
	 * 
	 * @param items
	 * @return
	 */
	public static DevicesMessage getTO(List<ControllerType> items) {
		DevicesMessage to = new DevicesMessage();
		to.setDevices(new ArrayList<>());
		if (items != null)
			for (ControllerType item : items) {
				to.getDevices().add(DeviceTO.getTO(item));
			}
		return to;
	}
}
