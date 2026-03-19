package com.cursospring.init.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.cursospring.init.converters.JwtConverter;

import lombok.AllArgsConstructor;
@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {		
		JwtConverter jwtConverter;
		//método en el que se definen las restricciones de acceso a los recursos
		@Bean
		public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception  {		
			http.csrf(cr->cr.disable())
			.authorizeHttpRequests(aut->
					aut.requestMatchers(HttpMethod.POST,"/alumnos").hasRole("ADMINS")
					.requestMatchers(HttpMethod.DELETE,"/alumnos").hasAnyRole("ADMINS","OPERATORS")
					.requestMatchers(HttpMethod.GET,"/alumnos").authenticated()
					.anyRequest().permitAll())
			.oauth2ResourceServer(oauth2ResourceServer->
					oauth2ResourceServer.jwt(jwt->jwt
					.jwtAuthenticationConverter(jwtConverter)))
					.sessionManagement(sessionManagement->
					sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			return http.build();
			
		}
	
}
