package com.pintabar.webservices.response.errors;

import com.pintabar.exceptions.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	private int httpStatus;
	private ErrorCode code;
	private String message;
	private String developerMessage;
}
