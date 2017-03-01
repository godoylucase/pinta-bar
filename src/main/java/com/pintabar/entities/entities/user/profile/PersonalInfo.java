package com.pintabar.entities.entities.user.profile;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lucasgodoy on 17/03/17.
 */
@Getter
@Setter
public class PersonalInfo implements Serializable {
	private String firstName;
	private String middleName;
	private String lastName;
}
