package com.ramosisw.home.assistant.ws.ifc;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ramosisw.home.assistant.ws.msgs.DevicesMessage;
import com.ramosisw.jee.web.core.ws.rest.messages.BasicMessage;

/**
 * 
 * @author jcramos
 *
 */
@Path("/")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public interface APIService {

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("")
	public BasicMessage index() throws Exception;

	@GET
	@Path("/devices")
	public DevicesMessage devices() throws Exception;

	@GET
	@Path("/devices/add")
	public BasicMessage devices_add() throws Exception;

	@GET
	@Path("/devices/all")
	public DevicesMessage devices_all() throws Exception;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/{id}")
	public BasicMessage index(@PathParam("id") String id) throws Exception;
}
