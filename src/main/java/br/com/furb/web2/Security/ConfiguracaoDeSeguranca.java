package br.com.furb.web2.Security;

import org.hibernate.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurty){
        return httpSecurty
                .csrf(csrf -> csrf.disable())
                .sessionManagement(Session ->
                        Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
