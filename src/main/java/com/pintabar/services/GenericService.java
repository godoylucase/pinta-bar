package com.pintabar.services;

import java.util.List;
import java.util.Optional;

/**
 * Created by lucasgodoy on 12/03/17.
 */
public interface GenericService<T, ID> {
	T save(T entity);

	Optional<T> readOne(ID id);

	List<T> readAll(List<ID> ids);

	T readOneByUuid(String uuid);

	/**
	 * Lazy load
	 *
	 * @param id
	 * @return
	 */
	T findOne(ID id);

	/**
	 * Lazy load
	 *
	 * @param ids
	 * @return
	 */
	List<T> findAll(List<ID> ids);
}
