package com.pintabar.webservices.apis.impl;

import com.pintabar.dto.PurchaseOrderDTO;
import com.pintabar.exceptions.AppException;
import com.pintabar.exceptions.ErrorCode;
import com.pintabar.services.BusinessService;
import com.pintabar.webservices.apis.OrderingAPI;
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
public class OrderingAPIImpl implements OrderingAPI {

	private final BusinessService businessService;

	public OrderingAPIImpl(BusinessService businessService) {
		this.businessService = businessService;
	}

	public Response userCheckin(String userUuid, String tableUnitUuid, UriInfo uriInfo) throws AppException {
		PurchaseOrderDTO purchaseOrder = businessService.checkInUserToTable(userUuid, tableUnitUuid)
				.orElseThrow(() -> new AppException(ErrorCode.INTERNAL_ERROR));
		return Response.status(Response.Status.CREATED)
				.entity(purchaseOrder)
				.build();
	}

	public Response addMenuItemInstancesToPurchaseOrder(String purchaseOrderUuid, OrderingWS orderingWS)
			throws AppException {
		PurchaseOrderDTO purchaseOrderDTO =	businessService.addItemsToPurchaseOrder(purchaseOrderUuid, orderingWS);
		return Response.status(Response.Status.OK)
				.entity(purchaseOrderDTO)
				.build();
	}

	public Response getMenuInstances(String businessUuid, boolean isDeleted) {
		return Response.ok(businessService.getMenuInstances(businessUuid, isDeleted)).build();
	}
}
