package com.pintabar.persistence.entities;

import com.pintabar.persistence.entities.base.TimestampedBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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

	@OneToOne
	@JoinColumn(name = "item_id")
	private MenuItem item;

	@ManyToOne
	@JoinColumn(name = "purchase_order_id")
	private PurchaseOrder purchaseOrder;
}
