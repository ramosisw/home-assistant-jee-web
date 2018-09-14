package com.ramosisw.home.assistant.api.to;

import com.ramosisw.home.assistant.api.enm.MessageAction;

/**
 * 
 * @author jcramos
 *
 */
public class MessageType<Payload> {
	MessageAction action;
	Payload payload;

	/**
	 * Default constructor
	 */
	public MessageType() {

	}

	/**
	 * 
	 * @param action
	 * @param data
	 */
	public MessageType(MessageAction action, Payload payload) {
		this.action = action;
		this.payload = payload;
	}

	/**
	 * @return the action
	 */
	public MessageAction getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(MessageAction action) {
		this.action = action;
	}

	/**
	 * @return the data
	 */
	public Payload getPayload() {
		return payload;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActionType [action=");
		builder.append(action);
		builder.append(", payload=");
		builder.append(payload);
		builder.append("]");
		return builder.toString();
	}

}
