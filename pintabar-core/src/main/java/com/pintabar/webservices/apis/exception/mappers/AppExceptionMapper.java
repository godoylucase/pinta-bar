package com.pintabar.webservices.apis.exception.mappers;

import com.pintabar.webservices.apis.exception.AppException;
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
public class AppExceptionMapper implements ExceptionMapper<AppException> {

	private final ResponseErrorHandler responseErrorHandler;

	public AppExceptionMapper(ResponseErrorHandler responseErrorHandler) {
		this.responseErrorHandler = responseErrorHandler;
	}

	@Override
	public Response toResponse(AppException ex) {
		return responseErrorHandler.createResponse(ex.getHttpStatus(),
				ex.getErrorCode(), ex.getParams());
	}
}
