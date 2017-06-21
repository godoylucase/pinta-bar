package com.pintabar.persistence.entities;

import com.pintabar.entities.base.UUIDBaseEntity;
import com.pintabar.persistence.interfaces.IMenu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	@JoinTable(name = "menu_menu_category",
			joinColumns = {@JoinColumn(name = "menu_id")},
			inverseJoinColumns = {@JoinColumn(name = "menu_category_id")})
	private List<MenuCategory> categories = new ArrayList<>();
}
