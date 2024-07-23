package com.microservicesproject.user_service.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/signup", "/perform_login", "/perform_signup", "/admin")
                .permitAll()  // Allow these endpoints without authentication
                .anyRequest()
                .authenticated()  // Require authentication for any other endpoints
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login")  // Custom login page
                .loginProcessingUrl("/perform_login")  // URL to process login
                .defaultSuccessUrl("/home", true)  // Redirect to this URL on successful login
                .failureUrl("/login?error=true")  // Redirect to this URL on login failure
                .permitAll()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless sessions
            )
            .csrf(csrf -> csrf
                .disable()  // Disable CSRF protection for stateless applications (if needed)
            );

        return http.build();
    }
}
