package com.eleonoralion.security.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@ComponentScan("com.eleonoralion")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().antMatchers("/users/**").authenticated(); // Только для аутентифицированных пользователей
        http
                .authorizeRequests()
                    .antMatchers("/users/**")
                    .permitAll() // Разрешить всем
                    .antMatchers("/registration/**")
                    .permitAll()
                ;
        http.csrf().disable();
    }
}
