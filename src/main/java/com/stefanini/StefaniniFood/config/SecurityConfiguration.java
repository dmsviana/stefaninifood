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

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html"
            // other public endpoints of your API may be appended to this array
    };

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
                .antMatchers(HttpMethod.PATCH, "/products/**").permitAll()
                .antMatchers(HttpMethod.GET, "/employees/**").permitAll()
                .antMatchers(HttpMethod.POST, "/employees/**").permitAll()
                .antMatchers(HttpMethod.GET, "/employessSearch/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/employees/**").permitAll()
                .antMatchers(HttpMethod.PATCH, "/employees/**").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
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
