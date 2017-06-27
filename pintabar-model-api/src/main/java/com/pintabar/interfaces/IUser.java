package com.pintabar.interfaces;

/**
 * Created by lucasgodoy on 2/06/17.
 */
public interface IUser {

	String getUsername();

	String getEmail();

	boolean isDeleted();

	// List<IUserProfile>getUserProfiles();

}
