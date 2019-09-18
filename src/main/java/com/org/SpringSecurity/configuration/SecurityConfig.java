package com.org.SpringSecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
		.and()
		.withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
		.and()
		.withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/anonymous*").anonymous()
			.antMatchers("/login*").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login.html")
			.loginProcessingUrl("/performLogin")
			.defaultSuccessfulUrl("/homepage.html", true)
			//.failureUrl("/login.html, error="true")
			.failureHandler(authenticationFailureHandler())
			.and()
			.logout()
			.logoutUrl("/performLogout");
			.deleteCookies("JSESSIONID");
			.logoutSuccessHandler(logoutSuccessHandler());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
