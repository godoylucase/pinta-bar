package com.pintabar.persistence.repositories;

import com.pintabar.persistence.entities.PurchaseOrder;

import javax.transaction.Transactional;

/**
 * Created by lucasgodoy on 14/06/17.
 */
@Transactional
public interface PurchaseOrderRepository extends GenericJpaRepository<PurchaseOrder, Long> {
}
