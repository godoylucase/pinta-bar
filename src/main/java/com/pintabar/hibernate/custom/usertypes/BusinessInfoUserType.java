package com.pintabar.hibernate.custom.usertypes;

import com.pintabar.hibernate.custom.JacksonUserType;
import com.pintabar.persistence.entities.user.profile.BusinessInfo;

/**
 * Created by lucasgodoy on 19/03/17.
 */
public class BusinessInfoUserType extends JacksonUserType {
	@Override
	public Class returnedClass() {
		return BusinessInfo.class;
	}
}
