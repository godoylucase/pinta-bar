package com.pintabar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 * Created by lucasgodoy on 10/06/17.
 */
@Configuration
public class GeneralConfiguration {

	@Bean
	public LocaleResolver localeResolver() {
		return new AcceptHeaderLocaleResolver();
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("i18n/messages");
		source.setDefaultEncoding("UTF-8");
		source.setFallbackToSystemLocale(false);
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
}
