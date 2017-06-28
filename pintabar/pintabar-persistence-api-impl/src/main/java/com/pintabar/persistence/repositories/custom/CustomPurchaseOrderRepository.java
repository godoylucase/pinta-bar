package com.pintabar.persistence.repositories.custom;

import com.pintabar.entities.PurchaseOrder;
import com.pintabar.entities.PurchaseOrderStatus;

import java.util.EnumSet;
import java.util.List;

/**
 * Created by lucasgodoy on 22/06/17.
 */
public interface CustomPurchaseOrderRepository {

	List<PurchaseOrder> findPurchaseOrdersByUserIdAndStatus(Long userId, EnumSet<PurchaseOrderStatus> purchaseOrderStatus);

}
