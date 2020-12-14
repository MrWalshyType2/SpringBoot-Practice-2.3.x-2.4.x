package com.example.security;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final AdminServerProperties adminServerProps;
	
	public WebSecurityConfig(AdminServerProperties adminServerProps) {
		this.adminServerProps = adminServerProps;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String path = this.adminServerProps.getContextPath();
		
		SavedRequestAwareAuthenticationSuccessHandler successHandler = 
				new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setTargetUrlParameter("redirectTo");
		successHandler.setDefaultTargetUrl(this.adminServerProps.getContextPath() + "/");
	
		http.authorizeRequests()
				.antMatchers(path + "/assets/**").permitAll()
				.antMatchers(path + "/login").permitAll()
				.antMatchers(path + "/actuator/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.formLogin()
					.loginPage(path + "/login")
					.successHandler(successHandler)
			.and()
				.logout()
					.logoutUrl(path + "/logout")
			.and()
				.httpBasic()
			.and()
				.csrf()
					.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
					.ignoringRequestMatchers(
							new AntPathRequestMatcher(path + "/instances", HttpMethod.POST.toString()),
							new AntPathRequestMatcher(path + "/instances/*", HttpMethod.DELETE.toString()),
							new AntPathRequestMatcher(path + "/actuator/**"))
			.and()
				.rememberMe()
					.key(UUID.randomUUID().toString())
					.tokenValiditySeconds(1000000);
	}
	
	
}
