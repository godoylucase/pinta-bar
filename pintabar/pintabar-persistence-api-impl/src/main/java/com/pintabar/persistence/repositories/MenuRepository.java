package com.pintabar.persistence.repositories;

import com.pintabar.entities.Menu;
import com.pintabar.persistence.repositories.custom.CustomMenuRepository;
import com.pintabar.repositories.GenericJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lucasgodoy on 18/06/17.
 */
@Transactional
public interface MenuRepository extends GenericJpaRepository<Menu, Long>,
		CustomMenuRepository {
}
