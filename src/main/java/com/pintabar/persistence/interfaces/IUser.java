package com.pintabar.persistence.interfaces;

import java.util.List;

/**
 * Created by lucasgodoy on 2/06/17.
 */
public interface IUser {

	String getUsername();

	String getEmail();

	boolean isDeleted();

	// List<IUserProfile>getUserProfiles();

}
