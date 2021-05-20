package com.samaspace.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		  auth.inMemoryAuthentication()
		  .withUser("toto").password("toto").roles("Admin", "User");
		  
		  auth.inMemoryAuthentication()
		  .withUser("tata").password("tata").roles("User");
		 
		
		/*
		 * auth.jdbcAuthentication() .dataSource(dataSource)
		 * .usersByUsernameQuery("SELECT login as principal, pwd as credentials, role as role from users where login=?"
		 * )
		 * .authoritiesByUsernameQuery("SELECT role as role from users_role where role=?"
		 * ) .rolePrefix("ROLE_");
		 */
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin();
		http.authorizeRequests().antMatchers("/admin/*").hasRole("Admin");
		
		http.authorizeRequests().antMatchers("/user/*").hasRole("User");
		http.exceptionHandling().accessDeniedPage("/403");
	}
	
	 @Bean public PasswordEncoder getPasswordEncoder() 
	 { 
		 return NoOpPasswordEncoder.getInstance();
	 }
	
	
	

}
