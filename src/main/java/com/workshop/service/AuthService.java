package com.workshop.service;

import com.workshop.dto.LoginRequest;
import com.workshop.dto.LoginResponse;
import com.workshop.model.User;
import com.workshop.repository.UserRepository;
import com.workshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()
                    )
            );

            // Fetch user from DB
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Generate token with role
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

            // Build response
            LoginResponse response = new LoginResponse(token, user.getRole());

            // Add role-specific details
            if ("SPACE_OWNER".equalsIgnoreCase(user.getRole()) && user.getSpaces() != null) {
                response.setSpaceName(user.getSpaces().getSpace()); // space name
            } else if ("REGULAR_USER".equalsIgnoreCase(user.getRole()) && user.getUserProfile() != null) {
                response.setFirstName(user.getUserProfile().getFirstName());
                response.setLastName(user.getUserProfile().getLastName());
                response.setId(user.getId());
            }

            return response;

        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
