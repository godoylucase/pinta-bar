package com.pintabar.security.auth.jwt.verifier;

import org.springframework.stereotype.Component;

/**
 * Created by lucasgodoy on 9/06/17.
 */
@Component
public class BloomFilterTokenVerifier implements TokenVerifier {
	@Override
	public boolean verify(String jti) {
		return true;
	}
}