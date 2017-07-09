package com.pintabar.entities;

import com.pintabar.entities.base.UUIDBaseEntity;
import com.pintabar.interfaces.IMenuItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by lucasgodoy on 11/06/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MenuItem extends UUIDBaseEntity implements IMenuItem {

	private String name;

	private String description;

	@OneToOne
	@JoinColumn(name = "business_id")
	private Business business;

	@OneToOne
	@JoinColumn(name = "menu_item_instance_id")
	private MenuItemInstance menuItemInstance = null;

	@Type(type = "yes_no")
	private boolean deleted = false;

}
