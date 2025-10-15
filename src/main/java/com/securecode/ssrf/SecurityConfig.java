package com.securecode.ssrf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
            http
                .authorizeHttpRequests(auth -> auth              		
                	.requestMatchers("/api/form").permitAll()
                	.requestMatchers("/api/submit").authenticated()
                    .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository()))
                .formLogin(form -> form.defaultSuccessUrl("/api/form", true).permitAll());     
            
            
            return http.build();
                    
    }
}
