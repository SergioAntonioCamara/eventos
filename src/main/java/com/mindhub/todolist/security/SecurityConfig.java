package com.mindhub.todolist.security;

import com.mindhub.todolist.repository.CustomerRepository;
import com.mindhub.todolist.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomerRepository customerRepository;

    @Autowired
    public SecurityConfig(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Devuelve el bean del servicio de detalles de usuario
        return new CustomerDetailsService(customerRepository); // Alternativamente, inyecta este bean
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    @Lazy
    private AuthenticationProvider authenticationProvider;


/*
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        //uso de servicio CustomerDetailsService inyectado
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // deshabilito CSRF para simplificar pruebas
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/customer/register", "/customer/login" ).permitAll() // cualquiera sin ningún tipo de autentificación acceda a ese endpoint
                        .anyRequest().authenticated() // protege todas las demás rutas
                )
                .formLogin(form -> form
                        .loginPage("/login") // pagina login
                        .permitAll() // permiso para todos
                )
                .logout(logout -> logout.permitAll()); // permiso libre

        return http.build();
    }

    /*@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
}
/*