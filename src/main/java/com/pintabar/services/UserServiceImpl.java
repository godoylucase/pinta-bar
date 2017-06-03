package com.pintabar.services;

import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.persistence.dtomappers.UserDTOMapper;
import com.pintabar.persistence.entities.user.User;
import com.pintabar.persistence.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public Optional<UserDTO> getUser(Long id) {
		Optional<User> user = this.readOne(id);
		return userDTOMapper.mapToDTO(user.orElse(null));
	}

	@Override
	public Optional<UserDTO> getUser(String uuid) {
		Optional<User> user = userRepository.findByUuid(uuid);
		return userDTOMapper.mapToDTO(user.orElse(null));
	}

	@Override
	public Optional<UserDTO> getUserByUserName(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return userDTOMapper.mapToDTO(user.orElse(null));
	}

	@Override
	public Optional<UserDTO> getUserByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		return userDTOMapper.mapToDTO(user.orElse(null));
	}

	@Override
	public List<UserDTO> getUsers() {
		List<User> users = userRepository.findAll();
		List<UserDTO> userDTOs = new ArrayList<>();
		users.forEach(u -> userDTOs.add(userDTOMapper.mapToDTO(u).get()));
		return userDTOs;
	}

	@Override
	@Transactional
	public Optional<UserDTO> createUser(UserDTO userDTO) {
		User user = null;
		if (!StringUtils.isEmpty(userDTO.getEmail())
				&& !StringUtils.isEmpty(userDTO.getUsername())) {
			Optional<UserDTO> emailExist = getUserByEmail(userDTO.getEmail());
			Optional<UserDTO> usernameExist = getUserByUserName(userDTO.getUsername());

			if (!emailExist.isPresent() && !usernameExist.isPresent()) {
				user = User.builder()
						.username(userDTO.getUsername())
						.email(userDTO.getEmail())
						.build();
				user = save(user);
			}
		}
		return userDTOMapper.mapToDTO(user);
	}

	@Override
	@Transactional
	public Optional<UserDTO> deleteUser(String uuid) {
		if (!StringUtils.isEmpty(uuid)) {
			Optional<User> user = userRepository.findByUuid(uuid);
			user.ifPresent(u -> u.setDeleted(true));
			return userDTOMapper.mapToDTO(user.orElse(null));
		}
		return Optional.empty();
	}

	@Inject
	public void setUserDTOMapper(UserDTOMapper userDTOMapper) {
		this.userDTOMapper = userDTOMapper;
	}
}
