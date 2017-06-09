package com.pintabar.security.exceptions;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * Created by lucasgodoy on 9/06/17.
 */
public class AuthMethodNotSupportedException extends AuthenticationServiceException {
	private static final long serialVersionUID = 3705043083010304496L;

	public AuthMethodNotSupportedException(String msg) {
		super(msg);
	}
}