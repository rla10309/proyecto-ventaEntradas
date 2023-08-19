package com.dawes.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.dawes.servicioImpl.ServicioUsuarioImpl;

@Configuration
@EnableWebSecurity
public class MiSeguridad {
	@Autowired
	ServicioUsuarioImpl su;
	
	@Bean
	public BCryptPasswordEncoder encripta() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filtrocompleto(HttpSecurity http) throws Exception{

		// programamos la autenticación
		http
		.getSharedObject(AuthenticationManagerBuilder.class)
		.userDetailsService(su)
		.passwordEncoder(encripta());
		// autorizamos acceso a los recursos de user
		http
		.authorizeHttpRequests()
		.requestMatchers("/user/**")
		.hasAnyRole("ADMIN","USER")
		.and()
		.exceptionHandling()
		.accessDeniedPage("/403");
		
		// autorizamos acceso a los recursos de admin
		http
		.authorizeHttpRequests()
		.requestMatchers("/admin/**")
		.hasAnyRole("ADMIN")
		.and()
		.exceptionHandling()
		.accessDeniedPage("/403");
		
		// acceso publico a los recursos /, principal, login
		http
		.authorizeHttpRequests()
		.requestMatchers("/","/login","/index", "/css/**", "/img/**", "/usuario/formregistro" , "/usuario/registrarusuario")
		.permitAll()
		.anyRequest()
		.authenticated();
		
		// utilizamos un formulario de login personalizado
		http
		.formLogin()
		.loginPage("/login")
		.permitAll();
		
	
		return http.build();	
	}

}
