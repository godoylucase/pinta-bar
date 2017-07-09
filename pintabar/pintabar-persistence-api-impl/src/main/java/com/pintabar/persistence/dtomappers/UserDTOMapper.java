package com.pintabar.persistence.dtomappers;

import com.pintabar.dto.UserDTO;
import com.pintabar.dtomappers.GenericDTOMapper;
import com.pintabar.entities.user.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * Created by lucasgodoy on 21/03/17.
 */
@Component
public class UserDTOMapper implements GenericDTOMapper<User, UserDTO> {

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public Optional<UserDTO> mapToDTO(@Nullable User user) {
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
