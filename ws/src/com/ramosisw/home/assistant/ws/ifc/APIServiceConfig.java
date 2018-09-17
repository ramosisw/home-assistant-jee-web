package com.ramosisw.home.assistant.ws.ifc;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author jcramos
 *
 */
@Path("/config")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public interface APIServiceConfig {
	
	/**
	 * Validate is service was configurated
	 * @return
	 */
	@GET
	@Path("/setup")
	public boolean setup();
}
