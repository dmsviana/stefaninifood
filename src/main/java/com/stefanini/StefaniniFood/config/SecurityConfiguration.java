package com.stefanini.StefaniniFood.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/company/**").permitAll()
                .antMatchers(HttpMethod.POST, "/company").permitAll()
                .antMatchers(HttpMethod.PATCH, "/company/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/company/**").permitAll()
                .antMatchers(HttpMethod.GET, "/products/**").permitAll()
                .antMatchers(HttpMethod.POST, "/products/**").permitAll()
                .antMatchers(HttpMethod.GET, "/productsSearch/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/products/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
