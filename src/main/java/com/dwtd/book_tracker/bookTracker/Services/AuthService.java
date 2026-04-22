package com.dwtd.book_tracker.bookTracker.Services;

import com.dwtd.book_tracker.bookTracker.DTO.LoginRequest;
import com.dwtd.book_tracker.bookTracker.DTO.RegisterRequest;
import com.dwtd.book_tracker.bookTracker.DTO.LoginResponse;
import com.dwtd.book_tracker.bookTracker.DTO.RegistrationResponse;
import com.dwtd.book_tracker.bookTracker.Exception.InvalidCredentialsException;
import com.dwtd.book_tracker.bookTracker.Exception.UserAlreadyExistsException;
import com.dwtd.book_tracker.bookTracker.Models.User;
import com.dwtd.book_tracker.bookTracker.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public RegistrationResponse registerUser(RegisterRequest request) {
        try {
            User user = new User(
                    request.email(),
                    passwordEncoder.encode(request.password())
            );

            User savedUser = userRepository.save(user);

            String token = jwtService.generateToken(savedUser);

            return new RegistrationResponse(savedUser.getId(), savedUser.getEmail(), token);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException("A user with this email " + request.email() + " is already registered.");
        }
    }

    public LoginResponse login(LoginRequest request) {
        try {
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );

            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            String token = jwtService.generateToken(userDetails);

            return new LoginResponse(token);

        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
    }
}