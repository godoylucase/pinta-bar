package com.pintabar.rest.config;

import com.pintabar.rest.services.UserAPI;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

/**
 * Created by lucasgodoy on 20/03/17.
 */
@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(UserAPI.class);
	}
}
