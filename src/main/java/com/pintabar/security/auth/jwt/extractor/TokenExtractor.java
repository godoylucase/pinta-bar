package com.pintabar.security.auth.jwt.extractor;

/**
 * Created by lucasgodoy on 9/06/17.
 */
public interface TokenExtractor {

	String extract(String payload);

}
