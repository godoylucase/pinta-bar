package com.pintabar.services;

import com.pintabar.repositories.GenericJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lucasgodoy on 12/03/17.
 */
@Transactional(propagation = Propagation.REQUIRED)
public class GenericServiceImpl<T, ID extends Serializable>
		implements GenericService<T, ID> {

	private final GenericJpaRepository genericJpaRepository;

	protected GenericServiceImpl(GenericJpaRepository genericJpaRepository) {
		this.genericJpaRepository = genericJpaRepository;
	}

	@Override
	public T save(T entity) {
		return (T) genericJpaRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public T readOne(ID id) {
		return (T) genericJpaRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> readAll(List<ID> ids) {
		return genericJpaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public T readOneByUuid(String uuid) {
		return (T) genericJpaRepository.findByUuid(uuid);
	}

	@Override
	public T findOne(ID id) {
		return (T) genericJpaRepository.getOne(id);
	}

	@Override
	public List<T> findAll(List<ID> ids) {
		return genericJpaRepository.findAll();
	}
}
