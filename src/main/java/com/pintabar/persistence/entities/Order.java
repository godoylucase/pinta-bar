package com.pintabar.persistence.entities;

import com.pintabar.persistence.entities.base.UUIDBaseEntity;
import com.pintabar.persistence.entities.user.User;
import com.pintabar.persistence.interfaces.IOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasgodoy on 13/06/17.
 */
@Builder
@Getter
@Setter
@Entity
public class Order extends UUIDBaseEntity implements IOrder {

	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.OPENED;

	@OneToOne
	@JoinColumn(name = "table_id")
	private Table table;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<OrderDetail> details = new ArrayList<>();
}
