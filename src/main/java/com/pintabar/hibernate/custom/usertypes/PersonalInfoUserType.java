package com.pintabar.hibernate.custom.usertypes;

import com.pintabar.persistence.entities.user.profile.PersonalInfo;
import com.pintabar.hibernate.custom.JacksonUserType;

/**
 * Created by lucasgodoy on 19/03/17.
 */
public class PersonalInfoUserType extends JacksonUserType {
	@Override
	public Class returnedClass() {
		return PersonalInfo.class;
	}
}
