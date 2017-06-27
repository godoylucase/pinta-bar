package com.pintabar.persistence.repositories;

import com.pintabar.entities.PurchaseOrder;
import com.pintabar.persistence.repositories.custom.CustomPurchaseOrderRepository;
import com.pintabar.repositories.GenericJpaRepository;

import javax.transaction.Transactional;

/**
 * Created by lucasgodoy on 14/06/17.
 */
@Transactional
public interface PurchaseOrderRepository extends GenericJpaRepository<PurchaseOrder, Long>,
		CustomPurchaseOrderRepository {
}
