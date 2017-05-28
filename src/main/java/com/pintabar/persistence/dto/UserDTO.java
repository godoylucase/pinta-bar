package com.pintabar.persistence.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by lucasgodoy on 21/03/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
public class UserDTO implements BaseDTO {
	private String username;
	private String email;
}
