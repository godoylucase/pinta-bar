package com.pintabar.persistence.entities;

import com.pintabar.persistence.entities.base.UUIDBaseEntity;
import com.pintabar.persistence.interfaces.ITable;
import lombok.Builder;
import lombok.Getter;
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
@Entity
public class Table extends UUIDBaseEntity implements ITable {

	@ManyToOne
	@JoinColumn(name = "business_id")
	private Business business;

	private int internalNumber;
}
