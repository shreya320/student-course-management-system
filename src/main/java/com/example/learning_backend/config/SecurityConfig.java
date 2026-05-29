package com.example.learning_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.learning_backend.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    // This is to specify which endpoints are public and which are protected, and to set up basic authentication
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                // public endpoints
                .requestMatchers(
                        "/students",
                        "/courses",
                        "/enrollments/**",
                        "/register",
                        "/login"
                ).permitAll()
                // everything else protected
                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

        // http.formLogin(Customizer.withDefaults());
    }

    @SuppressWarnings("deprecation")
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

//     This is to set up in-memory authentication with two users: admin and user. This is just for demonstration purposes and should not be used in production.
//     @Bean
//     public UserDetailsService userDetailsService() {
//         UserDetails admin = User
//                 .withDefaultPasswordEncoder()
//                 .username("admin")
//                 .password("admin")
//                 .roles("ADMIN")
//                 .build();
//         UserDetails user = User
//                 .withDefaultPasswordEncoder()
//                 .username("user")
//                 .password("user")
//                 .roles("USER")
//                 .build();
//         return new InMemoryUserDetailsManager(admin, user);
//     }
}
