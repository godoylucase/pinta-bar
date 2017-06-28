package com.pintabar.persistense.persistense.dtomappers;

import com.pintabar.dto.UserDTO;
import com.pintabar.persistence.dtomappers.UserDTOMapper;
import com.pintabar.entities.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lucasgodoy on 5/06/17.
 */
public class UserDTOMapperTest {

	private UserDTOMapper userDTOMapper;

	@Before
	public void setUp() {
		userDTOMapper = new UserDTOMapper();
	}

	@Test
	public void mapToUserDTOWithNonNullUser_test() {
		User user = User.builder()
				.email("lucas@gmail.com")
				.username("lucas")
				.deleted(false).build();

		Optional<UserDTO> optionalUserDTO = userDTOMapper.mapToDTO(user);
		assertThat(optionalUserDTO.isPresent()).isTrue();
		UserDTO userDTO = optionalUserDTO.get();

		assertThat(user.getEmail()).isEqualTo(userDTO.getEmail());
		assertThat(user.getUsername()).isEqualTo(userDTO.getUsername());
		assertThat(user.isDeleted()).isEqualTo(userDTO.isDeleted());
	}

	@Test
	public void mapToUserDTOWithNullUser_test() {
		Optional<UserDTO> optionalUserDTO = userDTOMapper.mapToDTO(null);
		assertThat(optionalUserDTO.isPresent()).isFalse();
	}
}
