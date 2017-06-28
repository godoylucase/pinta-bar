package com.pintabar.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by lucasgodoy on 22/06/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class AppException extends Exception {
	protected ErrorCode errorCode;
	protected Object[] params;

	public AppException(ErrorCode errorCode, Object... params) {
		this.errorCode = errorCode;
		this.params = params;
	}
}
