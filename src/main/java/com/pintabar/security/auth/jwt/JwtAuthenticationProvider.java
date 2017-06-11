package com.pintabar.security.auth.jwt;

import com.pintabar.security.auth.JwtAuthenticationToken;
import com.pintabar.security.config.JwtSettings;
import com.pintabar.security.model.UserContext;
import com.pintabar.security.model.token.RawAccessJwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lucasgodoy on 9/06/17.
 * <p>
 * It has the following responsibilities:
 * - Verify the access token's signature
 * - Extract identity and authorization claims from Access token and use them to create UserContext
 * - If Access token is malformed, expired or simply if token is not signed with the appropriate
 * signing key Authentication exception will be thrown
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
	private final JwtSettings jwtSettings;

	@Autowired
	public JwtAuthenticationProvider(JwtSettings jwtSettings) {
		this.jwtSettings = jwtSettings;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

		Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
		String subject = jwsClaims.getBody().getSubject();
		List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
		List<GrantedAuthority> authorities = scopes.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		UserContext context = UserContext.create(subject, authorities);

		return new JwtAuthenticationToken(context, context.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
