package com.pintabar.persistence.entities;

import java.util.EnumSet;

/**
 * Created by lucasgodoy on 13/06/17.
 */
public enum PurchaseOrderStatus {
	OPENED, PROCESSING, READY, DELIVERED, CLOSED_CANCELLED, CLOSED_TO_BE_PAID, CLOSED_PAID;

	public static final EnumSet<PurchaseOrderStatus> CLOSED = EnumSet.of(CLOSED_CANCELLED, CLOSED_TO_BE_PAID, CLOSED_PAID);
	public static final EnumSet<PurchaseOrderStatus> OPEN = EnumSet.of(OPENED, PROCESSING, READY, DELIVERED);
}
