package com.pintabar.entities.user.profile;

import com.pintabar.entities.base.UUIDBaseEntity;
import com.pintabar.entities.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by lucasgodoy on 16/03/17.
 */
@Getter
@Setter
@Entity
@Table(name = "user_profile")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserProfile extends UUIDBaseEntity {

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
}
