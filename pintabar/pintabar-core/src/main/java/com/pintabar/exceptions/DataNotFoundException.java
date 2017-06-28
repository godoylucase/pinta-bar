package com.pintabar.exceptions;

import com.pintabar.webservices.apis.exception.AppException;
import com.pintabar.webservices.response.errors.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by lucasgodoy on 24/06/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class DataNotFoundException extends AppException {

	public DataNotFoundException(ErrorCode errorCode, Object... params) {
		super(errorCode, params);
	}
}
