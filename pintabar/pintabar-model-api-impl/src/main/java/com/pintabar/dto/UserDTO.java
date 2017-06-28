package com.pintabar.dto;

import com.pintabar.interfaces.IUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class UserDTO extends BaseDTO implements IUser {
	private String username;
	private String email;
	private boolean deleted = false;
}
