package com.example.Spring_Security.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.Spring_Security.Utils.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder()throws Exception{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http ,JwtFilter jwtFilter) throws Exception {
		//1.CSRF is disable
		//2.request authenticate all request
		//3.formlogin with default
		//4.httpBasic with default
		http 
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(auth -> auth// 2. Authorize all requests
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/todo/**").permitAll()
	            .anyRequest().authenticated()
	     )
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
		
	}
}
