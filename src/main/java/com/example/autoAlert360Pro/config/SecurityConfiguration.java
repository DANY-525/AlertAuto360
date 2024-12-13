package com.example.autoAlert360Pro.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    // Configura el AuthenticationManager como un bean

    // Configura la cadena de filtros de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilita CSRF (ya que estás usando JWT)
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos
                        .requestMatchers(
                                "/auth/register",
                                "/auth/authenticate",
                                "/vehicle",
                                "/api/vehicles",
                                "/api/vehicles/user/**",
                                "/api/vehicles/{id}"
                        ).permitAll()
                        // Cualquier otra solicitud debe estar autenticada
                        .anyRequest().authenticated()
                )
                // Configura el manejo de sesiones como stateless (sin estado, ideal para JWT)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configura el proveedor de autenticación
                .authenticationProvider(authenticationProvider)
                // Añade el filtro JWT antes del UsernamePasswordAuthenticationFilter
               .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
