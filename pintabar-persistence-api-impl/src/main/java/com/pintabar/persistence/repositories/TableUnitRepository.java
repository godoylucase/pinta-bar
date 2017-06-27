package com.pintabar.persistence.repositories;

import com.pintabar.entities.TableUnit;
import com.pintabar.repositories.GenericJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lucasgodoy on 13/06/17.
 */
@Transactional
public interface TableUnitRepository extends GenericJpaRepository<TableUnit, Long> {
}
