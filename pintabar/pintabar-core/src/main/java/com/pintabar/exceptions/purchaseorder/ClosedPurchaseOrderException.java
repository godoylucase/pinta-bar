package com.pintabar.exceptions.purchaseorder;

import com.pintabar.webservices.apis.exception.AppException;
import com.pintabar.webservices.response.errors.ErrorCode;

/**
 * Created by lucasgodoy on 23/06/17.
 */
public class ClosedPurchaseOrderException extends AppException {

	public ClosedPurchaseOrderException(ErrorCode errorCode, Object... params) {
		super(errorCode, params);
	}
}
