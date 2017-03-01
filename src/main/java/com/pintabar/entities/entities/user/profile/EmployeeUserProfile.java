package com.pintabar.entities.entities.user.profile;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by lucasgodoy on 16/03/17.
 */
@Getter
@Setter
@Entity
@Table(name = "user_profile_employee")
@PrimaryKeyJoinColumn(name = "id")
public class EmployeeUserProfile extends UserProfile {

	@Type(type = "com.pintabar.hibernate.custom.usertypes.PersonalInfoUserType")
	private PersonalInfo personalInfo;
}
