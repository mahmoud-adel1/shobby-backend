package com.shobby.user.service;

import com.shobby.common.exception.DatabaseErrorException;
import com.shobby.auth.exception.UserNotFoundException;
import com.shobby.user.entity.User;
import com.shobby.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Invalid request due to data integrity constraints.");
        } catch (Exception e) {
            throw new DatabaseErrorException("Something went wrong. Please try again later.");
        }
    }

    public boolean isUserExistByUsername(String username) {
        return userRepository.getUserByUsername(username).isPresent();
    }

    public boolean isUserExistByEmail(String email) {
        return userRepository.getUserByEmail(email).isPresent();
    }

    public boolean isUserExistByMobileNumber(String mobileNumber) {
        return userRepository.getUserByMobileNumber(mobileNumber).isPresent();
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public void deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) Objects.requireNonNull(authentication).getPrincipal();
        String username = Objects.requireNonNull(userDetails).getUsername();
        Optional<User> userOptional = userRepository.getUserByUsername(username);
        if (userOptional.isPresent()) {
            userOptional.get().setEnabled(false);
            userRepository.save(userOptional.get());
        } else {
            throw new UserNotFoundException("User not found.");
        }
    }
}
