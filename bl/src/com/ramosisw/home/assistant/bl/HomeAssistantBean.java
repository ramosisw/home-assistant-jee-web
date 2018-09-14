package com.ramosisw.home.assistant.bl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.jboss.logging.Logger;

import com.ramosisw.home.assistant.api.enm.MessageAction;
import com.ramosisw.home.assistant.api.ifc.EndpointLocal;
import com.ramosisw.home.assistant.api.ifc.HomeAssistantLocal;
import com.ramosisw.home.assistant.api.to.ControllerType;
import com.ramosisw.home.assistant.api.to.InvocationDeviceType;
import com.ramosisw.home.assistant.api.to.InvocationType;
import com.ramosisw.home.assistant.api.to.MessageType;
import com.ramosisw.home.assistant.pl.dao.ControllerDAO;
import com.ramosisw.home.assistant.pl.entity.Controller;
import com.ramosisw.jee.web.core.api.BaseErrorCodes;
import com.ramosisw.jee.web.core.api.ex.BLException;
import com.ramosisw.jee.web.core.api.ex.DLException;
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

	@EJB
	ControllerDAO controllerDAO;

	ControllerDAO getControllerDAO() throws BLException {
		return Bean.getBean(controllerDAO, ControllerDAO.class);
	}

	EndpointLocal getBean() throws BLException {
		return Bean.getBean(bean, EndpointLocal.class);
	}

	/**
	 * 
	 */
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

	/**
	 * 
	 */
	@Override
	public BasicType action(String id) throws BLException {
		InvocationType invocation = new InvocationType();
		invocation.setInvocated(true);
		invocation.setUuid(StringUtils.getUUID());
		invocation.setDevice(new InvocationDeviceType("light", "on"));

		MessageType<InvocationType> message = new MessageType<>();
		message.setAction(MessageAction.INVOCATION);
		message.setPayload(invocation);
		getBean().action(id, message);
		synchronized (invocation) {
			try {
				invocation.wait(5000);
			} catch (InterruptedException e) {
				log.error(e);
			}
		}
		BasicType bt = new BasicType(200,
				StringUtils.isEmpty(invocation.getMessage()) ? "ok" : invocation.getMessage());
		if (!invocation.isInvocated()) {
			bt.setCode(500);
			if (StringUtils.isEmpty(invocation.getMessage())) {
				bt.setMessage("Fail");
			}
		}
		return bt;
	}

	/**
	 * 
	 */
	@Override
	public List<ControllerType> devices() throws BLException {
		log.info("devices()");
		return getBean().getDevices();
	}

	@Override
	public BasicType devices_add() throws BLException {
		try {
			Controller controller = new Controller();
			controller.setDescription("Description");
			controller.setId("asdasd");
			controller.setIp("192.168.0.1");
			controller.setLocation_id("Room1");
			getControllerDAO().add(controller);
		} catch (DLException e) {
			log.error(e);
		}
		return new BasicType(200, "ok");
	}

	@Override
	public List<ControllerType> devices_all() throws BLException {
		try {
			return getControllerDAO().getControllers();
		} catch (DLException e) {
			log.error(e);
			throw new BLException(BaseErrorCodes.WS_BASE_CODE, "Error al cargar la lista");
		}
	}

}
