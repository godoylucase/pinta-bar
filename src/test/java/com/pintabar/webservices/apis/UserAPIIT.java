package com.pintabar.webservices.apis;

import com.google.common.collect.Lists;
import com.pintabar.BaseRestIntegrationTest;
import com.pintabar.persistence.entities.user.User;
import com.pintabar.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lucasgodoy on 5/06/17.
 */
public class UserAPIIT extends BaseRestIntegrationTest {

	@Autowired
	private UserRepository userRepository;

	private List<User> users = Lists.newArrayList();

	@Before
	@Transactional
	public void setUp() {
		userRepository.deleteAll();
		users = createUsers();
		userRepository.save(users);
	}

	@Test
	public void verifyQuantityOfUsers_test() {
		assertThat(getUsers().size()).isEqualTo(users.size());
	}

	private List<User> getUsers() {
		User[] fetchedUsers =
				given().when()
						.get("/api/user").as(User[].class);
		return Lists.newArrayList(fetchedUsers);
	}

	private List<User> createUsers() {
		User user1 = User.builder()
				.email("lucas@pintabar.com")
				.username("lucas")
				.build();

		User user2 = User.builder()
				.email("ezequiel@pintabar.com")
				.username("ezequiel")
				.build();

		User user3 = User.builder()
				.email("godoy@pintabar.com")
				.username("godoy")
				.deleted(true)
				.build();

		List<User> users = Lists.newArrayList();
		users.add(user1);
		users.add(user2);
		users.add(user3);

		return users;
	}
}
