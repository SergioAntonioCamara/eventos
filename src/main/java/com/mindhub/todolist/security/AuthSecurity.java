package com.mindhub.todolist.security;

import com.mindhub.todolist.model.Customer;
import com.mindhub.todolist.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class AuthSecurity extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            Optional<Customer> optionalUser = customerRepository.findByEmail(username);
            if (optionalUser.isPresent()) {
                Customer user = optionalUser.get();
                return new User(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRol().toString()));
            } else {
                throw new UsernameNotFoundException("Email inválido");
            }
        });
    }
}
