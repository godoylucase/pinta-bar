package com.pintabar.persistence.dtomappers;

import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.persistence.entities.user.User;
import org.springframework.stereotype.Component;

/**
 * Created by lucasgodoy on 21/03/17.
 */
@Component
public class UserDTOMapper implements GenericDTOMapper<User, UserDTO> {

	@Override
	public UserDTO mapToDTO(User user) {
		if (user != null) {
			UserDTO userDTO = UserDTO.builder()
					.username(user.getUsername())
					.email(user.getEmail())
					.build();
			userDTO.setUuid(user.getUuid());
			userDTO.setCreatedOn(user.getCreatedOn());
			userDTO.setUpdatedOn(user.getUpdatedOn());
			return userDTO;
		}
		return null;
	}
}
