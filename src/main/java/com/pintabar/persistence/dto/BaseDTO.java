package com.pintabar.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lucasgodoy on 21/03/17.
 */
@Getter
@Setter
public abstract class BaseDTO {
	private Long id;
	private String uuid;
	private Date createdOn;
	private Date updatedOn;
}
