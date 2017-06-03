package com.pintabar.persistence.dtomappers;

import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.persistence.entities.user.User;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;
import java.util.Optional;

/**
 * Created by lucasgodoy on 21/03/17.
 */
@Component
public class UserDTOMapper implements GenericDTOMapper<User, UserDTO> {

	@Override
	public Optional<UserDTO> mapToDTO(@Null User user) {
		UserDTO userDTO = null;
		if (user != null) {
			userDTO = UserDTO.builder()
					.username(user.getUsername())
					.email(user.getEmail())
					.deleted(user.isDeleted())
					.build();
			userDTO.setUuid(user.getUuid());
			userDTO.setCreatedOn(user.getCreatedOn());
			userDTO.setUpdatedOn(user.getUpdatedOn());
		}
		return Optional.ofNullable(userDTO);
	}
}
