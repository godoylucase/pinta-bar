package com.pintabar.persistence.entities;

import com.pintabar.persistence.entities.base.UUIDBaseEntity;
import com.pintabar.persistence.interfaces.IMenu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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

	@Type(type = "yes_no")
	private boolean deleted = false;

	@ManyToOne
	@JoinColumn(name = "business_id")
	private Business business;

	@ManyToMany
	@JoinTable(name = "menu_menu_category")
	private List<MenuCategory> categories = new ArrayList<>();
}
