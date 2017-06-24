package com.pintabar.webservices.apis.exception.mappers;

import com.pintabar.webservices.apis.exception.AppException;
import com.pintabar.webservices.response.errors.ErrorCode;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by lucasgodoy on 22/06/17.
 */
@Provider
@Component
public class AppExceptionMapper implements ExceptionMapper<AppException> {

	private final ExceptionMapperHelper exceptionMapperHelper;

	public AppExceptionMapper(ExceptionMapperHelper exceptionMapperHelper) {
		this.exceptionMapperHelper = exceptionMapperHelper;
	}

	@Override
	public Response toResponse(AppException ex) {
		ErrorCode errorCode = ex.getErrorCode();
		Object[] params = ex.getParams();
		Response.Status httpStatus = errorCode.getHttpStatus();

		return Response.status(httpStatus)
				.entity(exceptionMapperHelper.buildErrorResponse(httpStatus, errorCode, ex, params))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}
