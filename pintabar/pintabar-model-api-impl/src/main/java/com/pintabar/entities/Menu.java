package com.pintabar.entities;

import com.pintabar.entities.base.UUIDBaseEntity;
import com.pintabar.interfaces.IMenu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by lucasgodoy on 11/06/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Menu extends UUIDBaseEntity implements IMenu {

	private String name;

	@ManyToOne
	@JoinColumn(name = "business_id")
	private Business business;

	@OneToOne
	@JoinColumn(name = "menu_instance_id")
	private MenuInstance menuInstance = null;

	@Type(type = "yes_no")
	private boolean deleted = false;

}
