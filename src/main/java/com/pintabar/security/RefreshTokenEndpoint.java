package com.pintabar.security;

import com.pintabar.persistence.entities.user.User;
import com.pintabar.security.auth.jwt.extractor.TokenExtractor;
import com.pintabar.security.auth.jwt.verifier.TokenVerifier;
import com.pintabar.security.config.JwtSettings;
import com.pintabar.security.config.WebSecurityConstants;
import com.pintabar.security.exceptions.InvalidJwtToken;
import com.pintabar.security.model.UserContext;
import com.pintabar.security.model.token.JwtToken;
import com.pintabar.security.model.token.JwtTokenFactory;
import com.pintabar.security.model.token.RawAccessJwtToken;
import com.pintabar.security.model.token.RefreshToken;
import com.pintabar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by lucasgodoy on 9/06/17.
 */
@RestController
public class RefreshTokenEndpoint {
	@Autowired
	private JwtTokenFactory tokenFactory;
	@Autowired private JwtSettings jwtSettings;
	@Autowired private UserService userService;
	@Autowired private TokenVerifier tokenVerifier;
	@Autowired @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;

	@RequestMapping(value="/api/auth/token", method= RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody
	JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String tokenPayload = tokenExtractor.extract(request.getHeader(WebSecurityConstants.JWT_TOKEN_HEADER_PARAM));

		RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
		RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());

		String jti = refreshToken.getJti();
		if (!tokenVerifier.verify(jti)) {
			throw new InvalidJwtToken();
		}

		String subject = refreshToken.getSubject();
		Optional<User> optional = userService.getUserByUsername(subject);
		if (!optional.isPresent())
			throw new UsernameNotFoundException("User not found: " + subject);
		User user = optional.get();

		if (user.getRoles() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
				.collect(Collectors.toList());

		UserContext userContext = UserContext.create(user.getUsername(), authorities);

		return tokenFactory.createAccessJwtToken(userContext);
	}
}