package com.pintabar.controllers;

import com.pintabar.dto.CommentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasgodoy on 26/06/17.
 */
@RestController
@RequestMapping(value = "/review-api",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE)
public class DummyCommentRestController {

	@RequestMapping(value = "/dummy", method = RequestMethod.GET)
	public ResponseEntity<List<CommentDTO>> getDummyComments() {
		List<CommentDTO> comments = new ArrayList<>();
		comments.add(CommentDTO.builder()
				.businessUuid("5a0ea89d-2166-4ff8-b3ae-f96c1398a866")
				.userUuid("2110b06a-ae41-449f-85d0-2485d9d8befa")
				.comment("This place is awesome")
				.build());
		comments.add(CommentDTO.builder()
				.businessUuid("5a0ea89d-2166-4ff8-b3ae-f96c1398a866")
				.userUuid("2110b06a-ae41-449f-85d0-2485d9d8befa")
				.comment("I have been in better places so far...")
				.build());
		comments.add(CommentDTO.builder()
				.businessUuid("5a0ea89d-2166-4ff8-b3ae-f96c1398a866")
				.userUuid("2110b06a-ae41-449f-85d0-2485d9d8befa")
				.comment("Good service")
				.build());
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
}
