package com.example.resens.config;
import com.example.resens.exceptions.CustomAccessDeniedHandler;
import com.example.resens.exceptions.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration implements WebMvcConfigurer {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private CorsConfiguration corsConfiguration;



    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowedOriginPatterns(Collections.singletonList("*")); // Use allowedOriginPatterns instead of allowedOrigins
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                            CustomAuthenticationEntryPoint customEntryPoint,
                                            CustomAccessDeniedHandler customAccessDeniedHandler) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers( "/user/Register","/user/auth","/user/ConfirmAccount/**","/user/forgot-password/**","/user/RegisterTeacher/**","/user/ConfirmTeacher/**",
                                        "/teachers/**","/teachers/deleteTeacher/**","/file","/demandeDeCours","/demandeDeCours/**",
                                        "/adresses","/adresses/**","/etudiants","/etudiants/**","/matieres","/matieres/**",
                                        "/niveauEtudes","/niveauEtudes/**","/paiements","/paiements/**",
                                        "/recommendations","/recommendations/**","/regions","/regions/**",
                                        "/villes","/villes/**","/support-cours","/support-cours/**").permitAll()
                                .requestMatchers("/config/disableAccount/**", "/config/enableAccount/**","/config/registerAdmin","/config/getAllUsers").permitAll()
                                .requestMatchers("/villes/*","/villes").permitAll()
                                .requestMatchers("/profile/**").authenticated()
                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(e -> {
                    e.authenticationEntryPoint(customEntryPoint);
                    e.accessDeniedHandler(customAccessDeniedHandler);
                });
        return httpSecurity.build();
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/user/auth")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET", "POST")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:4200");
    }

}
