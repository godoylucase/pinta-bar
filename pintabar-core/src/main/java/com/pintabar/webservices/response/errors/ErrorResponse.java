package com.pintabar.webservices.response.errors;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by lucasgodoy on 10/06/17.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class ErrorResponse {
	private int status;
	private ErrorCode code;
	private String message;
}
