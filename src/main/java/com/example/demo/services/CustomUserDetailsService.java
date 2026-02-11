package com.example.demo.services;

import com.example.demo.entity.CustomUserDetails;
import com.example.demo.entity.UserBean;
import com.example.demo.repo.UserRepositoryLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositoryLayer repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Find the user in DB
        UserBean user = repo.findByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        // 2. Wrap it in our Passport and return it
        return new CustomUserDetails(user);
    }
}