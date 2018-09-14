package com.ramosisw.home.assistant.api.to;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

import com.ramosisw.home.assistant.api.enm.Client;

/**
 * 
 * @author jcramos
 *
 */
public class SubscriptorType {
	private Session client;
	private ControllerType controller;
	private List<InvocationType> invocations;
	private Client type;

	public SubscriptorType() {
		super();
	}

	public SubscriptorType(Session client) {
		super();
		this.client = client;
	}

	/**
	 * @return the client
	 */
	public Session getClient() {
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(Session client) {
		this.client = client;
	}

	/**
	 * @return the invocations
	 */
	public List<InvocationType> getInvocations() {
		if (invocations == null)
			invocations = new ArrayList<>();
		return invocations;
	}

	/**
	 * @return the controller
	 */
	public ControllerType getController() {
		return controller;
	}

	/**
	 * @param controller
	 *            the controller to set
	 */
	public void setController(ControllerType controller) {
		this.controller = controller;
	}

	/**
	 * @return the type
	 */
	public Client getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Client type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SubscriptorType [client=");
		builder.append(client);
		builder.append(", controller=");
		builder.append(controller);
		builder.append(", invocations=");
		builder.append(invocations);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}

}
