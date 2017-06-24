package com.pintabar.webservices.apis;

import com.pintabar.persistence.dto.PurchaseOrderDTO;
import com.pintabar.services.BusinessService;
import com.pintabar.services.TableUnitService;
import com.pintabar.services.UserService;
import com.pintabar.webservices.apis.exception.AppException;
import com.pintabar.webservices.request.OrderingWS;
import com.pintabar.webservices.response.errors.ErrorCode;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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

	public BusinessAPI(UserService userService, TableUnitService tableUnitService,
	                   BusinessService businessService) {
		this.userService = userService;
		this.tableUnitService = tableUnitService;
		this.businessService = businessService;
	}

	@POST
	@Path("/checkin/user/{userUuid}/tableUnit/{tableUnitUuid}/")
	public Response userCheckin(
			@PathParam("userUuid") String userUuid,
			@PathParam("tableUnitUuid") String tableUnitUuid,
			@Context UriInfo uriInfo) throws AppException {
		PurchaseOrderDTO purchaseOrder = businessService.checkInUserToTable(userUuid, tableUnitUuid)
				.orElseThrow(() -> new AppException(ErrorCode.INTERNAL_ERROR));
		return Response.status(Response.Status.CREATED)
				.entity(purchaseOrder)
				.build();
	}

	@PUT
	@Path("/ordering/purchaseOrder/{purchaseOrderUuid}/addItems")
	public Response addItemsToPurchaseOrder(
			@PathParam("purchaseOrderUuid") String purchaseOrderUuid,
			OrderingWS orderingWS) throws AppException {
		PurchaseOrderDTO purchaseOrderDTO =
				businessService.addItemsToPurchaseOrder(purchaseOrderUuid, orderingWS);
		return Response.status(Response.Status.OK)
				.entity(purchaseOrderDTO)
				.build();
	}

	@GET
	@Path("/{businessUuid}/menu")
	public Response getMenus(
			@PathParam("businessUuid") String businessUuid,
			@DefaultValue("false") @QueryParam("isDeleted") boolean isDeleted) {
		return Response.ok(businessService.getMenus(businessUuid, isDeleted)).build();
	}
}
