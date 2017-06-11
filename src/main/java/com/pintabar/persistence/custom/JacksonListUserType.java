package com.pintabar.persistence.custom;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Created by lucasgodoy on 19/03/17.
 */
public abstract class JacksonListUserType extends JacksonUserType {

	@Override
	public JavaType createJavaType(ObjectMapper mapper) {
		return mapper.getTypeFactory().constructCollectionType(List.class, returnedClass());
	}
}
