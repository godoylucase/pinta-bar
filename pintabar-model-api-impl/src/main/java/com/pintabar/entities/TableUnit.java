package com.pintabar.entities;

import com.pintabar.entities.base.UUIDBaseEntity;
import com.pintabar.interfaces.ITableUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by lucasgodoy on 13/06/17.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TableUnit extends UUIDBaseEntity implements ITableUnit {

	@ManyToOne
	@JoinColumn(name = "business_id")
	private Business business;

	private Integer internalNumber;
}
