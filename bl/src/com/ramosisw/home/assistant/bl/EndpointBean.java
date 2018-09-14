package com.ramosisw.home.assistant.bl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.websocket.Session;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramosisw.home.assistant.api.enm.Client;
import com.ramosisw.home.assistant.api.ifc.EndpointLocal;
import com.ramosisw.home.assistant.api.to.ControllerType;
import com.ramosisw.home.assistant.api.to.InvocationType;
import com.ramosisw.home.assistant.api.to.MessageType;
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
		log.info("EndpointBean initialized!");
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

	SubscriptorType getSubscriptor(String id) throws BLException {
		log.info(String.format("Find subscriptor with id [%s]", id));
		for (SubscriptorType subscriptor : subscribers)
			if (subscriptor.getController().getId().equals(id))
				return subscriptor;
		throw new BLException(2000, String.format("Client with id [%s] not found", id));
	}

	@Override
	public void onClose(Session client) throws BLException {
		for (SubscriptorType sub : subscribers) {
			if (sub.getClient().equals(client)) {
				for (InvocationType it : sub.getInvocations()) {
					synchronized (it) {
						it.setMessage("Client disconnected");
						it.setInvocated(false);
						it.notify();
					}
				}

				subscribers.remove(sub);
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
	public void onMessage(String text, Session session) throws BLException {
		BasicType basicMessage = new BasicType(200, "Success");
		try {
			SubscriptorType client = getSubscriptor(session);
			if (client == null)
				return;

			MessageType<JsonNode> action = mapper.readValue(text, new TypeReference<MessageType<JsonNode>>() {
			});
			log.info(action);
			switch (action.getAction()) {
			case SUBSCRIBE:
				ControllerType controller = mapper.readValue(action.getPayload().toString(), ControllerType.class);
				client.setController(controller);
				log.info(client);
				break;
			case INVOCATION:
				InvocationType client_invocation = mapper.readValue(action.getPayload().toString(),
						InvocationType.class);
				log.info(client_invocation);
				for (InvocationType invocation : client.getInvocations()) {
					if (client_invocation.getUuid().equals(invocation.getUuid())) {
						synchronized (invocation) {
							invocation.setMessage(client_invocation.getMessage());
							invocation.setInvocated(client_invocation.isInvocated());
							invocation.notify();
						}
					}
				}
				break;
			case MESSAGE:
				log.info(action.getPayload().toString());
				break;
			case NOTIFICATION:
				log.info(action.getPayload().toString());
				break;
			default:
				basicMessage.setMessage("Message not identify");
				break;
			}
			session.getAsyncRemote().sendText(mapper.writeValueAsString(basicMessage));
		} catch (JsonProcessingException e) {
			log.error(e);
			session.getAsyncRemote().sendText(String.format("{\"code\": 666, \"message\": \"%s\"}", e.getMessage()));
		} catch (IOException e) {
			log.error(e);
			basicMessage.setCode(500);
			basicMessage.setMessage("internal exception, report to admin");
		}
	}

	/**
	 * 
	 */
	@Override
	public void action(String id, MessageType<InvocationType> message) throws BLException {
		log.info(String.format("Action to id [%s]", id));
		SubscriptorType client = getSubscriptor(id);
		client.getInvocations().add(message.getPayload());

		try {
			log.info("Sending message: " + message);
			client.getClient().getAsyncRemote().sendText(mapper.writeValueAsString(message));
		} catch (JsonProcessingException e) {
			log.error(e);
		}
	}

	/**
	 * 
	 */
	@Override
	public List<ControllerType> getDevices() throws BLException {
		log.info("getDevices()");
		List<ControllerType> devices = new ArrayList<>();
		for (SubscriptorType subscriptor : this.subscribers) {
			if (subscriptor.getType() == Client.DEVICE) {
				devices.add(subscriptor.getController());
			}
		}
		log.info(String.format("Devies found [%d]", devices.size()));
		return devices;
	}

}
