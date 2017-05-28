package com.pintabar.webservices.apis;

import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.services.UserService;
import com.pintabar.services.UserServiceImpl;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Optional;

/**
 * Created by lucasgodoy on 19/03/17.
 */
@Component
@Path("/user")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UserAPI {

	private final UserService userService;

	public UserAPI(UserServiceImpl userService) {
		this.userService = userService;
	}

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

	@DELETE
	@Path("/{uuid}")
	public Response deleteUser(@PathParam("uuid") String uuid) {
		Optional deletedUser = userService.deleteUser(uuid);
		if (deletedUser.isPresent()) {
			return Response.accepted().build();
		} else {
			return Response.notModified().build();
		}
	}
}
