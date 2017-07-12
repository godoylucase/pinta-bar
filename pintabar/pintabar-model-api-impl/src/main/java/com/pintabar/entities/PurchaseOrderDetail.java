package com.pintabar.entities;

import com.pintabar.entities.base.TimestampedBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

/**
 * Created by lucasgodoy on 13/06/17.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PurchaseOrderDetail extends TimestampedBaseEntity {

	private BigDecimal quantity = BigDecimal.ZERO;

	@OneToOne
	@JoinColumn(name = "menu_item_instance_id")
	private MenuItemInstance menuItemInstance;

	@ManyToOne
	@JoinColumn(name = "purchase_order_id")
	private PurchaseOrder purchaseOrder;
}
