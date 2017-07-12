package com.pintabar.entities;

import com.pintabar.entities.base.UUIDBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Lucas.Godoy on 7/07/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MenuInstance extends UUIDBaseEntity {

	@Type(type = "yes_no")
	private boolean available = false;

	@OneToOne(mappedBy = "menuInstance")
	private Menu menu;

	@OneToMany(mappedBy = "menuInstance")
	private Set<MenuCategoryInstance> menuCategoryInstances = new HashSet<>();

}
