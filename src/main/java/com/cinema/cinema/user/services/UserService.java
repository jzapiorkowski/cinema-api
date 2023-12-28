package com.cinema.cinema.user.services;

import com.cinema.cinema.security.SecurityUtil;
import com.cinema.cinema.user.dto.CreateUserDto;
import com.cinema.cinema.user.exceptions.UserAlreadyExistsException;
import com.cinema.cinema.user.exceptions.UserDoesNotExistException;
import com.cinema.cinema.user.models.User;
import com.cinema.cinema.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(CreateUserDto createUserDto) {
        Optional<User> existingUser = userRepository.findByEmail(createUserDto.getEmail());

        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(existingUser.get().getEmail());
        }

        User user = new User();
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User getCurrentUserEntity() {
        String email = SecurityUtil.getSessionUserEmail();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserDoesNotExistException(email));
    }
}
