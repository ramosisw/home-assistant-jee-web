package com.ramosisw.home.assistant.ws.ifc;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ramosisw.home.assistant.ws.msgs.LoginMessage;

@Path("/auth")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public interface APIAuthService {

	@POST
	public Response authenticate(LoginMessage auth);

	@DELETE
	public Response authenticate();
}
