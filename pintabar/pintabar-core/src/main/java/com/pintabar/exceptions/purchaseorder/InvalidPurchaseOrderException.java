package com.pintabar.exceptions.purchaseorder;

import com.pintabar.webservices.apis.exception.AppException;
import com.pintabar.webservices.response.errors.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by lucasgodoy on 23/06/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class InvalidPurchaseOrderException extends AppException {

	public InvalidPurchaseOrderException(ErrorCode errorCode, Object... params) {
		super(errorCode, params);
	}
}
