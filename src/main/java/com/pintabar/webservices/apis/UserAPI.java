package com.pintabar.webservices.apis;

import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.services.UserService;
import com.pintabar.services.UserServiceImpl;
import org.springframework.stereotype.Component;

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
	public Response getUser(@PathParam("uuid") String uuid) {
		Optional user = userService.getUser(uuid);
		if (user.isPresent()) {
			return Response.ok(user.get()).build();
		} else {
			return Response.noContent().build();
		}
	}

	@GET
	public List getUsers(@QueryParam("deleted") Boolean isDeleted) {
		return userService.getUsers(isDeleted);
	}

	@POST
	public Response createUser(UserDTO userDTO, @Context UriInfo uriInfo) {
		Optional<UserDTO> user = userService.createUser(userDTO);
		if (user.isPresent()) {
			UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
			uriBuilder.path(user.get().getUuid());
			return Response.created(uriBuilder.build()).build();
		}
		return Response.status(Response.Status.CONFLICT).build();
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
