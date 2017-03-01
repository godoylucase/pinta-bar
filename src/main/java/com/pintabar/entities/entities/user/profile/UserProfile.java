package com.pintabar.entities.entities.user.profile;

import com.pintabar.entities.entities.base.UUIDBaseEntity;
import com.pintabar.entities.entities.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
