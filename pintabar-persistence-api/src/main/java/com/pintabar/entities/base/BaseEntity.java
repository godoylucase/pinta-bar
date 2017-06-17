package com.pintabar.entities.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by lucasgodoy on 1/03/17.
 */
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements GenericDomainInterface, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
}
