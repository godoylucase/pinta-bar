package com.pintabar.webservices.apis.exception;

import com.pintabar.webservices.response.errors.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.core.Response;

/**
 * Created by lucasgodoy on 22/06/17.
 */
@Getter
@Setter
public class AppException extends Exception {
	private Response.Status httpStatus;
	private ErrorCode errorCode;
	private Object[] params;

	public AppException(Response.Status httpStatus, ErrorCode errorCode, Object... params) {
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.params = params;
	}
}
