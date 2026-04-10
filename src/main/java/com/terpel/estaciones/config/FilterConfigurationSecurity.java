package com.terpel.estaciones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class FilterConfigurationSecurity {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers(HttpMethod.GET, "/api/**").hasRole("USER")
					.requestMatchers(HttpMethod.GET, "/api/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.POST, "/api/estaciones/**").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.PUT, "/api/estaciones/**").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.DELETE, "/api/estaciones/**").hasRole("ADMIN")
	                .anyRequest().authenticated()

		).httpBasic(withDefaults());

		return http.build();
			
	}

}
