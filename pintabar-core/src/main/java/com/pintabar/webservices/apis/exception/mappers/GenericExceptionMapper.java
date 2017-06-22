package com.pintabar.webservices.apis.exception.mappers;

import com.pintabar.webservices.response.errors.ErrorCode;
import com.pintabar.webservices.response.errors.ResponseErrorHandler;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by lucasgodoy on 22/06/17.
 */
@Provider
@Component
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	private final ResponseErrorHandler responseErrorHandler;

	public GenericExceptionMapper(ResponseErrorHandler responseErrorHandler) {
		this.responseErrorHandler = responseErrorHandler;
	}

	@Override
	public Response toResponse(Throwable ex) {
		return responseErrorHandler.createResponse(Response.Status.INTERNAL_SERVER_ERROR,
				ErrorCode.INTERNAL_ERROR, ex);
	}
}
