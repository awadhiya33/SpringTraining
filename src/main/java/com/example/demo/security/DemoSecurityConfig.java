package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
	
	
	//add support for JDBC and no more hardcode for users
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id=?");
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
		return jdbcUserDetailsManager;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer->configurer
				.requestMatchers(HttpMethod.GET,"/api/employees").hasRole("Employee")
				.requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("Employee")
				.requestMatchers(HttpMethod.POST,"/api/employees").hasRole("Manager")
				.requestMatchers(HttpMethod.PUT,"/api/employees/**").hasRole("Manager")
				.requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("Admin")
				);
		// use HTTP Basic authentication
		http.httpBasic(Customizer.withDefaults());
		http.csrf(csrf->csrf.disable());
		return http.build();
	}
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsManager() {
//		UserDetails abhishek=User.builder().username("abhi").password("{noop}test123").roles("Employee","Manager","Admin").build();	
//		UserDetails amit=User.builder().username("amit").password("{noop}test123").roles("Employee","Manager").build();
//		UserDetails mishra=User.builder().username("mishra").password("{noop}test123").roles("Employee").build();
//		
//		return new InMemoryUserDetailsManager(abhishek,amit,mishra);
//	}
}
