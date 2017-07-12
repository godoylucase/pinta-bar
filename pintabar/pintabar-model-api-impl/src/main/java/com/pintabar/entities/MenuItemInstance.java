package com.pintabar.entities;

import com.pintabar.entities.base.UUIDBaseEntity;
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
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * @author Lucas.Godoy on 7/07/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MenuItemInstance extends UUIDBaseEntity {

	@ManyToOne
	@JoinColumn(name = "menu_category_instance_id")
	private MenuCategoryInstance menuCategoryInstance;

	@OneToOne(mappedBy = "menuItemInstance")
	private MenuItem menuItem;

	@Type(type = "yes_no")
	private boolean available = true;

	private BigDecimal price = BigDecimal.ZERO;

	@Transient
	public boolean isFullAvailable() {
		return menuCategoryInstance != null
				&& menuCategoryInstance.isAvailable()
				&& menuCategoryInstance.getMenuCategory() != null
				&& !menuCategoryInstance.getMenuCategory().isDeleted()
				&& menuCategoryInstance.getMenuInstance() != null
				&& menuCategoryInstance.getMenuInstance().isAvailable()
				&& menuCategoryInstance.getMenuInstance().getMenu() != null
				&& !menuCategoryInstance.getMenuInstance().getMenu().isDeleted();
	}
}
