package com.nowon.cho.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

	@Bean
	UserDetailsService userDetailsService() {
		return new HUserDetailsService();
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(13);
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf)->csrf.disable())
			.authorizeHttpRequests(authorize->authorize
					.antMatchers("/css/**", "/img/**", "/js/**").permitAll()
					.antMatchers("/", "/users/signup", "/users/login","/users/signupagree","/cart","/sub/**","/product-info/**","/cs","/faq").permitAll()
					.antMatchers("/myharmony").authenticated()
					.anyRequest().permitAll()
					)
			.formLogin(formlogin->formlogin
					.loginPage("/users/login")
					.loginProcessingUrl("/users/login")
					.usernameParameter("email")
					.passwordParameter("pass")
					.permitAll()
					)
			.logout(logout->logout.logoutSuccessUrl("/"))
			.oauth2Login(oauth2->oauth2
					.loginPage("/users/login")
					.userInfoEndpoint(userInfo->userInfo
					.userService(myOauth2userService())
							)
					)
			;
		
		return http.build();
		
	}
	
	@Bean
	OAuth2UserService<OAuth2UserRequest, OAuth2User> myOauth2userService() {
		return new MyOauth2userService();
	}
}
