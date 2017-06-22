package com.pintabar.webservices.response.errors;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.PrintWriter;
import java.io.StringWriter;

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
	public Response createResponse(Response.Status httpStatus, ErrorCode errorCode, Object... params) {
		return doCreateResponse(httpStatus, errorCode, null, params);
	}

	@Override
	public Response createResponse(Response.Status httpStatus, ErrorCode errorCode, Throwable ex, Object... params) {
		return doCreateResponse(httpStatus, errorCode, ex, params);
	}

	private Response doCreateResponse(Response.Status httpStatus, ErrorCode errorCode, Throwable ex, Object... params) {
		String message = messageSource.getMessage(errorCode.getMessage(), params,
				LocaleContextHolder.getLocale());

		ErrorResponse errorResponse = ErrorResponse.builder()
				.httpStatus(httpStatus.getStatusCode())
				.code(errorCode)
				.message(message)
				.build();

		if (ex != null) {
			StringWriter errorStackTrace = new StringWriter();
			ex.printStackTrace(new PrintWriter(errorStackTrace));
			errorResponse.setDeveloperMessage(errorStackTrace.toString());
		}

		return Response.status(httpStatus)
				.entity(errorResponse)
				.type(MediaType.APPLICATION_XML)
				.build();
	}
}
