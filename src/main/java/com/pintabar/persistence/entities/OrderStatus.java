package com.pintabar.persistence.entities;

import java.util.EnumSet;

/**
 * Created by lucasgodoy on 13/06/17.
 */
public enum OrderStatus {
	OPENED, PROCESSING, READY, DELIVERED, CLOSED_CANCELLED, CLOSED_TO_BE_PAID, CLOSED_PAID;

	public static final EnumSet<OrderStatus> CLOSED = EnumSet.of(CLOSED_CANCELLED, CLOSED_TO_BE_PAID, CLOSED_PAID);
	public static final EnumSet<OrderStatus> OPEN = EnumSet.of(OPENED, PROCESSING, READY, DELIVERED);
}
