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
	USER_ALREADY_EXISTS("userAPI.user.already.exists");

	private final String message;

	ErrorCode(String messageCode) {
		this.message = messageCode;
	}
}
