package com.pintabar;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.Response;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by lucasgodoy on 15/03/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class AbstractBaseRestIntegrationTest {

	@LocalServerPort
	protected int serverPort;

	@Before
	public void init() {
		RestAssured.port = serverPort;
		RestAssured.defaultParser = Parser.JSON;
	}

	@Test
	public void basePing_test() {
		given()
				.when().get("/api/user")
				.then().statusCode(Response.Status.OK.getStatusCode());
	}
}
