package com.pintabar.webservices.response.errors;

import lombok.Getter;

import java.io.Serializable;

/**
 * Created by lucasgodoy on 10/06/17.
 */
@Getter
public enum ErrorCode implements Serializable {
	USERS_NOT_FOUND("userAPI.users.not.found"),
	USER_NOT_FOUND("userAPI.user.not.found"),
	USER_ALREADY_EXISTS("userAPI.user.already.exists"),
	USER_ALREADY_HAS_OPENED_ORDERS("businessAPI.user.already.has.opened.orders"),
	TABLE_UNIT_NOT_FOUND("businessAPI.table.not.found"),
	PURCHASE_ORDER_NOT_CREATED("businessAPI.purchase.order.not.created"),
	PURCHASE_ORDER_INVALID_OWNER("businessAPI.purchase.order.invalid.owner"),
	PURCHASE_ORDER_ALREADY_CLOSED("businessAPI.purchase.order.closed.status"),
	MENUES_NOT_FOUND("businessAPI.menues.not.found"),
	INTERNAL_ERROR("API.internal.error");

	private final String message;

	ErrorCode(String messageCode) {
		this.message = messageCode;
	}
}
