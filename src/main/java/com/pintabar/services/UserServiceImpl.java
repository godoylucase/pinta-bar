package com.pintabar.services;

import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.persistence.dtomappers.UserDTOMapper;
import com.pintabar.persistence.entities.user.User;
import com.pintabar.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public UserDTO getUserByUserName(String username) {
		User user = userRepository.findByUsername(username);
		return userDTOMapper.mapToDTO(user);
	}

	@Override
	public UserDTO getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return userDTOMapper.mapToDTO(user);
	}

	@Override
	public List<UserDTO> getUsers() {
		List<User> users = userRepository.findAll();
		List<UserDTO> userDTOs = new ArrayList<>();
		users.forEach(u -> userDTOs.add(userDTOMapper.mapToDTO(u)));
		return userDTOs;
	}

	@Override
	@Transactional
	public String createUser(UserDTO userDTO) {
		if (!StringUtils.isEmpty(userDTO.getEmail())
				&& !StringUtils.isEmpty(userDTO.getUsername())) {
			// refactor to Optional
			UserDTO emailExist = getUserByEmail(userDTO.getEmail());
			UserDTO usernameExist = getUserByUserName(userDTO.getUsername());

			if (emailExist == null && usernameExist == null) {
				User user = User.builder()
						.username(userDTO.getUsername())
						.email(userDTO.getEmail())
						.build();
				save(user);
				return user.getUuid();
			}
		}
		return null;
	}

	@Inject
	public void setUserDTOMapper(UserDTOMapper userDTOMapper) {
		this.userDTOMapper = userDTOMapper;
	}
}
