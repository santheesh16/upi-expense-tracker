package com.personal.upi_expense_tracker.repository;

import com.personal.upi_expense_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    Optional<User> findByEmail(String email);
}