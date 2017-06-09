package com.pintabar.security.auth.jwt;

import com.pintabar.security.config.WebSecurityConstants;
import com.pintabar.security.auth.JwtAuthenticationToken;
import com.pintabar.security.auth.jwt.extractor.TokenExtractor;
import com.pintabar.security.model.token.RawAccessJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lucasgodoy on 9/06/17.
 *
 * JwtTokenAuthenticationProcessingFilter filter is applied to each API (/api/**)
 * with exception of the refresh token endpoint (/api/auth/token) and login endpoint (/api/auth/login).
 * This filter has the following responsibilities:
 * - Check for access token in X-Authorization header. If Access token is found in the header,
 * delegate authentication to JwtAuthenticationProvider otherwise throw authentication exception
 * - Invokes success or failure strategies based on the outcome of authentication process performed
 * by JwtAuthenticationProvider
 */
public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
	private final AuthenticationFailureHandler failureHandler;
	private final TokenExtractor tokenExtractor;

	@Autowired
	public JwtTokenAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler,
	                                              TokenExtractor tokenExtractor, RequestMatcher matcher) {
		super(matcher);
		this.failureHandler = failureHandler;
		this.tokenExtractor = tokenExtractor;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String tokenPayload = request.getHeader(WebSecurityConstants.JWT_TOKEN_HEADER_PARAM);
		RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));
		return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
	                                        Authentication authResult) throws IOException, ServletException {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authResult);
		SecurityContextHolder.setContext(context);
		chain.doFilter(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
	                                          AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		failureHandler.onAuthenticationFailure(request, response, failed);
	}
}
