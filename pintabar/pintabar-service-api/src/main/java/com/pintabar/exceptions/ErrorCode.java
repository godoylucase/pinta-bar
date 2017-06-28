package com.pintabar.exceptions;

import lombok.Getter;

import javax.ws.rs.core.Response;
import java.io.Serializable;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * Created by lucasgodoy on 10/06/17.
 */
@Getter
public enum ErrorCode implements Serializable {
	USERS_NOT_FOUND(NOT_FOUND, "userAPI.users.not.found"),
	USER_NOT_FOUND(NOT_FOUND, "userAPI.user.not.found"),
	USER_ALREADY_EXISTS(CONFLICT, "userAPI.user.already.exists"),
	USER_ALREADY_HAS_OPENED_ORDERS(CONFLICT, "businessAPI.user.already.has.opened.orders"),
	TABLE_UNIT_NOT_FOUND(NOT_FOUND, "businessAPI.table.not.found"),
	PURCHASE_ORDER_NOT_FOUND(NOT_FOUND, "businessAPI.purchase.order.not.created"),
	PURCHASE_ORDER_INVALID_OWNER(BAD_REQUEST, "businessAPI.purchase.order.invalid.owner"),
	PURCHASE_ORDER_ALREADY_CLOSED(CONFLICT, "businessAPI.purchase.order.closed.status"),
	MENUS_NOT_FOUND(NOT_FOUND, "businessAPI.menues.not.found"),
	MENU_ITEM_NOT_FOUND(NOT_FOUND, "businessAPI.menu.item.not.found"),
	INTERNAL_ERROR(INTERNAL_SERVER_ERROR, "API.internal.error");

	private final Response.Status httpStatus;
	private final String message;

	ErrorCode(Response.Status httpStatus, String messageCode) {
		this.httpStatus = httpStatus;
		this.message = messageCode;
	}
}
