package com.pintabar.persistence.repositories;

import com.pintabar.entities.MenuInstance;
import com.pintabar.persistence.repositories.custom.CustomMenuInstanceRepository;
import com.pintabar.repositories.GenericJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lucas.Godoy on 9/07/17.
 */
@Transactional
public interface MenuInstanceRepository extends GenericJpaRepository<MenuInstance, Long>,
		CustomMenuInstanceRepository {
}
