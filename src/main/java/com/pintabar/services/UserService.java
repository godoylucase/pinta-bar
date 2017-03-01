package com.pintabar.services;

import com.pintabar.entities.entities.user.User;

import java.io.Serializable;

/**
 * Created by lucasgodoy on 12/03/17.
 */
public interface UserService<T extends User, ID extends Serializable>
		extends GenericService<T, ID> {
}
