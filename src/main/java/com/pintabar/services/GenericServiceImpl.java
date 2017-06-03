package com.pintabar.services;

import com.pintabar.persistence.repositories.GenericJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by lucasgodoy on 12/03/17.
 */
@Transactional(propagation = Propagation.REQUIRED)
public class GenericServiceImpl<T, ID extends Serializable>
		implements GenericService<T, ID> {

	private final GenericJpaRepository genericJpaRepository;

	GenericServiceImpl(GenericJpaRepository genericJpaRepository) {
		this.genericJpaRepository = genericJpaRepository;
	}

}
