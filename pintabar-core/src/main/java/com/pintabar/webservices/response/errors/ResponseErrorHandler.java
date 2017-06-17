package com.pintabar.webservices.response.errors;

import javax.ws.rs.core.Response;

/**
 * Created by lucasgodoy on 10/06/17.
 */
@FunctionalInterface
public interface ResponseErrorHandler {
	Response getResponse(Response.Status status, ErrorCode errorCode, Object... params);
}
