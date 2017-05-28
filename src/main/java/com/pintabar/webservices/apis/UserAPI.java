package com.pintabar.webservices.apis;

import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.services.UserService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by lucasgodoy on 19/03/17.
 */
@Component
@Path("/user")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UserAPI {

	@Inject
	private UserService userService;

	@GET
	@Path("{uuid}")
	public UserDTO getUser(@PathParam("uuid") String uuid) {
		UserDTO userDTO = userService.getUser(uuid);
		return userDTO;
	}
}
