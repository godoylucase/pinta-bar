package com.pintabar.persistence.entities;

import com.pintabar.entities.base.UUIDBaseEntity;
import com.pintabar.persistence.interfaces.IMenuItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

	//private String description;

	@Type(type = "yes_no")
	private boolean deleted = false;

	@Type(type = "yes_no")
	private boolean available = true;

	private BigDecimal price = BigDecimal.ZERO;

	@ManyToMany(mappedBy = "items")
	public List<MenuCategory> categories = new ArrayList<>();
}
