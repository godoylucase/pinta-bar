package com.pintabar.webservices.apis;

import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.services.UserService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * Created by lucasgodoy on 19/03/17.
 */
@Component
@Path("/user")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class UserAPI {

	@Inject
	private UserService userService;

	@GET
	@Path("/{uuid}")
	public UserDTO getUser(@PathParam("uuid") String uuid) {
		return userService.getUser(uuid);
	}

	@GET
	public List<UserDTO> getUsers() {
		return userService.getUsers();
	}

	@POST
	public Response createUser(UserDTO userDTO, @Context UriInfo uriInfo) {
		String uuid = userService.createUser(userDTO);
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		uriBuilder.path(uuid);
		return Response.created(uriBuilder.build()).build();
	}
}
