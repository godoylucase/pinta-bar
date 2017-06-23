package com.pintabar.persistence.repositories;

import com.pintabar.persistence.entities.MenuItem;
import com.pintabar.repositories.GenericJpaRepository;

/**
 * Created by lucasgodoy on 23/06/17.
 */
public interface MenuItemRepository extends GenericJpaRepository<MenuItem, Long> {
}
