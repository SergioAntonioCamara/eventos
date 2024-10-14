package com.mindhub.todolist.security;

import com.mindhub.todolist.service.CustomerDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/index.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/events/create").hasAnyAuthority("ADMIN", "ORGANIZER") // Solo organizadores pueden crear eventos y administradores por supuesto.
                        .requestMatchers("/api/events").hasAuthority("USER")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/customers/").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                .csrf(csrf -> csrf.disable())
                .formLogin(formLogin -> {
                    formLogin.loginPage("/index.html")
                            .loginProcessingUrl("/api/login")
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .permitAll()
                            .successHandler((request, response, authentication) -> clearAuthenticationAttributes(request))
                            .failureHandler((request, response, exception) -> response.sendError(401, "Usuario o contraeña inválido"));
                })
                .logout(logout ->
                        logout.logoutUrl("/api/logout")
                                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                                .deleteCookies("JSESSIONID"))
                .rememberMe(rememberMe ->
                        rememberMe.userDetailsService(customerDetailsService));

        return http.build();
    }

    // aquí se configura el UserDetailsService y el codificador de contraseñas
    /*@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerDetailsService).passwordEncoder(passwordEncoder());
    }*/

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
