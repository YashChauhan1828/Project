package com.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) // enables CORS using CorsConfigurationSource bean
            .csrf(AbstractHttpConfigurer::disable) // disables CSRF safely
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers("/images/**").permitAll()
            	.requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().permitAll()
            );
//        	.formLogin(form -> form
//                .loginPage("/elogin") // custom login page URL
//                .loginProcessingUrl("/doLogin") // form action URL
//                .successHandler("/ehome") // after successful login
//                .permitAll()
//            )
//            .logout(logout -> logout
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
//                .permitAll()
//            );
        return http.build();
    }
}
