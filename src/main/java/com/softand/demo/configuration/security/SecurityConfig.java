package com.softand.demo.configuration.security;

import com.softand.demo.configuration.security.filter.JwtTokenValidator;
import com.softand.demo.service.UserDetailServiceImpl;
import com.softand.demo.util.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
        .csrf(csrf -> csrf.disable())
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(http-> {
            // Configuracion de los endpoints publicos
            http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();
            http.requestMatchers(HttpMethod.GET, "/api/products").permitAll();
            http.requestMatchers(HttpMethod.GET, "/api/products/**").permitAll();
            
            // Configuracion de los endpoints privados
            http.requestMatchers(HttpMethod.GET, "method/get").hasAnyAuthority("READ");
            
            http.requestMatchers(HttpMethod.POST, "/api/products").hasAnyRole("ADMIN","DEVELOPER");
            http.requestMatchers(HttpMethod.PUT, "/api/products/**").hasAnyRole("ADMIN","DEVELOPER");
            http.requestMatchers(HttpMethod.DELETE, "/api/products/**").hasAnyRole("ADMIN","DEVELOPER");

            // Configurar el resto de endpoint - NO ESPECIFICADOS
            http.anyRequest().denyAll(); 
            // http.anyRequest().authenticated(); // PERMITE EL INGRESO A TODO EL QUE ESTE AUTENTICADO
        })
        .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();// PROVIDER PARA CONECTARNOS A UNA DB
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        // return NoOpPasswordEncoder.getInstance();
    }

    // public static void main(String[] args) {
    //     System.out.println(new BCryptPasswordEncoder().encode("1234"));
    // }

}
