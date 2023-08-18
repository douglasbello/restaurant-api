package br.com.douglasbello.restaurant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/food", HttpMethod.POST.name())).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/food/{id}", HttpMethod.PUT.name())).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/food/{id}", HttpMethod.DELETE.name())).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/drink", HttpMethod.POST.name())).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/drink/{id}", HttpMethod.PUT.name())).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/drink/{id}", HttpMethod.DELETE.name())).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/admin/{id}", HttpMethod.PUT.name())).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/admin/{id}", HttpMethod.DELETE.name())).hasRole("ADMIN")
//                        .requestMatchers(new AntPathRequestMatcher("/admin", HttpMethod.POST.name())).hasRole("ADMIN")
//                        .requestMatchers(new AntPathRequestMatcher("/admin/sign-up", HttpMethod.POST.name())).hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.headers(headers -> headers.frameOptions((HeadersConfigurer.FrameOptionsConfig::disable)));
        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
