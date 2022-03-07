package com.example.security.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//BASIC AUTH
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")//Authorizer requests
                .permitAll()
                .anyRequest() //anyRequest
                .authenticated()//authenticated username and password
                .and()
                .httpBasic();//mecanism to effort the autentication
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails gabrielAraujo = User.builder()
                .username("Gabriel")
                .password(passwordEncoder.encode("password"))
                .roles("STUDENT") //ROLE_STUDENT
                .build();
        UserDetails isabelAraujo = User.builder()
                .username("Gabriel")
                .password(passwordEncoder.encode("password123"))
                .roles("ADMIN") //ROLE_STUDENT
                .build();

        return new InMemoryUserDetailsManager(
                gabrielAraujo,
                isabelAraujo
        );
    }
}
