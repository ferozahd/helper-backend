package com.shippingoo.config;


import com.shippingoo.service.userdetails.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JWTTokenHelper jwTTokenHelper;
    private AuthenticationEntryPoint authenticationEntryPoint;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);

	}


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
    //     .authorizeRequests()
    //     .antMatchers("/", "/home").permitAll()
    //     .anyRequest().authenticated()
    //     .and()
    // .formLogin()
      
    //     .permitAll()
    //     .and()
    // .logout()
    //     .permitAll();


			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
			.and().authorizeRequests((request) -> request.antMatchers( "/auth/**").permitAll().antMatchers("/file/**").permitAll()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated())
			.addFilterBefore(new JWTAuthenticationFilter(customUserDetailsService, jwTTokenHelper),UsernamePasswordAuthenticationFilter.class);

		http.csrf().disable().cors().and().headers().frameOptions().disable();

	}

}
