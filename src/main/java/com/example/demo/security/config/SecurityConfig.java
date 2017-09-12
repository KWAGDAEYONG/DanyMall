package com.example.demo.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Filter ssoFilter;

    public SecurityConfig(Filter ssoFilter){
        this.ssoFilter = ssoFilter;
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception{
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/","/h2-console/**","/favicon.ico","/css/**","/js/**","/fonts/**","/video/**","/img/**","/admin**","/search/**","/login/**","/detail/**","/putCart/**","/myCart/**","/removeCart/**","/buy/**","/write/**","/modifyUserInfo","/loginBeforeModify","/modify/**","/signupProcess","/logout","/signup").permitAll()
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll()
                .and().headers().frameOptions().sameOrigin()
                .and().csrf().disable()
                .addFilterBefore(ssoFilter, BasicAuthenticationFilter.class);
    }
}
