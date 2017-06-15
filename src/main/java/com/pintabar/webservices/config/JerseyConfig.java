package com.pintabar.webservices.config;

import com.pintabar.webservices.apis.BusinessAPI;
import com.pintabar.webservices.apis.UserAPI;
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
		register(BusinessAPI.class);
	}
}
