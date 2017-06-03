package com.pintabar.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by lucasgodoy on 12/03/17.
 */
@NoRepositoryBean
@Transactional
public interface GenericJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	Optional<T> findByUuid(String uuid);

}
