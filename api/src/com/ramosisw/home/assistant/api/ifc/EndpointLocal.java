package com.ramosisw.home.assistant.api.ifc;

import java.util.List;

import javax.ejb.Local;
import javax.websocket.Session;

import com.ramosisw.home.assistant.api.to.ControllerType;
import com.ramosisw.home.assistant.api.to.InvocationType;
import com.ramosisw.home.assistant.api.to.MessageType;
import com.ramosisw.jee.web.core.api.ex.BLException;

/**
 * 
 * @author jcramos
 *
 */
@Local
public interface EndpointLocal {
	public void onClose(Session session) throws BLException;

	public void onOpen(Session session) throws BLException;

	public void onMessage(String text, Session client) throws BLException;

	public int getNumOfClients();

	public void action(String id, MessageType<InvocationType> message) throws BLException;

	public List<ControllerType> getDevices() throws BLException;

}