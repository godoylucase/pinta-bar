package com.pintabar.entities;

import com.pintabar.entities.base.UUIDBaseEntity;
import com.pintabar.interfaces.IBusiness;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lucasgodoy on 11/06/17.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Business extends UUIDBaseEntity implements IBusiness {

	@Enumerated(EnumType.STRING)
	private BusinessType type = BusinessType.BAR;

	private String name;

	@Type(type = "yes_no")
	private boolean deleted = false;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "business")
	private Set<Menu> menus = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "business")
	private List<TableUnit> tableUnits = new ArrayList<>();
}
