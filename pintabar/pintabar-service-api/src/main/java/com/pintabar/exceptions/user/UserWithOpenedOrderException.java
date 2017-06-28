package com.pintabar.exceptions.user;

import com.pintabar.exceptions.AppException;
import com.pintabar.exceptions.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by lucasgodoy on 22/06/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class UserWithOpenedOrderException extends AppException {

	public UserWithOpenedOrderException(ErrorCode errorCode, Object... params) {
		super(errorCode, params);
	}
}
