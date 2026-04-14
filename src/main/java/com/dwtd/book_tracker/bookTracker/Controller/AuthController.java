package com.dwtd.book_tracker.bookTracker.Controller;

import com.dwtd.book_tracker.bookTracker.DTO.LoginRequest;
import com.dwtd.book_tracker.bookTracker.DTO.RegisterRequest;
import com.dwtd.book_tracker.bookTracker.DTO.LoginResponse;
import com.dwtd.book_tracker.bookTracker.DTO.RegistrationResponse;
import com.dwtd.book_tracker.bookTracker.Services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}
