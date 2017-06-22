package com.pintabar.webservices.response.errors;

import javax.ws.rs.core.Response;

/**
 * Created by lucasgodoy on 10/06/17.
 */
public interface ResponseErrorHandler {

	Response createResponse(Response.Status httpStatus, ErrorCode errorCode,
	                        Object... params);

	Response createResponse(Response.Status httpStatus, ErrorCode errorCode,
	                        Throwable ex, Object... params);

}
