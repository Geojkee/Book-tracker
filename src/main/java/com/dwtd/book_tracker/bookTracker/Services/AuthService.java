package com.dwtd.book_tracker.bookTracker.Services;

import com.dwtd.book_tracker.bookTracker.DTO.AuthRequest;
import com.dwtd.book_tracker.bookTracker.DTO.LoginResponse;
import com.dwtd.book_tracker.bookTracker.DTO.RegistrationResponse;
import com.dwtd.book_tracker.bookTracker.Models.User;
import com.dwtd.book_tracker.bookTracker.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public RegistrationResponse registerUser(AuthRequest request){
        if (userRepository.findByEmail(request.email()).isPresent()){
            throw new RuntimeException("User with this email is exists");
        }

        User user = new User(
                request.email(),
                passwordEncoder.encode(request.password())
        );

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser.getEmail());

        return new RegistrationResponse(savedUser.getId(), savedUser.getEmail(), token);
    }

    public LoginResponse login(AuthRequest request){
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(()-> new RuntimeException("Incorrect email or password"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new RuntimeException("Incorrect email or password");
        }

        String token = jwtService.generateToken(request.email());

        return new LoginResponse(token);
    }
}
