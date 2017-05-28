package com.pintabar.services;

import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.persistence.dtomappers.UserDTOMapper;
import com.pintabar.persistence.entities.user.User;
import com.pintabar.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by lucasgodoy on 12/03/17.
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long>
		implements UserService<User, Long> {

	private final UserRepository userRepository;
	private UserDTOMapper userDTOMapper;

	public UserServiceImpl(UserRepository userRepository) {
		super(userRepository);
		this.userRepository = userRepository;
	}

	@Override
	public UserDTO getUser(Long id) {
		User user = this.readOne(id);
		return userDTOMapper.mapToDTO(user);
	}

	@Override
	public UserDTO getUser(String uuid) {
		User user = userRepository.findByUuid(uuid);
		return userDTOMapper.mapToDTO(user);
	}

	@Inject
	public void setUserDTOMapper(UserDTOMapper userDTOMapper) {
		this.userDTOMapper = userDTOMapper;
	}
}
