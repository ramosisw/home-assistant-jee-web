package com.ramosisw.home.assistant.pl.to;

import java.util.ArrayList;
import java.util.List;

import com.ramosisw.home.assistant.api.to.ControllerType;
import com.ramosisw.home.assistant.pl.entity.Controller;

public class ControllerTO {

	/**
	 * 
	 * @param from
	 * @return
	 */
	public static ControllerType getTO(Controller from) {
		ControllerType to = new ControllerType();
		to.setDescription(from.getDescription());
		// to.setDevices(new );
		to.setId(from.getId());
		to.setIp(from.getIp());
		to.setLocation_id(from.getLocation_id());
		return to;
	}

	/**
	 * 
	 * @param items
	 * @return
	 */
	public static List<ControllerType> getTO(List<Controller> items) {
		List<ControllerType> to = new ArrayList<>();
		for (Controller item : items) {
			to.add(getTO(item));
		}
		return to;
	}

}
