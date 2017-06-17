package com.pintabar.persistence.entities.user;

import com.google.common.base.Objects;
import com.pintabar.persistence.entities.base.UUIDBaseEntity;
import com.pintabar.persistence.entities.user.profile.UserProfile;
import lombok.*;
import org.hibernate.annotations.Type;

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
public class User extends UUIDBaseEntity {
	private String username;
	private String email;
	@Type(type = "yes_no")
	private boolean deleted = false;

	@OneToMany(mappedBy = "user")
	private List<UserProfile> userProfiles = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return deleted == user.deleted &&
				Objects.equal(username, user.getUsername()) &&
				Objects.equal(email, user.getEmail()) &&
				Objects.equal(uuid, user.getUuid());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(username, email, deleted, uuid);
	}
}
