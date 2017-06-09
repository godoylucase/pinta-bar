package com.pintabar.services;

import com.pintabar.persistence.dto.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Created by lucasgodoy on 12/03/17.
 */
public interface UserService<T, ID> extends GenericService<T, ID> {

	Optional<UserDTO> getUser(Long id);

	Optional<UserDTO> getUser(String uuid);

	Optional<UserDTO> getUserByUsername(String username);

	Optional<UserDTO> getUserByEmail(String email);

	List<UserDTO> getUsers(Boolean isDeleted);

	Optional<UserDTO> createUser(UserDTO userDTO);

	Optional<UserDTO> deleteUser(String uuid);
}
