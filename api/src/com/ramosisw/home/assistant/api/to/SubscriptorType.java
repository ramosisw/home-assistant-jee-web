package com.ramosisw.home.assistant.api.to;

import javax.websocket.Session;

import com.ramosisw.jee.web.core.api.to.BasicType;

/**
 * 
 * @author jcramos
 *
 */
public class SubscriptorType {
	Session client;
	int id;
	BasicType basicMessage;

	public SubscriptorType() {
		super();
	}

	public SubscriptorType(Session client) {
		super();
		this.client = client;
	}

	public Session getClient() {
		return client;
	}

	public void setClient(Session client) {
		this.client = client;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BasicType getBasicType() {
		return basicMessage;
	}

	public void setBasicType(BasicType basicMessage) {
		this.basicMessage = basicMessage;
	}
	
	
}
