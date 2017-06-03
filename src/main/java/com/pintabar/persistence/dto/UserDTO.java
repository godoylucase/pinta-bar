package com.pintabar.persistence.dto;

import com.pintabar.persistence.interfaces.IUser;
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
public class UserDTO extends BaseDTO implements IUser {
	private String username;
	private String email;
	private boolean deleted = false;
}
