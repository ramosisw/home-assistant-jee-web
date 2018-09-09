package com.ramosisw.home.assistant.ws.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import com.ramosisw.home.assistant.api.ifc.HomeAssistantLocal;
import com.ramosisw.home.assistant.ws.ifc.APIService;
import com.ramosisw.jee.web.core.api.ex.BLException;
import com.ramosisw.jee.web.core.api.util.Bean;
import com.ramosisw.jee.web.core.ws.rest.messages.BasicMessage;
import com.ramosisw.jee.web.core.ws.to.BasicTO;

/**
 * 
 * @author jcramos
 *
 */
@Stateless
public class APIServiceImpl implements APIService {
	private final static Logger log = Logger.getLogger(APIServiceImpl.class.getName());
	
	@EJB
	HomeAssistantLocal bean;
	
	/**
	 * 
	 * @return
	 * @throws BLException
	 */
	public HomeAssistantLocal getBean() throws BLException{
		return Bean.getBean(bean, HomeAssistantLocal.class);
	}
	
	@Override
	public BasicMessage index() throws Exception {
		try {
			return BasicTO.getTO(getBean().test());
		} catch (BLException e) {
			log.error(e);
			throw e;
		}
	}

}
