package com.proma.pma.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{

		auth.jdbcAuthentication()
		.usersByUsernameQuery("select username, password, enabled "+
		"from user_accounts where username = ?")
		.authoritiesByUsernameQuery("select username, role " +
		"from user_accounts where username = ?")
		.dataSource(dataSource)
		.passwordEncoder(bCryptEncoder); // radka dekoduje pouziva BCrypt
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
//		.antMatchers("/projects/new").hasAuthority("ADMIN") // pro metodu hasRole() musi byt tam ten pre-pend "ROLE_ADMIN" nebo "ROLE_USER"
//		.antMatchers("/projects/save").hasAuthority("ADMIN") //  user_id=2 Tom,tom USER / user_id Tim,tom ADMIN
//		.antMatchers("/employees/new").hasAuthority("USER") // tady ten pre-pend byt nemusi
//		.antMatchers("/employees/save").hasAuthority("USER")
		.antMatchers("/","/**").permitAll()
		.and().formLogin(); 
		
	}
	}
	
