package com.pintabar.persistence.entities;

import com.pintabar.persistence.entities.base.UUIDBaseEntity;
import com.pintabar.persistence.interfaces.IMenuCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * As prototyping phase no category will be available to be created
 * by any API, but fixed types
 * Created by lucasgodoy on 11/06/17.
 */
@Builder
@Getter
@Setter
@Entity
public class MenuCategory extends UUIDBaseEntity implements IMenuCategory{

	@Type(type = "yes_no")
	private boolean deleted = false;

	@Enumerated(EnumType.STRING)
	private MenuCategoryType type = MenuCategoryType.FOOD;

	@ManyToMany(mappedBy = "categories")
	private List<Menu> menues;

	@ManyToMany
	@JoinTable(name = "menu_category_menu_item")
	private List<MenuItem> items = new ArrayList<>();

}
