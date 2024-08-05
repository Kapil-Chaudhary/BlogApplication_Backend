package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blog.security.CustomUserDetailService;
import com.blog.security.jwt.JwtAuthenticationEntryPoint;
import com.blog.security.jwt.JwtAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {
	
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

      http.csrf(csrf -> csrf.disable())
              .authorizeHttpRequests(authz -> authz
                      .requestMatchers("/test").authenticated()
                      .requestMatchers("/api/auth/**").permitAll()
//                      .requestMatchers(HttpMethod.GET).permitAll()
                      .anyRequest().authenticated() );
                          
//              .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
      
//	      http.formLogin(form -> form
//	              .loginPage(LOGIN_URL)
//	              .loginProcessingUrl(LOGIN_URL)
//	              .failureUrl(LOGIN_FAIL_URL)
//	              .usernameParameter(USERNAME)
//	              .passwordParameter(PASSWORD)
//	              .defaultSuccessUrl(DEFAULT_SUCCESS_URL));
      
//      http.httpBasic(withDefaults());
      
     
      
      
      // for Jwt Authentication -----------
      
      //// In Old version
//      http.exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
//      							.and()
//      							.sessionManagement()
//      							.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

      //// In new version
      http.exceptionHandling(ex -> ex.authenticationEntryPoint(this.jwtAuthenticationEntryPoint))
      		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
      
      
      http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
      
      return http.build();
  }
  
  
  
//	// configure methods
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {	
//		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
//	}
	
	
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	    return builder.getAuthenticationManager();
	}
   

}
