package com.pintabar.webservices.config;

import com.pintabar.PintabarServerApplication;
import com.pintabar.webservices.apis.BusinessAPI;
import com.pintabar.webservices.apis.ReviewAPI;
import com.pintabar.webservices.apis.UserAPI;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import java.util.logging.Logger;

/**
 * Created by lucasgodoy on 20/03/17.
 */
@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		packages("com.pintabar.webservices.apis.exception.mappers");
		register(UserAPI.class);
		register(BusinessAPI.class);
		register(ReviewAPI.class);
		register(new LoggingFilter(Logger.getLogger(PintabarServerApplication.class.getName()), true));
	}
}
