package com.personal.upi_expense_tracker.service;

import com.personal.upi_expense_tracker.model.User;
import com.personal.upi_expense_tracker.model.UserPrincipal;
import com.personal.upi_expense_tracker.repository.UserRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public Optional<User> getUserById(int userId) {
        return userRepo.findById(userId);
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public void updateUser(User user) {
        userRepo.save(user);
    }

    public void deleteUser(int userId) {
        userRepo.deleteById(userId);
    }
}
