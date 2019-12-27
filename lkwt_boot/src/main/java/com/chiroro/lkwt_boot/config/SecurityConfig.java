package com.chiroro.lkwt_boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * SecurityConfig
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
            .antMatchers("/").permitAll() //기본 경로 전부 허용
            .antMatchers("/tnm/**").access("ROLE_STUDENT") // 메인 경로 학생만 허용
            .anyRequest().authenticated().and()
            .formLogin().loginPage("/login").permitAll().and()
            .logout().permitAll();
    }

}