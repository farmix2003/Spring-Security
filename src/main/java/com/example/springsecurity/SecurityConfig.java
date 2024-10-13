package com.example.springsecurity;

import com.example.springsecurity.service.JwtFilter;
import com.example.springsecurity.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailService userDetailService;


    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.
                                requestMatchers("/api/register", "/api/login").permitAll().
                                anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }

   @Bean
    public AuthenticationProvider authenticationProvider(){
       DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
       provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
       provider.setUserDetailsService(userDetailService);
        return provider;
   }

   @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
   }

}
