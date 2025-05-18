package org.example.hibernate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    @Bean
    public UserDetailsService inMemoryUserDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .authorities("read")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .roles("ADMIN")
                .authorities("read", "write")
                .build();
        UserDetails writer = User.builder()
                .username("writer")
                .password(passwordEncoder.encode("password"))
                .roles("WRITER")
                .authorities("write")
                .build();
        return new InMemoryUserDetailsManager(user, admin, writer);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, LogoutSuccessHandler webSecurityUserLogoutHandler) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/persons/by-city").permitAll()
                    .requestMatchers("/persons/by-age").authenticated()
                    .requestMatchers("/auth/by-name-surname").hasAuthority("write")
            )
            .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .logout((logout) -> logout
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .logoutSuccessHandler(webSecurityUserLogoutHandler)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }

    @Bean
    public LogoutSuccessHandler webSecurityUserLogoutHandler() {
        return (request, response, authentication) -> {
            System.out.println("User logged out successfully!");
            response.sendRedirect("/persons");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
