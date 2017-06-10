package com.pintabar.webservices.response.errors;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by lucasgodoy on 10/06/17.
 */
@Component
public class ResponseErrorHandlerImpl implements ResponseErrorHandler {

	private final ResourceBundleMessageSource messageSource;

	public ResponseErrorHandlerImpl(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public Response getResponse(Response.Status status, ErrorCode errorCode, Object... params) {
		String message = messageSource.getMessage(errorCode.getMessage(), params,
				LocaleContextHolder.getLocale());
		ErrorResponse errorResponse = ErrorResponse.builder()
				.status(status.getStatusCode())
				.code(errorCode)
				.message(message)
				.build();
		return Response.status(status)
				.entity(errorResponse)
				.type(MediaType.APPLICATION_XML)
				.build();
	}
}
