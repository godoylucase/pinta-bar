package com.pintabar.exceptions.purchaseorder;

import com.pintabar.exceptions.AppException;
import com.pintabar.exceptions.ErrorCode;

/**
 * Created by lucasgodoy on 23/06/17.
 */
public class ClosedPurchaseOrderException extends AppException {

	public ClosedPurchaseOrderException(ErrorCode errorCode, Object... params) {
		super(errorCode, params);
	}
}
