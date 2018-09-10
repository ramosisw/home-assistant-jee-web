package com.ramosisw.home.assistant.bl;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.websocket.Session;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramosisw.home.assistant.api.ifc.EndpointLocal;
import com.ramosisw.home.assistant.api.to.SubscriptorType;
import com.ramosisw.jee.web.core.api.ex.BLException;
import com.ramosisw.jee.web.core.api.to.BasicType;

@Stateless
public class EndpointBean implements EndpointLocal {
	private final static Logger log = Logger.getLogger(EndpointBean.class.getName());
	Set<SubscriptorType> subscribers = Collections.synchronizedSet(new HashSet<SubscriptorType>());
	ObjectMapper mapper = new ObjectMapper();
	Object sync = new Object();

	private int clients = 0;

	public EndpointBean() {
		log.info("EJB initialized!");
	}

	void addClient(int num) throws BLException {
		clients += num;
	}

	@Override
	public int getNumOfClients() {
		return clients;
	}

	public void addSubscriptor(SubscriptorType sub) throws BLException {
		subscribers.add(sub);
	}

	SubscriptorType getSubscriptor(Session client) throws BLException {
		log.info(String.format("Find subscriptor with session [%s] in [%d] clients", client.getId(),
				subscribers.size()));
		for (SubscriptorType subscriptor : subscribers)
			if (subscriptor.getClient().equals(client))
				return subscriptor;
		throw new BLException(2001, String.format("Client with session id [%s] not found", client.getId()));
	}

	SubscriptorType getSubscriptor(int id) throws BLException {
		log.info(String.format("Find subscriptor with id [%d]", id));
		for (SubscriptorType subscriptor : subscribers)
			if (subscriptor.getId() == id)
				return subscriptor;
		throw new BLException(2000, String.format("Client with id [%d] not found", id));
	}

	@Override
	public void onClose(Session client) throws BLException {
		for (SubscriptorType subscriptor : subscribers) {
			if (subscriptor.getClient().equals(client)) {
				if (subscriptor.getBasicType() != null)
					synchronized (subscriptor.getBasicType()) {
						subscriptor.getBasicType().setMessage("Client was disconnected");
						subscriptor.getBasicType().notify();
					}

				subscribers.remove(subscriptor);
				addClient(-1);
				break;
			}
		}
	}

	@Override
	public void onOpen(Session client) throws BLException {
		subscribers.add(new SubscriptorType(client));
		addClient(1);
	}

	@Override
	public void onMessage(String text, Session client) throws BLException {
		BasicType basicMessage = new BasicType(200, "Success");
		try {
			BasicType clientMessage = mapper.readValue(text, BasicType.class);
			SubscriptorType sub = getSubscriptor(client);

			if (sub != null) {
				sub.setId(clientMessage.getCode());
				if (sub.getBasicType() != null)
					synchronized (sub.getBasicType()) {
						sub.getBasicType().setMessage(clientMessage.getMessage());
						sub.getBasicType().notify();
					}
			}
			client.getAsyncRemote().sendText(mapper.writeValueAsString(basicMessage));
		} catch (JsonProcessingException e) {
			client.getAsyncRemote()
					.sendText(String.format("{\"code\": 666, \"message\": \"%s\"}", "Error al construir la respuesta"));
		} catch (IOException e) {
			log.error(e);
			basicMessage.setCode(500);
			basicMessage.setMessage("internal exception, report to admin");
		}
	}

	@Override
	public BasicType action(int id, BasicType sync) throws BLException {
		log.info(String.format("Action to id [%d]", id));
		BasicType bt = new BasicType(200, new Date().toString());
		SubscriptorType client = getSubscriptor(id);
		client.setBasicType(sync);
		try {
			client.getClient().getAsyncRemote().sendText(mapper.writeValueAsString(bt));
		} catch (JsonProcessingException e) {
			log.error(e);
		}
		return bt;
	}

}
