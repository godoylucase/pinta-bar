package com.pintabar.webservices.apis;

import com.pintabar.exceptions.general.DataNotFoundException;
import com.pintabar.dto.UserDTO;
import com.pintabar.services.UserService;
import com.pintabar.services.UserServiceImpl;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.Optional;

import static com.pintabar.exceptions.ErrorCode.USER_ALREADY_EXISTS;
import static com.pintabar.exceptions.ErrorCode.USER_NOT_FOUND;

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
	public Response getUser(@PathParam("uuid") String uuid) throws DataNotFoundException {
		Optional<UserDTO> user = userService.getUser(uuid);
		return Response.ok(user.orElseThrow(() -> new DataNotFoundException(USER_NOT_FOUND)))
				.build();
	}

	@GET
	public Response getUsers(@DefaultValue("false") @QueryParam("deleted") boolean isDeleted) {
		return Response.ok(userService.getUsers(isDeleted)).build();
	}

	@POST
	public Response createUser(UserDTO userDTO, @Context UriInfo uriInfo) throws DataNotFoundException {
		UserDTO user = userService.createUser(userDTO).orElseThrow(() -> new DataNotFoundException(USER_ALREADY_EXISTS));
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		uriBuilder.path(user.getUuid());
		return Response.created(uriBuilder.build()).build();
	}

	@DELETE
	@Path("/{uuid}")
	public Response deleteUser(@PathParam("uuid") String uuid) throws DataNotFoundException {
		userService.deleteUser(uuid).orElseThrow(() -> new DataNotFoundException(USER_NOT_FOUND));
		return Response.accepted().build();
	}
}
