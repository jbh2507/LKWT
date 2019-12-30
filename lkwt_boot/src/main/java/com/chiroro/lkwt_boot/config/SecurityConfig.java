package com.chiroro.lkwt_boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Setter;

/**
 * SecurityConfig
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Setter(onMethod_ = @Autowired)
  private UserDetailsService userDetailsService;

  @Bean
  public DaoAuthenticationProvider authProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
    return authProvider;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
          .antMatchers("/","/login","/service","/resources/**").permitAll()
          .antMatchers("/tnm/**").hasRole("STUDENT") // 메인 경로 학생만 허용
          .anyRequest()
            .authenticated()
            .and()
          .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/loginprocess")
            .defaultSuccessUrl("/tnm/main")
            .usernameParameter("username")
            .passwordParameter("password")
            .successHandler(new LoginSuccessHandler())
            .failureHandler(new LoginFailerHandler())
            .permitAll()
            .and()
          .logout()
            .permitAll()
            .and()
          .exceptionHandling().accessDeniedPage("/login");

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider());
    
  }
  
}