package com.pintabar.webservices.apis.exception.mappers;

import com.pintabar.webservices.response.errors.ErrorCode;
import com.pintabar.webservices.response.errors.ErrorResponse;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by lucasgodoy on 24/06/17.
 */
@Component
public class ExceptionMapperHelper {

	private final ResourceBundleMessageSource messageSource;

	public ExceptionMapperHelper(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	ErrorResponse buildErrorResponse(Response.Status httpStatus, ErrorCode errorCode, Throwable ex, Object... params) {
		String message = messageSource.getMessage(errorCode.getMessage(), params,
				LocaleContextHolder.getLocale());

		StringWriter errorStackTrace = new StringWriter();
		ex.printStackTrace(new PrintWriter(errorStackTrace));

		return ErrorResponse.builder()
				.httpStatus(httpStatus.getStatusCode())
				.code(errorCode)
				.message(message)
				.developerMessage(errorStackTrace.toString())
				.build();
	}
}
