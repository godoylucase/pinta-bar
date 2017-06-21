package com.pintabar.webservices.apis;

import com.google.common.collect.Lists;
import com.pintabar.AbstractBaseRestIntegrationTest;
import com.pintabar.persistence.dto.UserDTO;
import com.pintabar.persistence.entities.user.User;
import com.pintabar.persistence.repositories.UserRepository;
import com.pintabar.webservices.response.errors.ErrorCode;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

/**
 * Created by lucasgodoy on 5/06/17.
 */
public class UserAPIIT extends AbstractBaseRestIntegrationTest {

	private final static String USER_API_PATH = "/api/user";

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
	public void getUserByUuid_test() {
		User user = users.get(0);
		String uuid = user.getUuid();

		given().pathParam("uuid", uuid)
				.when()
				.get(USER_API_PATH + "/{uuid}")
				.then()
				.body("username", equalTo(user.getUsername()))
				.body("email", equalTo(user.getEmail()))
				.body("uuid", equalTo(user.getUuid()))
				.statusCode(Response.Status.OK.getStatusCode());
	}

	@Test
	public void getUserWithWrongUuid_test() {
		String uuid = "thisIsNotAValidUUID";
		given().pathParam("uuid", uuid)
				.when()
				.get(USER_API_PATH + "/{uuid}")
				.then()
				.body("code", equalTo(ErrorCode.USER_NOT_FOUND))
				.statusCode(Response.Status.NOT_FOUND.getStatusCode());
	}

	@Test
	public void getAllUsers_test() {
		getUsersTests(null);
	}

	@Test
	public void getActiveUsers_test() {
		getUsersTests(false);
	}

	@Test
	public void getDeletedUsers_test() {
		getUsersTests(true);
	}

	@Test
	public void createUser_test() {
		UserDTO newUser = UserDTO.builder()
				.username("newUser")
				.email("newUser@pintabar.com")
				.build();

		// post the user to the API and save into DB
		String location =
				given().contentType(MediaType.APPLICATION_JSON)
						.body(newUser)
						.when()
						.post(USER_API_PATH)
						.then()
						.header("location", not(isEmptyOrNullString()))
						.statusCode(Response.Status.CREATED.getStatusCode())
						.extract()
						.header("location");

		// get created user from 'location' header attribute and compare
		given()
				.when()
				.get(location)
				.then()
				.statusCode(Response.Status.OK.getStatusCode())
				.body("username", equalTo(newUser.getUsername()))
				.body("email", equalTo(newUser.getEmail()));

		// get all users and check if newUser is present and the amount of users is incremented in one more
		getUsersTests(null);
	}

	@Test
	public void createUserWithExistingUsername_test() {
		String username = users.get(0).getUsername();
		String email = "newUser@pintabar.com";
		UserDTO newUser = UserDTO.builder()
				.username(username)
				.email(email)
				.build();
		createUserFailing(newUser);
	}

	@Test
	public void createUserWithExistingEmail_test() {
		String username = "newUser";
		String email = users.get(0).getEmail();
		UserDTO newUser = UserDTO.builder()
				.username(username)
				.email(email)
				.build();
		createUserFailing(newUser);
	}

	@Test
	public void deleteExistentUser_test() {
		User userToDelete = users.get(0);

		given().pathParam("uuid", userToDelete.getUuid())
				.when()
				.delete(USER_API_PATH + "/" + "{uuid}")
				.then()
				.statusCode(Response.Status.ACCEPTED.getStatusCode());

		Optional<User> userDeleted = userRepository.findByUuid(userToDelete.getUuid());
		userDeleted.ifPresent(u -> assertThat(u.isDeleted()).isTrue());
	}

	@Test
	public void deleteNonExistentUser_test() {
		String uuid = "thisIsNotAValidUUID";

		given().pathParam("uuid", uuid)
				.when()
				.delete(USER_API_PATH + "/" + "{uuid}")
				.then()
				.body("code", equalTo(ErrorCode.USER_NOT_FOUND))
				.statusCode(Response.Status.NOT_FOUND.getStatusCode());

		Optional<User> userDeleted = userRepository.findByUuid(uuid);
		assertThat(userDeleted.isPresent()).isFalse();
	}

	public void createUserFailing(UserDTO newUser) {
		given().contentType(MediaType.APPLICATION_JSON)
				.body(newUser)
				.when()
				.post(USER_API_PATH)
				.then()
				.body("code", equalTo(ErrorCode.USER_ALREADY_EXISTS))
				.statusCode(Response.Status.CONFLICT.getStatusCode());

		// double check DB user is not inserted
		Optional<User> noRecordedUserEmail = userRepository.findByEmail(newUser.getEmail());
		Optional<User> noRecordedUserUsername = userRepository.findByUsername(newUser.getUsername());
		assertThat(noRecordedUserEmail.isPresent() && noRecordedUserUsername.isPresent()).isFalse();
	}

	private void getUsersTests(Boolean isDeleted) {
		Predicate<User> predicate;
		if (isDeleted == null) {
			predicate = null;
		} else if (isDeleted) {
			predicate = u -> u.isDeleted();
		} else {
			predicate = u -> !u.isDeleted();
		}

		// updates users list in case of a creation or deletion
		users = userRepository.findAll();

		List<User> filteredBackedUsers = filteredDBUsers(predicate);
		List<User> retrievedUsers = getUsersAPICall(isDeleted);

		boolean areRetrievedUsersTheSame = filteredBackedUsers.containsAll(retrievedUsers);

		assertThat(areRetrievedUsersTheSame).isTrue();
		assertThat(retrievedUsers.size()).isEqualTo(filteredBackedUsers.size());
	}

	private List<User> getUsersAPICall(Boolean isDeleted) {
		User[] fetchedUsers =
				given().queryParam("deleted", isDeleted == null ? "" : isDeleted.toString())
						.when()
						.get(USER_API_PATH)
						.as(User[].class);
		return Lists.newArrayList(fetchedUsers);
	}

	private List<User> filteredDBUsers(Predicate<User> predicate) {
		return predicate == null ? users : users.stream().filter(predicate).collect(Collectors.toList());
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
