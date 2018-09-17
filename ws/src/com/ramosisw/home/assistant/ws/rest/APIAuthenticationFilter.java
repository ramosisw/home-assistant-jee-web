package com.ramosisw.home.assistant.ws.rest;

import javax.annotation.Priority;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import com.ramosisw.jee.web.core.api.ifc.SecureRequest;
import com.ramosisw.jee.web.core.api.util.KeyGenerator;
import com.ramosisw.jee.web.core.ws.rest.BaseAuthenticationFilter;

import io.jsonwebtoken.Jwts;

@Provider
@SecureRequest
@Priority(javax.ws.rs.Priorities.AUTHENTICATION)
public class APIAuthenticationFilter extends BaseAuthenticationFilter {
	private static final Logger log = Logger.getLogger(APIAuthenticationFilter.class.getName());

	@Override
	protected void validateToken(String token) throws Exception {
		log.info("validateToken()");
		// Validate the token
		Jwts.parser().setSigningKey(KeyGenerator.DEFAULT_KEY).parseClaimsJws(token);
		log.info("#### valid token : " + token);
	}

}
