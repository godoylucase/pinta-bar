package com.pintabar.services;

import com.pintabar.persistence.dto.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Created by lucasgodoy on 12/03/17.
 */
public interface UserService<T, ID> extends GenericService<T, ID> {

	UserDTO getUser(Long id);

	UserDTO getUser(String uuid);

	UserDTO getUserByUserName(String username);

	UserDTO getUserByEmail(String email);

	List<UserDTO> getUsers();

	String createUser(UserDTO userDTO);

	Optional<UserDTO> deleteUser(String uuid);
}
