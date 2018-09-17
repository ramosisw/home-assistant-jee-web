package com.ramosisw.home.assistant.ws.rest;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

import com.ramosisw.home.assistant.ws.ifc.APIAuthService;
import com.ramosisw.home.assistant.ws.msgs.AuthMessage;
import com.ramosisw.home.assistant.ws.msgs.LoginMessage;
import com.ramosisw.jee.web.core.api.util.KeyGenerator;
import com.ramosisw.jee.web.core.ws.rest.BaseAuthenticationEndpoint;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class APIAuthServiceImpl extends BaseAuthenticationEndpoint<LoginMessage> implements APIAuthService {

	@Context
	private UriInfo uriInfo;

	private static final Logger log = Logger.getLogger(APIAuthServiceImpl.class.getName());

	public APIAuthServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ramosisw.jee.web.core.ws.rest.BaseAuthenticationEndpoint#authenticate()
	 */
	@Override
	public Response authenticate() {
		log.info("Entry desAuthenticateUser");
		try {
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			log.error(e);
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ramosisw.jee.web.core.ws.rest.BaseAuthenticationEndpoint#authenticate(
	 * java.lang.Object)
	 */
	@Override
	public Response authenticate(LoginMessage login) {
		log.info("Entry authenticateUser");
		try {
			// Authenticate the user using the credentials provided
			validAuthentication(login);

			// Issue a token for the user
			String token = issueToken(login);

			// Return the token on the response
			return Response.ok(new AuthMessage(token))
					.header(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token)).build();

		} catch (Exception e) {
			log.error(e);
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ramosisw.jee.web.core.ws.rest.BaseAuthenticationEndpoint#issueToken(java.
	 * lang.Object)
	 */
	@Override
	public String issueToken(LoginMessage loginMessage) {
		String username = loginMessage.getName();
		String jwtToken = Jwts.builder().setId(username).setSubject(username)
				//.setIssuer(uriInfo.getAbsolutePath().toString()).setIssuedAt(new Date())
				//.setExpiration(DateUtils.toDate(LocalDateTime.now().plusMinutes(expirationMinutes)))
				.signWith(SignatureAlgorithm.HS512, KeyGenerator.DEFAULT_KEY)//.claim("uuid", UUID.randomUUID())
				.compact();
		return jwtToken;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ramosisw.jee.web.core.ws.rest.BaseAuthenticationEndpoint#
	 * validAuthentication(java.lang.Object)
	 */
	@Override
	public void validAuthentication(LoginMessage loginMessage) throws Exception {
		String username = loginMessage.getName();
		String password = loginMessage.getPassword();
		if (username == null || password == null || username.isEmpty() || password.isEmpty())
			new Exception("Empty request");

		if (!username.equals(password)) {
			throw new NotAllowedException("Not allowed");
		}

	}

}
