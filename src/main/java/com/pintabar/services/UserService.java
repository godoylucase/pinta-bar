package com.pintabar.services;

import com.pintabar.persistence.dto.UserDTO;

/**
 * Created by lucasgodoy on 12/03/17.
 */
public interface UserService<T, ID> extends GenericService<T, ID> {

	UserDTO getUser(Long id);

	UserDTO getUser(String uuid);
}
