package com.pintabar.persistence.entities.user;

import com.pintabar.persistence.entities.base.UUIDBaseEntity;
import com.pintabar.persistence.entities.user.profile.UserProfile;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasgodoy on 4/03/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User extends UUIDBaseEntity {
	private String username;
	private String email;

	@OneToMany(mappedBy = "user")
	private List<UserProfile> userProfiles = new ArrayList<>();
}
