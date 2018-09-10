package com.ramosisw.home.assistant.bl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.jboss.logging.Logger;

import com.ramosisw.home.assistant.api.ifc.EndpointLocal;
import com.ramosisw.home.assistant.api.ifc.HomeAssistantLocal;
import com.ramosisw.jee.web.core.api.ex.BLException;
import com.ramosisw.jee.web.core.api.to.BasicType;
import com.ramosisw.jee.web.core.api.util.Bean;
import com.ramosisw.jee.web.core.api.util.StringUtils;

/**
 * 
 * @author jcramos
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class HomeAssistantBean implements HomeAssistantLocal {
	private final static Logger log = Logger.getLogger(HomeAssistantBean.class.getName());
	
	public HomeAssistantBean() {
		log.info("HomeAssistantBean initialized!");
	}

	@EJB
	EndpointLocal bean;

	EndpointLocal getBean() throws BLException {
		return Bean.getBean(bean, EndpointLocal.class);
	}

	@Override
	public BasicType test() throws BLException {
		BasicType bt = new BasicType(200, "Ok");
		try {
			bt.setMessage(String.format("Clients %d", getBean().getNumOfClients()));
		} catch (Exception ex) {
			log.error(ex);
		}
		return bt;
	}

	@Override
	public BasicType action(int id) throws BLException {
		BasicType sync = new BasicType();
		sync.setMessage(StringUtils.getUUID());
		getBean().action(id, sync);
		synchronized (sync) {
			try {
				sync.wait();
			} catch (InterruptedException e) {
				log.error(e);
			}
		}
		return sync;
	}

}
