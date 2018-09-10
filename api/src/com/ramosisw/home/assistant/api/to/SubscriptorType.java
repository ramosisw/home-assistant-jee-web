package com.ramosisw.home.assistant.api.to;

import java.util.ArrayList;
import java.util.List;

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
	List<BasicType> waitForResponse;

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

	public List<BasicType> getWaitForResponse() {
		if(waitForResponse == null)
			waitForResponse = new ArrayList<>();
		return waitForResponse;
	}
	
	
}
