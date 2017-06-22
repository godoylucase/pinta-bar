package com.pintabar.webservices.apis;

import com.pintabar.exceptions.UserWithOpenedOrderException;
import com.pintabar.persistence.dto.MenuDTO;
import com.pintabar.persistence.dto.PurchasePurchaseOrderDTO;
import com.pintabar.persistence.dto.TableUnitDTO;
import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.services.BusinessService;
import com.pintabar.services.TableUnitService;
import com.pintabar.services.UserService;
import com.pintabar.webservices.apis.exception.AppException;
import com.pintabar.webservices.response.errors.ErrorCode;
import com.pintabar.webservices.response.errors.ResponseErrorHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.Consumes;
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
import java.util.List;
import java.util.Optional;

/**
 * Created by lucasgodoy on 13/06/17.
 */
@Component
@Path("/business")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class BusinessAPI {

	private final UserService userService;
	private final TableUnitService tableUnitService;
	private final BusinessService businessService;
	private final ResponseErrorHandler responseErrorHandler;

	public BusinessAPI(UserService userService, TableUnitService tableUnitService,
	                   BusinessService businessService, ResponseErrorHandler responseErrorHandler) {
		this.userService = userService;
		this.tableUnitService = tableUnitService;
		this.businessService = businessService;
		this.responseErrorHandler = responseErrorHandler;
	}

	@POST
	@Path("/checkin/user/{userUuid}/tableUnit/{tableUnitUuid}/")
	public Response userCheckin(
			@PathParam("userUuid") String userUuid,
			@PathParam("tableUnitUuid") String tableUnitUuid,
			@Context UriInfo uriInfo) throws AppException {
		Optional<UserDTO> userDTO = userService.getUser(userUuid);
		Optional<TableUnitDTO> tableUnitDTO = tableUnitService.getTableUnit(tableUnitUuid);
		if (!userDTO.isPresent()) {
			return responseErrorHandler.createResponse(Response.Status.NOT_FOUND, ErrorCode.USER_NOT_FOUND);
		} else if (!tableUnitDTO.isPresent()) {
			return responseErrorHandler.createResponse(Response.Status.NOT_FOUND, ErrorCode.TABLE_UNIT_NOT_FOUND);
		}

		try {
			Optional<PurchasePurchaseOrderDTO> purchaseOrder = businessService.checkInUserToTable(userDTO.get(),
					tableUnitDTO.get());
			if (purchaseOrder.isPresent()) {
				UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
				uriBuilder.path(purchaseOrder.get().getUuid());
				return Response.status(Response.Status.CREATED)
						.entity(purchaseOrder.get())
						.build();
			}
		} catch (UserWithOpenedOrderException e) {
			throw new AppException(Response.Status.CONFLICT, ErrorCode.USER_ALREADY_HAS_OPENED_ORDERS);
		}
		return responseErrorHandler.createResponse(Response.Status.INTERNAL_SERVER_ERROR, ErrorCode.PURCHASE_ORDER_NOT_CREATED);
	}

	@GET
	@Path("/{businessUuid}/menu")
	public Response getMenues(
			@PathParam("businessUuid") String businessUuid,
			@QueryParam("isDeleted") String isDeleted) {
		if (StringUtils.isEmpty(isDeleted)) {
			List<MenuDTO> menues = businessService.getMenues(businessUuid);
			if (!menues.isEmpty()) {
				return Response.ok(menues).build();
			}
			return responseErrorHandler.createResponse(Response.Status.NOT_FOUND, ErrorCode.MENUES_NOT_FOUND);
		}
		return Response.ok(businessService.getMenues(businessUuid, Boolean.valueOf(isDeleted))).build();
	}
}
