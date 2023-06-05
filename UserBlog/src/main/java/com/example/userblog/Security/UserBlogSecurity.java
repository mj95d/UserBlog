package com.example.userblog.Security;

import com.example.userblog.Config.SpringConfiguration;
import com.example.userblog.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




    @AllArgsConstructor
    @Service
    public class UserBlogSecurity implements SpringConfiguration {
        private final UserRepository userRepository;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = this.userRepository.findUserByUsername(username);

            if(user==null){
                throw new UsernameNotFoundException("Wrong username or password");
            }

            return user;
        }
    }
