package com.personal.upi_expense_tracker.service;

import com.personal.upi_expense_tracker.model.User;
import com.personal.upi_expense_tracker.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(request);
        String email = user.getAttribute("email");

        User dbUser = userRepo.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setGoogleId(user.getName());
            newUser.setUsername(user.getAttribute("username"));
            newUser.setRole("USER");
            newUser.setCreatedAt(LocalDateTime.now());
            return userRepo.save(newUser);
        });

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + dbUser.getRole())),
                user.getAttributes(), "email");
    }
}


