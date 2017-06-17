package com.pintabar.persistence.custom.usertypes;

import com.pintabar.persistence.custom.JacksonUserType;
import com.pintabar.persistence.entities.user.profile.PersonalInfo;

/**
 * Created by lucasgodoy on 19/03/17.
 */
public class PersonalInfoUserType extends JacksonUserType {
	@Override
	public Class returnedClass() {
		return PersonalInfo.class;
	}
}
