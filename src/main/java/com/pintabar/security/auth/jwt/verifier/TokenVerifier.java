package com.pintabar.security.auth.jwt.verifier;

/**
 * Created by lucasgodoy on 9/06/17.
 */
public interface TokenVerifier {
	boolean verify(String jti);
}
