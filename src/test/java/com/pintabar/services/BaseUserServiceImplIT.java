package com.pintabar.services;

import com.pintabar.BaseIntegrationTest;
import com.pintabar.persistence.entities.user.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lucasgodoy on 15/03/17.
 */
public class BaseUserServiceImplIT extends BaseIntegrationTest {

	@Inject
	private UserServiceImpl userService;

	private static User firstUser;

	@Before
	public void setUp() {
		if(!isSetupExecuted) {
			firstUser = generateAndSaveUser();
			isSetupExecuted = true;
		}
	}

	@Test
	public void saveUser_Test() {
		User readUser = userService.readOne(firstUser.getId()).get();

		assertThat(firstUser.getEmail()).isEqualToIgnoringCase(readUser.getEmail());
		assertThat(firstUser.getUsername()).isEqualToIgnoringCase(readUser.getUsername());
	}

	@Test
	public void readOnlyMethodDoesNotChangeUserValuesOutsideOfTransaction_Test() {
		User userToModify = userService.readOne(firstUser.getId()).get();
		userToModify.setEmail("info@pintabar.com");

		User originalUser = userService.readOne(firstUser.getId()).get();

		assertThat(userToModify.getEmail()).isNotEqualToIgnoringCase(originalUser.getEmail());
	}

	@Test
	@Transactional
	public void findChangeUserWithinTransaction_Test() {
		final String NEW_EMAIL = "info@pintabar.com";
		User userToModify = userService.findOne(firstUser.getId());
		userToModify.setEmail(NEW_EMAIL);

		User readUser = userService.readOne(userToModify.getId()).get();

		assertThat(readUser.getEmail()).isEqualToIgnoringCase(NEW_EMAIL);
	}

	private User generateAndSaveUser() {
		User user = User.builder()
				.email("test@pintabar.com")
				.username("username")
				.build();

		userService.save(user);
		return user;
	}

}
