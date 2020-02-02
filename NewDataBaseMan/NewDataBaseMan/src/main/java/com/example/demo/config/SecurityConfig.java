package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favicon.ico", "/css/**", "/js/**", "/images/**", "/fonts/**", "/shutdown" /* for Demo */);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/login", "/register", "/login_error","/learn_fileopen","/openfile").permitAll()
                .antMatchers("/edit","/login_success","/openfile").hasRole("EDITOR")
                .antMatchers("/learn","/login_success","/openfile").hasRole("LEARNER")
                .anyRequest()
				.authenticated();
        
        http.formLogin()
        	.loginProcessingUrl("/")
        	.loginPage("/")
        	.successForwardUrl("/start")
            .failureUrl("/login_error")
            .usernameParameter("username")
        	.passwordParameter("password");
        
        http.logout()
        	.logoutSuccessUrl("/").permitAll();
        
        http 
        	.csrf().ignoringAntMatchers("/upload_edit","/learn_fileopen","/which");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}