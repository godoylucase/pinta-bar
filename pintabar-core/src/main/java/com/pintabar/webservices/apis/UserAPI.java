package com.pintabar.webservices.apis;

import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.services.UserService;
import com.pintabar.services.UserServiceImpl;
import com.pintabar.webservices.response.errors.ErrorCode;
import com.pintabar.webservices.response.errors.ResponseErrorHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
	private final ResponseErrorHandler responseErrorHandler;

	public UserAPI(UserServiceImpl userService, ResponseErrorHandler responseErrorHandler) {
		this.userService = userService;
		this.responseErrorHandler = responseErrorHandler;
	}

	@GET
	@Path("/{uuid}")
	public Response getUser(@PathParam("uuid") String uuid) {
		Optional user = userService.getUser(uuid);
		if (user.isPresent()) {
			return Response.ok(user.get())
					.build();
		} else {
			return responseErrorHandler.getResponse(Response.Status.NOT_FOUND, ErrorCode.USER_NOT_FOUND);
		}
	}

	@GET
	public Response getUsers(@QueryParam("deleted") String isDeleted) {
		if (StringUtils.isEmpty(isDeleted)) {
			List<UserDTO> users = userService.getUsers(null);
			if (!users.isEmpty()) {
				return Response.ok(users).build();
			}
			return responseErrorHandler.getResponse(Response.Status.NOT_FOUND, ErrorCode.USERS_NOT_FOUND);
		}
		return Response.ok(userService.getUsers(Boolean.valueOf(isDeleted))).build();
	}

	@POST
	public Response createUser(UserDTO userDTO, @Context UriInfo uriInfo) {
		Optional<UserDTO> user = userService.createUser(userDTO);
		if (user.isPresent()) {
			UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
			uriBuilder.path(user.get().getUuid());
			return Response.created(uriBuilder.build()).build();
		}
		return responseErrorHandler.getResponse(Response.Status.CONFLICT, ErrorCode.USER_ALREADY_EXISTS);
	}

	@DELETE
	@Path("/{uuid}")
	public Response deleteUser(@PathParam("uuid") String uuid) {
		Optional deletedUser = userService.deleteUser(uuid);
		if (deletedUser.isPresent()) {
			return Response.accepted().build();
		} else {
			return responseErrorHandler.getResponse(Response.Status.NOT_FOUND, ErrorCode.USER_NOT_FOUND);
		}
	}
}
