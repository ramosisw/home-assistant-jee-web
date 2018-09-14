package com.ramosisw.home.assistant.api.ifc;

import java.util.List;

import javax.ejb.Local;

import com.ramosisw.home.assistant.api.to.ControllerType;
import com.ramosisw.jee.web.core.api.ex.BLException;
import com.ramosisw.jee.web.core.api.to.BasicType;

/**
 * 
 * @author jcramos
 *
 */
@Local
public interface HomeAssistantLocal {
	public BasicType test() throws BLException;

	public BasicType action(String id) throws BLException;

	public List<ControllerType> devices() throws BLException;

	public BasicType devices_add() throws BLException;

	public List<ControllerType> devices_all() throws BLException;
}
