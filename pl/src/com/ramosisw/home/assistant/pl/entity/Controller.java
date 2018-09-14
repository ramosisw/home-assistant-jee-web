package com.ramosisw.home.assistant.pl.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Controller
 *
 */
@Entity

public class Controller implements Serializable {

	   
	@Id
	private String id;
	private String location_id;
	private String description;
	private String ip;
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}   
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}   
	public String getLocation_id() {
		return this.location_id;
	}

	public void setLocation_id(String location_id) {
		this.location_id = location_id;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
   
}
