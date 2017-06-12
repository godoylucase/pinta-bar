package com.pintabar.persistence.entities;

import com.pintabar.persistence.entities.base.TimestampedBaseEntity;
import lombok.Builder;
import lombok.Getter;
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
@Entity
public class OrderDetail extends TimestampedBaseEntity {

	@OneToOne
	@JoinColumn(name = "item_id")
	private MenuItem item;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
}
