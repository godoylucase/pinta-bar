package com.pintabar.webservices.apis.exception.mappers;

import com.pintabar.webservices.response.errors.ErrorCode;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.*;

/**
 * Created by lucasgodoy on 22/06/17.
 */
@Provider
@Component
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	private final ExceptionMapperHelper exceptionMapperHelper;

	public GenericExceptionMapper(ExceptionMapperHelper exceptionMapperHelper) {
		this.exceptionMapperHelper = exceptionMapperHelper;
	}

	@Override
	public Response toResponse(Throwable ex) {
		return Response.status(INTERNAL_SERVER_ERROR)
				.entity(exceptionMapperHelper.buildErrorResponse(INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_ERROR, ex))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}
