package com.pintabar.webservices.apis;

import com.pintabar.dto.PurchaseOrderDTO;
import com.pintabar.exceptions.AppException;
import com.pintabar.exceptions.ErrorCode;
import com.pintabar.services.BusinessService;
import com.pintabar.ws.OrderingWS;
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
@Path("/ordering")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class OrderingAPI {

	private final BusinessService businessService;

	public OrderingAPI(BusinessService businessService) {
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
	@Path("/purchaseOrder/{purchaseOrderUuid}/addMenuItemInstances")
	public Response addMenuItemInstancesToPurchaseOrder(
			@PathParam("purchaseOrderUuid") String purchaseOrderUuid,
			OrderingWS orderingWS) throws AppException {
		PurchaseOrderDTO purchaseOrderDTO =
				businessService.addItemsToPurchaseOrder(purchaseOrderUuid, orderingWS);
		return Response.status(Response.Status.OK)
				.entity(purchaseOrderDTO)
				.build();
	}

	@GET
	@Path("/business/{businessUuid}/menuInstance")
	public Response getMenuInstances(
			@PathParam("businessUuid") String businessUuid,
			@DefaultValue("false") @QueryParam("isDeleted") boolean isDeleted) {
		return Response.ok(businessService.getMenuInstances(businessUuid, isDeleted)).build();
	}
}
