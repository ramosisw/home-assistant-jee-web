package com.ramosisw.home.assistant.bl;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.ramosisw.home.assistant.api.ifc.HomeAssistantLocal;
import com.ramosisw.jee.web.core.api.ex.BLException;
import com.ramosisw.jee.web.core.api.to.BasicType;

/**
 * 
 * @author jcramos
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class HomeAssistantBean implements HomeAssistantLocal{

	@Override
	public BasicType test() throws BLException {
		return new BasicType(200, "Ok");
	}
	
}
