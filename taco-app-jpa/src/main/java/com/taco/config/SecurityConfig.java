package com.taco.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder
                .inMemoryAuthentication()
                .withUser("leo")
                .password("{noop}lein23")
                .authorities("ROLE_USER")
                .and()
                .withUser("woody")
                .password("{noop}bullseye")
                .authorities("ROLE_USER")
                ;
    }

}
