package com.pintabar.persistence.repositories;

import com.pintabar.persistence.entities.user.User;

/**
 * Created by lucasgodoy on 10/03/17.
 */
public interface UserRepository extends GenericJpaRepository<User, Long> {
	User findByUsername(String username);

	User findByEmail(String email);
}
