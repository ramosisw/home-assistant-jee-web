package com.ramosisw.home.assistant.api.ifc;

import javax.ejb.Local;

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
}
