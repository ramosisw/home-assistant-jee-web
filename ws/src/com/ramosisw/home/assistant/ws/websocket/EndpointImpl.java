package com.ramosisw.home.assistant.ws.websocket;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.jboss.logging.Logger;

import com.ramosisw.home.assistant.api.ifc.EndpointLocal;
import com.ramosisw.jee.web.core.api.ex.BLException;
import com.ramosisw.jee.web.core.api.util.Bean;

@Stateless
@ServerEndpoint("/websocket")
public class EndpointImpl {
	private final static Logger log = Logger.getLogger(EndpointImpl.class.getName());

	@EJB
	EndpointLocal bean;

	// static Set<Session> allSessions;

	EndpointLocal getBean() throws BLException {
		return Bean.getBean(bean, EndpointLocal.class);
	}

	/**
	 * 
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		log.info("on open");
		try {
			getBean().onOpen(session);
		} catch (BLException e) {
			log.error(e);
		}

		/*
		 * allSessions = session.getOpenSessions(); // start the scheduler on the very
		 * first connection if (allSessions.size() == 1) { //
		 * timer.scheduleAtFixedRate(() -> inspectFiles(session), 0, 1, //
		 * TimeUnit.SECONDS); }
		 */
	}

	/**
	 * 
	 * @param session
	 */
	@OnClose
	public void onClose(Session session) {
		log.info("on close");
		try {
			getBean().onClose(session);
		} catch (BLException e) {
			log.error(e);
		}
	}

	/**
	 * 
	 * @param text
	 * @param client
	 */
	@OnMessage
	public void onMessage(String text, Session client) {
		log.info(text);
		
		try {
			getBean().onMessage(text, client);
		} catch (BLException e) {
			log.error(e);
		}

	}
}
