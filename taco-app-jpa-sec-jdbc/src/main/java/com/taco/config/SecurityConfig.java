package com.taco.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        //return new StandardPasswordEncoder("53cr3t");
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder authBuilder) throws Exception {

        //final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        authBuilder
                .userDetailsService(userDetailsService)
                //.passwordEncoder(encoder)
                .passwordEncoder(encoder())
                ;

//        authBuilder
//                .inMemoryAuthentication()
//                .withUser("leo")
//                .password(encoder.encode("lein23"))
//                //.password("{noop}lein23")
//                .authorities("ROLE_USER")
//                .and()
//                .withUser("woody")
//                .password("{noop}bullseye")
//                .authorities("ROLE_USER")
//                ;

        /*
         auth
    .jdbcAuthentication()
      .dataSource(dataSource)
      .usersByUsernameQuery(
          "select username, password, enabled from Users " +
          "where username=?")
      .authoritiesByUsernameQuery(
          "select username, authority from UserAuthorities " +
          "where username=?")
      .passwordEncoder(new StandardPasswordEncoder("53cr3t");
         */
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // the following two endpoints should be only accessible for users with ROLE_USER role.
                .antMatchers("/design", "/orders")
                .hasRole("ROLE_USER")
                .antMatchers("/", "/**")
                .permitAll()
                ;
    }
}
