package com.pintabar.hibernate.custom.usertypes;

import com.pintabar.entities.entities.user.profile.BusinessInfo;
import com.pintabar.hibernate.custom.JacksonUserType;

/**
 * Created by lucasgodoy on 19/03/17.
 */
public class BusinessInfoUserType extends JacksonUserType {
	@Override
	public Class returnedClass() {
		return BusinessInfo.class;
	}
}
