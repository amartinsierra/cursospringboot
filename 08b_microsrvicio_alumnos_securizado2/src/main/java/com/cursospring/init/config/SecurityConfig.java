package com.cursospring.init.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@EnableWebSecurity
@Configuration
public class SecurityConfig {
	@Value("${spring.datasource.driver-class-name}")
	private String driver;
	@Value("${security.db.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String user;
	@Value("${spring.datasource.password}")
	private String pass;
	/* @Bean
		public InMemoryUserDetailsManager  usersDetailsMemory() throws Exception {
			List<UserDetails> users=List.of( 
					User.withUsername("user1")		          
						  .password("{noop}user1")    //lo de {noop} se pone para indicar que no está encriptada  
						  .roles("USERS")
				          .build(),
				    User.withUsername("admin")
				          .password("{noop}admin") 
				          .roles("USERS", "ADMINS")
				          .build(),
				    User.withUsername("user2")
				          .password("{noop}user2")
				          .roles("OPERATORS")
				          .build()
	          );
			return new InMemoryUserDetailsManager(users);		
		}*/
		@Bean
		public JdbcUserDetailsManager usersDetailsJdbc() {
			DriverManagerDataSource ds=new DriverManagerDataSource();
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(pass);
			JdbcUserDetailsManager jdbcDetails=new JdbcUserDetailsManager(ds);
			
			jdbcDetails.setUsersByUsernameQuery("select user, pwd, enabled"
	           	+ " from users where user=?");
			jdbcDetails.setAuthoritiesByUsernameQuery("select user, rol "
	           	+ "from roles where user=?");
			return jdbcDetails;
		}
		
		@Bean
		public PasswordEncoder getEncoder() {
			return new BCryptPasswordEncoder();
		}
	    /*si la base de datos de seguridad es la misma de la aplicación
		@Bean
		public JdbcUserDetailsManager usersDetailsJdbc(DriverManagerDataSource ds) {
			JdbcUserDetailsManager jdbcDetails=new JdbcUserDetailsManager(ds);
			
			jdbcDetails.setUsersByUsernameQuery("select user, pwd, enabled"
	           	+ " from users where user=?");
			jdbcDetails.setAuthoritiesByUsernameQuery("select user, rol "
	           	+ "from roles where user=?");
			return jdbcDetails;
		}*/
		
		/*@Bean
	    public DefaultSpringSecurityContextSource contextSource() {
	        return new DefaultSpringSecurityContextSource(
	                List.of("ldap://localhost:8389/"), "dc=example,dc=com");
	    }
		
		//ldap
		@Bean
	    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
	        AuthenticationManagerBuilder auth =
	                http.getSharedObject(AuthenticationManagerBuilder.class);

	        auth
	            .ldapAuthentication()
	            .userDnPatterns("uid={0},ou=people")
	            .groupSearchBase("ou=groups")
	            .groupSearchFilter("(uniqueMember={0})")
	            .contextSource(contextSource());

	        return auth.build();
	    }*/
		
		
		
		
		
		//método en el que se definen las restricciones de acceso a los recursos
		@Bean
		public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception  {		
			http.csrf(cr->cr.disable())
			.authorizeHttpRequests(aut->
					aut.requestMatchers(HttpMethod.POST,"/alumnos").hasRole("ADMINS")
					.requestMatchers(HttpMethod.DELETE,"/alumnos").hasAnyRole("ADMINS","OPERATORS")
					.requestMatchers(HttpMethod.GET,"/alumnos").authenticated()
					.anyRequest().permitAll())
			.httpBasic(Customizer.withDefaults());
			return http.build();
			
		}
	
}
