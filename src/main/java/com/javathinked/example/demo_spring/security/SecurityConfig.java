package com.javathinked.example.demo_spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Active CORS avec la configuration ci-dessous
            .cors(c -> c.configurationSource(corsConfigurationSource()))
            // API stateless
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Autoriser les preflight OPTIONS
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // Endpoints publics (auth, docs…)
                .requestMatchers(
                    "/auth/**",
                    "/actuator/**",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/error"
                ).permitAll()

                // 👉 Pour tes tests front sans JWT, ouvre provisoirement:
                .requestMatchers("/api/clients/**").permitAll()

                // Le reste reste protégé (quand tu enverras un JWT valide)
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cors = new CorsConfiguration();

        // 👉 Front Vite en dev
        cors.setAllowedOrigins(List.of(
            "http://localhost:5173"   // React Vite
            // "http://localhost:3000" // (si un autre front tourne ailleurs)
            // "https://app.tondomaine.com" // prod
        ));

        cors.setAllowedMethods(List.of("GET","POST","PUT","DELETE","PATCH","OPTIONS"));

        // Autorise tous les headers côté client (pratique en dev)
        cors.setAllowedHeaders(List.of("*"));

        // Expose les en-têtes utiles que le navigateur peut lire
        cors.setExposedHeaders(List.of("Location", "Authorization"));

        // Si tu n'utilises pas de cookies, ce true n'est pas obligatoire, mais OK en dev
        cors.setAllowCredentials(true);

        cors.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Limiter aux routes API
        source.registerCorsConfiguration("/api/**", cors);
        // (tu peux aussi mettre "/**" si tu préfères global)

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
