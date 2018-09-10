package com.ramosisw.home.assistant.api.ifc;

import javax.ejb.Local;
import javax.websocket.Session;

import com.ramosisw.jee.web.core.api.ex.BLException;
import com.ramosisw.jee.web.core.api.to.BasicType;

@Local
public interface EndpointLocal {
	public void onClose(Session session) throws BLException;

	public void onOpen(Session session) throws BLException;

	public void onMessage(String text, Session client) throws BLException;

	public int getNumOfClients();
	
	public BasicType action(int id, BasicType bt) throws BLException;
	
}