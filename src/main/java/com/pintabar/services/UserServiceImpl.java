package com.pintabar.services;

import com.pintabar.entities.entities.user.User;
import com.pintabar.repositories.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Created by lucasgodoy on 12/03/17.
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long>
		implements UserService<User, Long> {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	}
}
