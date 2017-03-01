package com.pintabar.rest.services;

import com.pintabar.rest.wsobjects.UserWS;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by lucasgodoy on 19/03/17.
 */
@Component
@Path("/user")
public class UserAPI {
	@GET
	@Produces("application/json")
	public UserWS getUser() {
		UserWS userWS = new UserWS();
		userWS.setMsg("testing ws");
		return userWS;
	}
}
