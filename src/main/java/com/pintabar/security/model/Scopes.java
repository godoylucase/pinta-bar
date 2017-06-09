package com.pintabar.security.model;

/**
 * Created by lucasgodoy on 9/06/17.
 */
public enum Scopes {
	REFRESH_TOKEN;

	public String authority() {
		return "ROLE_" + this.name();
	}
}